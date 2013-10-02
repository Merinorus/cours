	.data
sumstr:	.asciiz "Somme = "

	.text
main: 	addi $s0, $zero, 0	# $s0 = 0

somme:	addi $v0, $zero, 5	# $v0 = 5 (read_int)
	syscall			# lecture d'un int dans $v0
	beq  $v0, $zero, end	# if($v0 == 0) goto end
	add $s0, $s0, $v0	# $s0 = $s0 + $v0
	j somme			# goto somme

end:	addi $v0, $zero, 4	# $v0 = 4 (print_string)
	la $a0, sumstr		# $a0 = sumstr
	syscall			# afficher sumstr
	
	addi $v0, $zero, 1	# $v0 = 1 (print_int)
	add  $a0, $zero, $s0	# $a0 = $s0
	syscall			# afficher $a0
	
	addi $v0, $zero, 10	# $v0 = 10 (exit)
	syscall			# fin du programme