.data
msg_did_it: .asciiz "I ran the invalid syscall."

.text
main:
    li $v0, -52
    syscall

    li $v0, 4
    la $a0, msg_did_it
    syscall

