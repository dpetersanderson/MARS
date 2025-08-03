from pathlib import Path
import os
import subprocess as sp
import shutil

targets = [
    'x86_64-pc-windows-gnu',
    'x86_64-unknown-linux-gnu',
    'x86_64-apple-darwin',
]

output_binary_paths = [
    ['Mars-Windows.exe', 'x86_64-pc-windows-gnu/release/rust_wrapper.exe'],
    ['Mars-Linux', 'x86_64-unknown-linux-gnu/release/rust_wrapper'],
    ['Mars-OSX', 'x86_64-apple-darwin/release/rust_wrapper'],
]

base_path = Path(__file__).parent
rust_wrapper = base_path / 'wrappers' / 'rust_wrapper'
with open('Mars.jar', 'rb') as file:
    jar_contents = file.read()

os.chdir(rust_wrapper)
for target in targets:
    print('build', target)
    sp.call(['cargo', 'build', '--release', '--target', target])

for dist_file, wrapper in output_binary_paths:
    with open(f'/mnt/{dist_file}', 'wb') as outfile:
        with open('target/' + wrapper, 'rb') as infile:
            outfile.write(infile.read())
            outfile.write(jar_contents)
    print('write', dist_file)
