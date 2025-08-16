.text
main:
	li $v0, 5  # read integer
	syscall
	
	li $s0, 1
	li $s1, 1
	li $s2, 1
	move $s7, $zero
	blt $v0, 2, print_answer
	subiu $v0, $v0, 1
loop:
	addiu $s7, $s7, 1
	addu $s2, $s0, $s1
	move $s0, $s1
	move $s1, $s2
	beq $v0, $s7, print_answer
	j loop
print_answer:
	li $v0, 1  # print integer
	move $a0, $s2
	syscall
