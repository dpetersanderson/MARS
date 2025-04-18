.data
filename: .asciiz "out.txt"
content: .asciiz "Hello, World!"
msg_did_it: .asciiz "I wrote to the file successfuly."
msg_negative_fd: .asciiz "FD is negative."

.text
main:
    li $v0, 13
    la $a0, filename
    li $a1, 1
    syscall

    move $s0, $v0
    bltz $s0, negative_fd

    li $v0, 15
    move $a0, $s0
    la $a1, content
    la $a2, 0
    la $t0, content
    subiu $t0, $t0, 1

    strlen_loop:
        addiu $t0, $t0, 1
        addiu $a2, $a2, 1
        lb $t1, 0($t0)
        bnez $t1, strlen_loop

    syscall

    li $v0, 16
    move $a0, $s0
    syscall

    li $v0, 4
    la $a0, msg_did_it
    syscall


negative_fd:
    li $v0, 4
    la, $a0, msg_negative_fd
    syscall
