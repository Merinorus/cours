	.data
borninf:.word	0
bornsup:.word	10
array:	.word 12,3,6,18,7,15,10,9,0,4
space:	.asciiz " "
endl:	.asciiz "\n"

	.text
main:	la $a0, array		# charger l'adresse de array dans $a0
	lw $a1, borninf		# charger borninf dans $a1
	lw $a2, bornsup		# charger bornsup dans $a2
	jal affetat		# apperler affetat (afficher le tableau)

	addi $v0, $zero, 10	# $v0 = 10 (exit)
	syscall			# fin du programme

# afficher un nombre $a0 sur 2 caractères	
setw:	addi $sp, $sp, -12	# allouer l'espace dans la pile
	sw $ra, 0($sp)		# empiler $ra
	sw $a0, 4($sp)		# empiler $a0
	sw $a1, 8($sp)		# empiler $a1
	
	slti $a1, $a0, 10	# if ($a0 < 10) $t0 = 1
	beq $a1, 0, setwend	# if($a0 >= 10) goto setwend
	
	addi $v0, $zero, 4	# $v0 = 4 [print_string)
	la $a0, space		# $a0 = adresse de space
	syscall			# afficher space
	
setwend:lw $a0, 8($sp)		# dépiler $a1
	lw $a0, 4($sp)		# dépiler $a0
	lw $ra, 0($sp)		# dépiler $ra
	addi $sp, $sp, 12	# libérer la mémoire
	
	addi $v0, $zero, 1	# $v0 = 1 (print_int)
	syscall			# afficher $a0
	
	jr $ra			# retour à l'appelant

# afficher un tableau en $a0 de $a1 à $a2
affetat:addi $sp, $sp, -8	# allouer de la mémoire
	sw $a0, 4($sp)		# empiler $a0
	sw $ra, 0($sp)		# empiler $ra
	
	sll $t1, $a1, 2		# $t1 = borninf*4
	add $t0, $a0, $t1	# $t0 = $a0 + $t1 (adresse du tableau + borninf*4)
	sll $t2, $a2, 2		# $t2 = bornsup*4
	add $t2, $t2, $t0	# $t2 = $t0 + $t2 (adresse du tableau + bornsup*4)
	
affloop:slt $t1, $t0, $t2	# if($t0 < bornsup) $t1 = 1
	beq $t1, 0, affend	# if($t0 >=bornsup) goto affend
	
	add $a0, $zero, $t0	# $a0 = $t0 (adresse de tableau[i])
	lw $a0, 0($a0)		# $a0 = tableau[i]
	jal setw		# appel de setw (afficher $a0)
	
	addi $t0, $t0, 4	# $t0+=4
	j affloop		# retour à affloop
	
affend:	addi $v0, $zero, 4	# $v0 = 4 (print_string)
	la $a0, endl		# $a0 = adresse de endl
	syscall			# afficher endl
	
	lw $ra, 0($sp)		# dépiler $ra
	lw $a0, 4($sp)		# dépiler $a0
	addi $sp, $sp, 8	# libérer la mémoire
	
	jr $ra			# retour à l'appelant
	
