.data
    msg_did_it: .asciiz "I did not exit!"

.text
main:
    li $v0, 17
    li $a0, 4
    syscall

    li $v0, 4
    la $a0, msg_did_it
    syscall

