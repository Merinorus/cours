	.data
save:	.word	0,0,0,0,0,0,0,1,2,3

	.text
main:	la $a0, save		# $a0 = adresse de début de save
	addi $a1, $zero, 1	# $a1 = k = 1
	addi $a2, $zero, 9	# $a2 = n = 9
	addi $s0, $zero, 0	# $s0 = i = 0
	jal rechst		# goto rechst
	
	add $a0, $zero, $v0	# $a0 = $v0 (valeur de retour de rech)
	addi $v0, $zero, 1	# $v0 = 1 (print_int)
	syscall			# afficher $a0
	
	addi $v0, $zero, 10	# $v0 = 10 (exit)
	syscall			# fin du programme
	
rech:	addi $s0, $s0, 1	# $s0 = $s0 + 1 (i++)
rechst:	beq $s0, $a2, rech2	# if(n == i) goto rech2
	slt $t0, $s0, $a2	# if(n < i) $t0 == 1
	beq $t0, $zero, ret	# if(n > i) goto ret
	
rech2:	sll $t1, $s0, 2		# $t1 = i*4 = i<<2
	add $t1, $t1, $a0	# $t1 = $t1 + $a0 (i*4 + save => adresse de save(i])
	lw $t2, 0($t1)		# $t2 = save[i]
	beq $t2, $a1, ret	# if(save[i] == k) goto rech
	j rech
	
ret:	add $v0, $zero, $s0	# return $s0
	jr $ra			# retour au début
