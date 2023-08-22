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

@p = global i32 0

@k = global i32 0

@i = global i32 0

define i32 @main() {
entry:
 %0 = call i32 @getInt()
 store i32 %0, ptr @n
 ret i32 0

}


