.data
    msg_did_it: .asciiz "I didn't crash!"

.text
main:
    li $v0, 5  # read first integer
    syscall

    li $v0, 5  # read second integer
    syscall

    li $v0, 4  # print result
    la $a0, msg_did_it
    syscall
