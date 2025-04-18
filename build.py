from glob import glob
import os
import subprocess as sp

resources = [
    'PseudoOps.txt',
    'Config.properties',
    'Syscall.properties',
    'Settings.properties',
    'MARSlicense.txt',
    'MipsXRayOpcode.xml',
    'registerDatapath.xml',
    'controlDatapath.xml',
    'ALUcontrolDatapath.xml',
    'docs',
    'help',
    'images',
]

if __name__ == '__main__':
    for p in glob('**/**.class', recursive=True):
        os.unlink(p)  # remove all binaries

    # compile source files
    sources = glob('**/**.java', recursive=True)
    exit_code = sp.call(['javac'] + sources)
    print(f'\njavac returned: {exit_code}')

    # create jar with resources and binaries
    binaries = glob('**/**.class', recursive=True)
    exit_code = sp.call(['jar', 'cmf', 'mainclass.txt', 'Mars.jar'] + resources + binaries)
    print(f'\njar returned: {exit_code}')
