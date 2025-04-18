# Sample MIPS program that attempts to read from a non existing file, and then write to it.
# Based on the sample program by Kenneth Vollmar and Pete Sanderson

.data
filename_read:  .asciiz "thisfiledoesnotexist.txt"      # filename that will be opened for reading
filename_write:      .asciiz "thisfiledoesnotexist.txt"      # filename that will be opened for writing
it_failed_str: .asciiz "The syscall returned -1\n"
file_exists_str: .asciiz "Reproducing this bug requires that no file named \"thisfiledoesnotexist.txt\" exists in the current working directory.\nPlease delete that file and try again.\n"
bug_demo_ok:
  .asciiz "Opening for writing succeeded, which means the bug probably doesn't exist anymore. Great!\n"
bug_demo_fail:
  .ascii "Opening for writing failed, because opening for reading before also failed, and the filename was kept in the internal list of filenames.\n"
  .ascii "To see that this happened because of the attempt to open for reading, try changing either filename_read or filename_write,\n"
  .asciiz "or remove the code that tries to open the file for reading."

.text
 

###############################################################
# Open (for reading) a file that does not exist. This should fail, and that's expected.
li   $v0, 13       # system call for open file
la   $a0, filename_read     # output file name
li   $a1, 0        # Open for reading (flags are 0: read, 1: write)
li   $a2, 0        # mode is ignored
syscall            # open a file (file descriptor returned in $v0)
bne $v0, -1, error_file_exists  # Must fail because the file shouldn't exist

###############################################################
# Open (for writing) the same file that we tried to open for reading before
li   $v0, 13       # system call for open file
la   $a0, filename_write     # output file name
li   $a1, 1        # Open for writing (flags are 0: read, 1: write)
li   $a2, 0        # mode is ignored
syscall            # open a file (file descriptor returned in $v0)
bne $v0, -1, open_for_writing_success  # Check if fd == -1

li $v0, 4  # system call for printing a string
la $a0, bug_demo_fail # if we're here, the file failed to open
syscall
j exit

open_for_writing_success:

li $v0, 4  # system call for printing a string
la $a0, bug_demo_ok # if we're here, the file was opened successfuly
syscall
j exit

error_file_exists:

li $v0, 4  # system call for printing a string
la $a0, file_exists_str  # make sure file doesn't exist
syscall
j exit

exit: