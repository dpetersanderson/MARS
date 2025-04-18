.data

.text

main:
    li $v0, 5  # read first integer
    syscall

    move $s0, $v0

    li $v0, 5  # read second integer
    syscall

    add $a0, $s0, $v0

    li $v0, 1  # print result
    syscall
