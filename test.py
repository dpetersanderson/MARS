import subprocess as sp
from pathlib import Path

base_command = ["java", "-jar", "Mars.jar", "nc", "me", "ae2", "se1", "sm"]
base_command_assemble = base_command + ["a"]

if __name__ == '__main__':
    Path('thisfiledoesnotexist.txt').unlink(missing_ok=True)
    fails, oks = 0, 0
    for test in Path('integration').iterdir():
        sbfile = test / 'no_sandbox'
        fafile = test / 'fails_assembly'
        asm_command = base_command_assemble + [str(test / 'test.asm')]
        mars_asm = sp.Popen(asm_command, stdin=sp.DEVNULL, stdout=sp.PIPE, stderr=sp.PIPE)
        asm_code = mars_asm.wait()
        if bool(asm_code) != fafile.is_file():
            print(f'{test.name}-{i}: FAIL: ', end='')
            print('error in compilation' if asm_code else 'successful compilation')
            print(f'{test.name}-{i}: stdout: {mars_asm.stdout.read()}', end='')
            print(f'{test.name}-{i}: stderr: {mars_asm.stderr.read()}', end='')
        if sbfile.is_file():
            command = base_command + [str(test / 'test.asm')]
        else:
            command = base_command + ['sb'] + [str(test / 'test.asm')]
        i = 0
        while (test / f'input-{i}.bin').is_file() and (test / f'output-{i}.bin').is_file():
            with (test / f'input-{i}.bin').open('rb') as infile:
                mars = sp.Popen(command, stdin=infile, stdout=sp.PIPE, stderr=sp.PIPE)
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
    print(f'Summary: {oks} ({oks/(fails+oks):.2%}) passed, {fails} ({fails/(fails+oks):.2%}) failed.')
    Path('thisfiledoesnotexist.txt').unlink(missing_ok=True)

