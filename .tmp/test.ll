declare void @print(ptr %s)
declare void @println(ptr %s)
declare void @printInt(i32 %x)
declare void @printlnInt(i32 %x)
declare ptr @getString()
declare i32 @getInt()
declare ptr @toString(i32 %x)
declare i32 @__string_length(ptr %s)
declare ptr @__string_subString(ptr %s, i32 %l, i32 %r)
declare i32 @__string_parseInt(ptr %s)
declare i32 @__string_ord(ptr %s, i32 %x)
declare ptr @__string_add(ptr %s, ptr %t)
declare i1 @__string_sge(ptr %s, ptr %t)
declare i1 @__string_slt(ptr %s, ptr %t)
declare i1 @__string_sle(ptr %s, ptr %t)
declare i1 @__string_sgt(ptr %s, ptr %t)
declare i1 @__string_eq(ptr %s, ptr %t)
declare i1 @__string_ne(ptr %s, ptr %t)
declare ptr @_malloc(i32 %size)
@n = global i32 0

@a = global ptr null

@i = global i32 0

define void @_mx_global_var_init_of_a() {
entry:
 %0 = call ptr @_malloc(i32 84)
 store i32 20, ptr %0
 %1 = getelementptr ptr, ptr %0, i32 1
 store ptr %1, ptr @a
 ret void 

}


define i32 @jud(i32 %x) {
entry:
 %0 = alloca i32
 store i32 %x, ptr %0
 %1 = alloca i32
 %2 = alloca i32
 store i32 0, ptr %1
 br label %block_0

block_0:
 %3 = load i32, ptr %1
 %4 = load i32, ptr @n
 %5 = load i32, ptr %0
 %6 = sdiv i32 %4, %5
 %7 = icmp slt i32 %3, %6
 br i1 %7, label %block_1, label %block_3

block_1:
 %8 = alloca i1
 store i1 false, ptr %8
 store i32 0, ptr %2
 br label %block_4

block_4:
 %9 = load i32, ptr %2
 %10 = load i32, ptr %0
 %11 = sub i32 %10, 1
 %12 = icmp slt i32 %9, %11
 br i1 %12, label %block_5, label %block_7

block_5:
 %13 = load ptr, ptr @a
 %14 = load i32, ptr %1
 %15 = load i32, ptr %0
 %16 = mul i32 %14, %15
 %17 = load i32, ptr %2
 %18 = add i32 %16, %17
 %19 = getelementptr i32, ptr %13, i32 %18
 %20 = load i32, ptr %19
 %21 = load ptr, ptr @a
 %22 = load i32, ptr %1
 %23 = load i32, ptr %0
 %24 = mul i32 %22, %23
 %25 = load i32, ptr %2
 %26 = add i32 %24, %25
 %27 = add i32 %26, 1
 %28 = getelementptr i32, ptr %21, i32 %27
 %29 = load i32, ptr %28
 %30 = icmp sgt i32 %20, %29
 br i1 %30, label %block_8, label %block_9

block_8:
 store i1 true, ptr %8
 br label %block_9

block_9:
 br label %block_6

block_6:
 %31 = load i32, ptr %2
 store i32 %32, ptr %2
 %32 = add i32 %31, 1
 br label %block_4

block_7:
 %33 = load i1, ptr %8
 %34 = xor i1 %33, true
 br i1 %34, label %block_10, label %block_11

block_10:
 ret i32 1

block_10:
 ret i32 1

block_11:
 br label %block_2

block_2:
 %35 = load i32, ptr %1
 store i32 %36, ptr %1
 %36 = add i32 %35, 1
 br label %block_0

block_3:
 ret i32 0

}


define i32 @main() {
entry:
 %0 = call i32 @getInt()
 store i32 %0, ptr @n
 store i32 0, ptr @i
 br label %block_0

block_0:
 %1 = load i32, ptr @i
 %2 = load i32, ptr @n
 %3 = icmp slt i32 %1, %2
 br i1 %3, label %block_1, label %block_3

block_1:
 %4 = load ptr, ptr @a
 %5 = load i32, ptr @i
 %6 = getelementptr i32, ptr %4, i32 %5
 %7 = call i32 @getInt()
 store i32 %7, ptr %6
 br label %block_2

block_2:
 %8 = load i32, ptr @i
 store i32 %9, ptr @i
 %9 = add i32 %8, 1
 br label %block_0

block_3:
 %10 = load i32, ptr @n
 store i32 %10, ptr @i
 br label %block_4

block_4:
 %11 = load i32, ptr @i
 %12 = icmp sgt i32 %11, 0
 br i1 %12, label %block_5, label %block_7

block_5:
 %13 = load i32, ptr @i
 %14 = call i32 @jud(i32 %13)
 %15 = icmp sgt i32 %14, 0
 br i1 %15, label %block_8, label %block_9

block_8:
 %16 = load i32, ptr @i
 %17 = call ptr @toString(i32 %16)
 call void @print(ptr %17)
 ret i32 0

block_8:
 %16 = load i32, ptr @i
 %17 = call ptr @toString(i32 %16)
 call void @print(ptr %17)
 ret i32 0

block_9:
 br label %block_6

block_6:
 %18 = load i32, ptr @i
 %19 = sdiv i32 %18, 2
 store i32 %19, ptr @i
 br label %block_4

block_7:
 ret i32 0

}


