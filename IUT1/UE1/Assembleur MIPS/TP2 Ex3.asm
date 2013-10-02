	.data
intstr:	.asciiz "Entier :"
powstr:	.asciiz "Puissance :"
resstr: .asciiz "Resultat :"

	.text
main:	addi $v0, $zero, 4	# $v0 = 4 (print_string)
	la $a0, intstr		# $a0 = intstr
	syscall			# afficher intstr
	
	addi $v0, $zero, 5	# $v0 = 5 (read_int)
	syscall			# lire x
	add $t0, $zero, $v0	# $t0 = x
	
	addi $v0, $zero, 4	# $v0 = 4 (print_string)
	la $a0, powstr		# $a0 = powstr
	syscall			# afficher powstr
	
	addi $v0, $zero, 5	# $v0 = 5 (read_int)
	syscall			# lire n
	add $t1, $zero, $v0	# $t1 = n
	
	addi $v0, $zero, 4	# $v0 = 4 (print_string)
	la $a0, resstr		# $a0 = resstr
	syscall			# afficher resstr
	
	add $a0, $zero, $t0	# $a0 = $t0
	add $a1, $zero, $t1	# $a1 = $t1
	jal power		# goto power
	
	add $a0, $zero, $v0	# $a0 = $v0 (valeur de retour de power)
	add $v0, $zero, 1	# $v0 = 1 (print_int)
	syscall			# afficher $a0 (résultat de power)

	add $v0, $zero, 10	# $v0 = 10 (exit)
	syscall			# fin du programme
	
power:	addi $sp, $sp, -4	# allouer l'espace dans la pile
	sw $ra, 0($sp)		# empiler $ra
	
	slti $t0, $a1, 1	# if($a1 < 1) $t0 == 1
	beq $t0, $zero, recurs	# if($a1 >= 1) goto recurs
	addi $v0, $zero, 1	# else return 1
	
	addi $sp, $sp, 4	# rend l'espace dans la pile
	jr $ra			# retour à l'appelant
	
recurs: addi $a1, $a1, -1	# $a1 = $a1 - 1
	jal power		# appel récursif
	lw $ra, 0($sp)		# dépile $ra
	addi $sp, $sp, 4	# rend l'espace dans la pile
	mul $v0, $a0, $v0	# return x*power(x,n-1)
	jr $ra			# retour à la fonction appelante
