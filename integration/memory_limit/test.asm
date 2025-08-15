# Ensures that programs are able to use all the memory even with the 128MB limit set by the judge.

.data
	newline: .asciiz "\n"

.text

	move $s0, $zero
	lh $s1, newline  # save newline and zero terminator because they're going to be overwritten by the loop later

gp_sp_loop:
	move $s2, $zero

	li $v0, 41  # random int
	move $a0, $zero  # rng id
	syscall
	
	bge $gp, 0x10400000, gp_done
	sw $a0, 0($gp)  # store the random number to gp
	li $s2, 1
gp_done:

	ble $sp, 0x7fbffffc, sp_done
	sw $a0, 0($sp)  # store the random number to sp
	li $s2, 1
sp_done:

	beqz $s2, heap_loop_pre
	
	subiu $sp, $sp, 4  # decrement sp, increment gp
	addiu $gp, $gp, 4
	
	addiu $s0, $s0, 1
	
	j gp_sp_loop

heap_loop_pre:
	move $s3, $zero

heap_loop:
	li $v0, 9  # sbrk
	li $a0, 4  # allocate a word
	syscall
	move $s4, $v0

	li $v0, 41  # random int
	move $a0, $zero  # rng id
	syscall
	
	sw $a0, ($s4)  # store so we're sure the memory is actually being used
	
	addiu $s3, $s3, 1
	beq $s3, 983024, all_done

	j heap_loop

all_done:
	# Memory OK, print "123321\n" to indicate that

	li $v0, 1  # print integer
	li $a0, 123321
	syscall
	
	sh $s1, newline
	li $v0, 4  # print string
	la $a0, newline
	syscall
