.data

.text  # no main label
    li $v0, 5  # read number of inputs
    syscall

    subiu $s0, $v0, 1

    li $v0, 5  # read first input
    syscall
    move $s1, $v0  # assume it to be the max
    beqz $s0, found_max

    loop:
        li $v0, 5  # read next input
        syscall
        blt $v0, $s1, skip
        move $s1, $v0
        skip:
        subiu $s0, $s0, 1
        bgt $s0, 0, loop

    found_max:
    li $v0, 1
    move $a0, $s1
    syscall
