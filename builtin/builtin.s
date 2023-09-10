	.text
	.attribute	4, 16
	.attribute	5, "rv32i2p0_m2p0_a2p0_c2p0"
	.file	"builtin.c"
	.globl	print
	.p2align	1
	.type	print,@function
print:
	mv	a1, a0
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	tail	printf
.Lfunc_end0:
	.size	print, .Lfunc_end0-print

	.globl	println
	.p2align	1
	.type	println,@function
println:
	mv	a1, a0
	lui	a0, %hi(.L.str.1)
	addi	a0, a0, %lo(.L.str.1)
	tail	printf
.Lfunc_end1:
	.size	println, .Lfunc_end1-println

	.globl	printInt
	.p2align	1
	.type	printInt,@function
printInt:
	mv	a1, a0
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	tail	printf
.Lfunc_end2:
	.size	printInt, .Lfunc_end2-printInt

	.globl	printlnInt
	.p2align	1
	.type	printlnInt,@function
printlnInt:
	mv	a1, a0
	lui	a0, %hi(.L.str.3)
	addi	a0, a0, %lo(.L.str.3)
	tail	printf
.Lfunc_end3:
	.size	printlnInt, .Lfunc_end3-printlnInt

	.globl	getString
	.p2align	1
	.type	getString,@function
getString:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	li	a0, 256
	call	malloc
	mv	a1, a0
	sw	a1, 8(sp)
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	call	scanf
	lw	a0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end4:
	.size	getString, .Lfunc_end4-getString

	.globl	getInt
	.p2align	1
	.type	getInt,@function
getInt:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	addi	a1, sp, 8
	call	scanf
	lw	a0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end5:
	.size	getInt, .Lfunc_end5-getInt

	.globl	toString
	.p2align	1
	.type	toString,@function
toString:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	a0, 4(sp)
	li	a0, 16
	call	malloc
	lw	a2, 4(sp)
	sw	a0, 8(sp)
	lui	a1, %hi(.L.str.2)
	addi	a1, a1, %lo(.L.str.2)
	call	sprintf
	lw	a0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end6:
	.size	toString, .Lfunc_end6-toString

	.globl	__string.length
	.p2align	1
	.type	__string.length,@function
__string.length:
	tail	strlen
.Lfunc_end7:
	.size	__string.length, .Lfunc_end7-__string.length

	.globl	__string.substring
	.p2align	1
	.type	__string.substring,@function
__string.substring:
	addi	sp, sp, -32
	sw	ra, 28(sp)
	sw	a2, 12(sp)
	sw	a1, 16(sp)
	sw	a0, 4(sp)
	sub	a0, a2, a1
	sw	a0, 8(sp)
	addi	a0, a0, 1
	call	malloc
	lw	a2, 12(sp)
	lw	a1, 16(sp)
	sw	a0, 20(sp)
	mv	a0, a1
	sw	a0, 24(sp)
	blt	a1, a2, .LBB8_2
	j	.LBB8_1
.LBB8_1:
	lw	a0, 20(sp)
	lw	a1, 8(sp)
	add	a2, a0, a1
	li	a1, 0
	sb	a1, 0(a2)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.LBB8_2:
	lw	a1, 12(sp)
	lw	a0, 24(sp)
	lw	a3, 20(sp)
	lw	a4, 16(sp)
	lw	a2, 4(sp)
	add	a2, a2, a0
	lb	a2, 0(a2)
	sub	a4, a0, a4
	add	a3, a3, a4
	sb	a2, 0(a3)
	addi	a0, a0, 1
	mv	a2, a0
	sw	a2, 24(sp)
	beq	a0, a1, .LBB8_1
	j	.LBB8_2
.Lfunc_end8:
	.size	__string.substring, .Lfunc_end8-__string.substring

	.globl	__string.parseInt
	.p2align	1
	.type	__string.parseInt,@function
__string.parseInt:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	lui	a1, %hi(.L.str.2)
	addi	a1, a1, %lo(.L.str.2)
	addi	a2, sp, 8
	call	sscanf
	lw	a0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end9:
	.size	__string.parseInt, .Lfunc_end9-__string.parseInt

	.globl	__string.ord
	.p2align	1
	.type	__string.ord,@function
__string.ord:
	add	a0, a0, a1
	lbu	a0, 0(a0)
	ret
.Lfunc_end10:
	.size	__string.ord, .Lfunc_end10-__string.ord

	.globl	__string.add
	.p2align	1
	.type	__string.add,@function
__string.add:
	addi	sp, sp, -32
	sw	ra, 28(sp)
	sw	a1, 20(sp)
	sw	a0, 16(sp)
	call	strlen
	mv	a1, a0
	lw	a0, 20(sp)
	sw	a1, 12(sp)
	call	strlen
	mv	a1, a0
	lw	a0, 12(sp)
	add	a0, a0, a1
	addi	a0, a0, 1
	call	malloc
	lw	a1, 16(sp)
	sw	a0, 24(sp)
	call	strcpy
	lw	a1, 20(sp)
	lw	a0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	tail	strcat
.Lfunc_end11:
	.size	__string.add, .Lfunc_end11-__string.add

	.globl	__string.slt
	.p2align	1
	.type	__string.slt,@function
__string.slt:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	srli	a0, a0, 31
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end12:
	.size	__string.slt, .Lfunc_end12-__string.slt

	.globl	__string.sle
	.p2align	1
	.type	__string.sle,@function
__string.sle:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	slti	a0, a0, 1
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end13:
	.size	__string.sle, .Lfunc_end13-__string.sle

	.globl	__string.sgt
	.p2align	1
	.type	__string.sgt,@function
__string.sgt:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	mv	a1, a0
	li	a0, 0
	slt	a0, a0, a1
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end14:
	.size	__string.sgt, .Lfunc_end14-__string.sgt

	.globl	__string.sge
	.p2align	1
	.type	__string.sge,@function
__string.sge:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	not	a0, a0
	srli	a0, a0, 31
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end15:
	.size	__string.sge, .Lfunc_end15-__string.sge

	.globl	__string.eq
	.p2align	1
	.type	__string.eq,@function
__string.eq:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	seqz	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end16:
	.size	__string.eq, .Lfunc_end16-__string.eq

	.globl	__string.ne
	.p2align	1
	.type	__string.ne,@function
__string.ne:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	snez	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end17:
	.size	__string.ne, .Lfunc_end17-__string.ne

	.globl	_malloc
	.p2align	1
	.type	_malloc,@function
_malloc:
	tail	malloc
.Lfunc_end18:
	.size	_malloc, .Lfunc_end18-_malloc

	.type	.L.str,@object
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%s"
	.size	.L.str, 3

	.type	.L.str.1,@object
.L.str.1:
	.asciz	"%s\n"
	.size	.L.str.1, 4

	.type	.L.str.2,@object
.L.str.2:
	.asciz	"%d"
	.size	.L.str.2, 3

	.type	.L.str.3,@object
.L.str.3:
	.asciz	"%d\n"
	.size	.L.str.3, 4

	.ident	"Ubuntu clang version 15.0.7"
	.section	".note.GNU-stack","",@progbits
	.addrsig
