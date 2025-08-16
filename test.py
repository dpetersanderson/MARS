import subprocess as sp
from pathlib import Path
import shlex
import sys

base_path = Path(__file__).parent

args_append = ["nc", "me", "ae2", "se1", "sm"]
base_commands = [
    ["/usr/bin/java", "-Xmx128M", "-jar", "Mars.jar"] + args_append,
    ["/usr/lib/jvm/jdk-1.8.0_441-oracle-x64/bin/java", "-Xmx128M", "-jar", "Mars.jar"] + args_append,
    ["./Mars-Linux"] + args_append,
]
base_commands_assemble = [base_command + ["a"] for base_command in base_commands]


def write_summary(title, oks, fails):
    print(f'{title}: {oks} ({oks/(fails+oks):.2%}) passed, {fails} ({fails/(fails+oks):.2%}) failed.')


if __name__ == '__main__':
    total_fails, total_oks = 0, 0
    for base_command, base_command_assemble in zip(base_commands, base_commands_assemble):
        Path('thisfiledoesnotexist.txt').unlink(missing_ok=True)
        fails, oks = 0, 0
        for test in (base_path / 'integration').iterdir():
            sbfile = test / 'no_sandbox'
            fafile = test / 'fails_assembly'
            asm_command = base_command_assemble + [str(test / 'test.asm')]
            mars_asm = sp.Popen(asm_command, stdin=sp.DEVNULL, stdout=sp.PIPE, stderr=sp.PIPE)
            asm_code = mars_asm.wait()
            if bool(asm_code) != fafile.is_file():
                print(f'{test.name}: FAIL: ', end='')
                print('error in compilation' if asm_code else 'successful compilation')
                print(f'{test.name}: stdout: {mars_asm.stdout.read()}', end='')
                print(f'{test.name}: stderr: {mars_asm.stderr.read()}', end='')
                fails += 1
                continue
            if sbfile.is_file():
                command = shlex.join(base_command + [str(test / 'test.asm')])
            else:
                command = shlex.join(base_command + ['sb'] + [str(test / 'test.asm')])
            i = 0
            while (test / f'input-{i}.bin').is_file() and (test / f'output-{i}.bin').is_file():
                sys.stdout.flush()
                with (test / f'input-{i}.bin').open('rb') as infile:
                    mars = sp.Popen(command, stdin=infile, stdout=sp.PIPE, stderr=sp.PIPE, shell=True)
                    errors = mars.stderr.read().strip()
                    if mars.wait() != 0 or errors:
                        for error in errors.decode('utf-8').splitlines():
                            print(f'{test.name}-{i}: {error}')
                        print(f'{test.name}-{i}: mars returned {mars.returncode}')
                    output = mars.stdout.read().strip()
                    expected = (test / f'output-{i}.bin').read_bytes().strip()
                    if output == expected:
                        print(f'{test.name}-{i}: OK')
                        oks += 1
                    else:
                        print(f'{test.name}-{i}: FAIL')
                        fails += 1
                        print('expect:', expected)
                        print('got:', output)
                i += 1
            total_oks += oks
            total_fails += fails
        write_summary(f'Summary for {shlex.join(base_command)}', oks, fails)
        Path('thisfiledoesnotexist.txt').unlink(missing_ok=True)
    write_summary('Total', total_oks, total_fails)

