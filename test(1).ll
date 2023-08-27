declare void @_func_print(ptr)
declare void @_func_println(ptr)
declare void @_func_printInt(i32)
declare void @_func_printlnInt(i32)
declare ptr @_func_getString()
declare i32 @_func_getInt()
declare ptr @_func_toString(i32)
declare i32 @__string.length(ptr)
declare ptr @__string.substring(ptr, i32, i32)
declare i32 @__string.parseInt(ptr)
declare i32 @__string.ord(ptr, i32)
declare ptr @__string.add(ptr, ptr)
declare i1 @__string.lt(ptr, ptr)
declare i1 @__string.le(ptr, ptr)
declare i1 @__string.gt(ptr, ptr)
declare i1 @__string.ge(ptr, ptr)
declare i1 @__string.eq(ptr, ptr)
declare i1 @__string.ne(ptr, ptr)
declare ptr @__malloc(i32)

@n.0 = global i32 0
@m.0 = global i32 0
@g.0 = global ptr null
@INF.0 = global i32 10000000
%class.Edge = type { i32, i32, i32 }
%class.EdgeList = type { ptr, ptr, ptr, i32 }
%class.Array_Node = type { ptr, i32 }
%class.Heap_Node = type { ptr }
%class.Node = type { i32, i32 }
@.str.0 = private unnamed_addr constant [3 x i8] c"-1\00"
@.str.1 = private unnamed_addr constant [2 x i8] c" \00"
@.str.2 = private unnamed_addr constant [1 x i8] c"\00"

define void @_func__mx_global_var_init() {
entry:
  br label %return

return:
  ret void
}

define void @__Edge.Edge(ptr %this) {
entry:
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  br label %return

return:
  ret void
}

define void @__EdgeList.EdgeList(ptr %this) {
entry:
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  br label %return

return:
  ret void
}

define void @__EdgeList.init(ptr %this, i32 %n.72, i32 %m.72) {
entry:
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %n.72.addr = alloca i32
  store i32 %n.72, ptr %n.72.addr
  %m.72.addr = alloca i32
  store i32 %m.72, ptr %m.72.addr
  %_0 = load i32, ptr %m.72.addr
  %_1 = mul i32 %_0, 4
  %_2 = add i32 %_1, 4
  %_3 = call ptr @__malloc(i32 %_2)
  store i32 %_0, ptr %_3
  %_4 = getelementptr ptr, ptr %_3, i32 1
  %_5 = load ptr, ptr %this.addr
  %_6 = getelementptr %class.EdgeList, ptr %_5, i32 0, i32 0
  %_7 = load ptr, ptr %_6
  store ptr %_4, ptr %_6
  %_8 = load i32, ptr %m.72.addr
  %_9 = mul i32 %_8, 4
  %_10 = add i32 %_9, 4
  %_11 = call ptr @__malloc(i32 %_10)
  store i32 %_8, ptr %_11
  %_12 = getelementptr i32, ptr %_11, i32 1
  %_13 = load ptr, ptr %this.addr
  %_14 = getelementptr %class.EdgeList, ptr %_13, i32 0, i32 1
  %_15 = load ptr, ptr %_14
  store ptr %_12, ptr %_14
  %_16 = load i32, ptr %n.72.addr
  %_17 = mul i32 %_16, 4
  %_18 = add i32 %_17, 4
  %_19 = call ptr @__malloc(i32 %_18)
  store i32 %_16, ptr %_19
  %_20 = getelementptr i32, ptr %_19, i32 1
  %_21 = load ptr, ptr %this.addr
  %_22 = getelementptr %class.EdgeList, ptr %_21, i32 0, i32 2
  %_23 = load ptr, ptr %_22
  store ptr %_20, ptr %_22
  %i.72 = alloca i32
  store i32 0, ptr %i.72
  store i32 0, ptr %i.72
  br label %for.cond1

for.cond1:
  %_24 = load i32, ptr %i.72
  %_25 = load i32, ptr %m.72.addr
  %_26 = icmp slt i32 %_24, %_25
  br i1 %_26, label %for.body1, label %for.end1

for.body1:
  %_27 = load ptr, ptr %this.addr
  %_28 = getelementptr %class.EdgeList, ptr %_27, i32 0, i32 1
  %_29 = load ptr, ptr %_28
  %_30 = load i32, ptr %i.72
  %_31 = getelementptr i32, ptr %_29, i32 %_30
  %_32 = load i32, ptr %_31
  store i32 -1, ptr %_31
  br label %for.step1

for.step1:
  %_33 = load i32, ptr %i.72
  %_34 = add i32 %_33, 1
  store i32 %_34, ptr %i.72
  br label %for.cond1

for.end1:
  store i32 0, ptr %i.72
  br label %for.cond2

for.cond2:
  %_35 = load i32, ptr %i.72
  %_36 = load i32, ptr %n.72.addr
  %_37 = icmp slt i32 %_35, %_36
  br i1 %_37, label %for.body2, label %for.end2

for.body2:
  %_38 = load ptr, ptr %this.addr
  %_39 = getelementptr %class.EdgeList, ptr %_38, i32 0, i32 2
  %_40 = load ptr, ptr %_39
  %_41 = load i32, ptr %i.72
  %_42 = getelementptr i32, ptr %_40, i32 %_41
  %_43 = load i32, ptr %_42
  store i32 -1, ptr %_42
  br label %for.step2

for.step2:
  %_44 = load i32, ptr %i.72
  %_45 = add i32 %_44, 1
  store i32 %_45, ptr %i.72
  br label %for.cond2

for.end2:
  %_46 = load ptr, ptr %this.addr
  %_47 = getelementptr %class.EdgeList, ptr %_46, i32 0, i32 3
  %_48 = load i32, ptr %_47
  store i32 0, ptr %_47
  br label %return

return:
  ret void
}

define void @__EdgeList.addEdge(ptr %this, i32 %u.75, i32 %v.75, i32 %w.75) {
entry:
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %u.75.addr = alloca i32
  store i32 %u.75, ptr %u.75.addr
  %v.75.addr = alloca i32
  store i32 %v.75, ptr %v.75.addr
  %w.75.addr = alloca i32
  store i32 %w.75, ptr %w.75.addr
  %e.75 = alloca ptr
  %_49 = call ptr @__malloc(i32 12)
  call void @__Edge.Edge(ptr %_49)
  store ptr %_49, ptr %e.75
  %_50 = load i32, ptr %u.75.addr
  %_51 = load ptr, ptr %e.75
  %_52 = getelementptr %class.Edge, ptr %_51, i32 0, i32 0
  %_53 = load i32, ptr %_52
  store i32 %_50, ptr %_52
  %_54 = load i32, ptr %v.75.addr
  %_55 = load ptr, ptr %e.75
  %_56 = getelementptr %class.Edge, ptr %_55, i32 0, i32 1
  %_57 = load i32, ptr %_56
  store i32 %_54, ptr %_56
  %_58 = load i32, ptr %w.75.addr
  %_59 = load ptr, ptr %e.75
  %_60 = getelementptr %class.Edge, ptr %_59, i32 0, i32 2
  %_61 = load i32, ptr %_60
  store i32 %_58, ptr %_60
  %_62 = load ptr, ptr %e.75
  %_63 = load ptr, ptr %this.addr
  %_64 = getelementptr %class.EdgeList, ptr %_63, i32 0, i32 0
  %_65 = load ptr, ptr %_64
  %_66 = load ptr, ptr %this.addr
  %_67 = getelementptr %class.EdgeList, ptr %_66, i32 0, i32 3
  %_68 = load i32, ptr %_67
  %_69 = getelementptr ptr, ptr %_65, i32 %_68
  %_70 = load ptr, ptr %_69
  store ptr %_62, ptr %_69
  %_71 = load ptr, ptr %this.addr
  %_72 = getelementptr %class.EdgeList, ptr %_71, i32 0, i32 2
  %_73 = load ptr, ptr %_72
  %_74 = load i32, ptr %u.75.addr
  %_75 = getelementptr i32, ptr %_73, i32 %_74
  %_76 = load i32, ptr %_75
  %_77 = load ptr, ptr %this.addr
  %_78 = getelementptr %class.EdgeList, ptr %_77, i32 0, i32 1
  %_79 = load ptr, ptr %_78
  %_80 = load ptr, ptr %this.addr
  %_81 = getelementptr %class.EdgeList, ptr %_80, i32 0, i32 3
  %_82 = load i32, ptr %_81
  %_83 = getelementptr i32, ptr %_79, i32 %_82
  %_84 = load i32, ptr %_83
  store i32 %_76, ptr %_83
  %_85 = load ptr, ptr %this.addr
  %_86 = getelementptr %class.EdgeList, ptr %_85, i32 0, i32 3
  %_87 = load i32, ptr %_86
  %_88 = load ptr, ptr %this.addr
  %_89 = getelementptr %class.EdgeList, ptr %_88, i32 0, i32 2
  %_90 = load ptr, ptr %_89
  %_91 = load i32, ptr %u.75.addr
  %_92 = getelementptr i32, ptr %_90, i32 %_91
  %_93 = load i32, ptr %_92
  store i32 %_87, ptr %_92
  %_94 = load ptr, ptr %this.addr
  %_95 = getelementptr %class.EdgeList, ptr %_94, i32 0, i32 3
  %_96 = load i32, ptr %_95
  %_97 = add i32 %_96, 1
  store i32 %_97, ptr %_95
  br label %return

return:
  ret void
}

define i32 @__EdgeList.nVertices(ptr %this) {
entry:
  %_ret_val = alloca i32
  store i32 0, ptr %_ret_val
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %_99 = load ptr, ptr %this.addr
  %_100 = getelementptr %class.EdgeList, ptr %_99, i32 0, i32 2
  %_101 = load ptr, ptr %_100
  %_103 = getelementptr i32, ptr %_101, i32 -1
  %_102 = load i32, ptr %_103
  store i32 %_102, ptr %_ret_val
  br label %return

return:
  %_98 = load i32, ptr %_ret_val
  ret i32 %_98
}

define i32 @__EdgeList.nEdges(ptr %this) {
entry:
  %_ret_val = alloca i32
  store i32 0, ptr %_ret_val
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %_105 = load ptr, ptr %this.addr
  %_106 = getelementptr %class.EdgeList, ptr %_105, i32 0, i32 0
  %_107 = load ptr, ptr %_106
  %_109 = getelementptr ptr, ptr %_107, i32 -1
  %_108 = load i32, ptr %_109
  store i32 %_108, ptr %_ret_val
  br label %return

return:
  %_104 = load i32, ptr %_ret_val
  ret i32 %_104
}

define void @__Array_Node.Array_Node(ptr %this) {
entry:
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %_110 = load ptr, ptr %this.addr
  %_111 = getelementptr %class.Array_Node, ptr %_110, i32 0, i32 1
  %_112 = load i32, ptr %_111
  store i32 0, ptr %_111
  %_113 = call ptr @__malloc(i32 68)
  store i32 16, ptr %_113
  %_114 = getelementptr ptr, ptr %_113, i32 1
  %_115 = load ptr, ptr %this.addr
  %_116 = getelementptr %class.Array_Node, ptr %_115, i32 0, i32 0
  %_117 = load ptr, ptr %_116
  store ptr %_114, ptr %_116
  br label %return

return:
  ret void
}

define void @__Array_Node.push_back(ptr %this, ptr %v.80) {
entry:
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %v.80.addr = alloca ptr
  store ptr %v.80, ptr %v.80.addr
  %_119 = load ptr, ptr %this.addr
  %_118 = call i32 @__Array_Node.size(ptr %_119)
  %_120 = load ptr, ptr %this.addr
  %_121 = getelementptr %class.Array_Node, ptr %_120, i32 0, i32 0
  %_122 = load ptr, ptr %_121
  %_124 = getelementptr ptr, ptr %_122, i32 -1
  %_123 = load i32, ptr %_124
  %_125 = icmp eq i32 %_118, %_123
  br i1 %_125, label %if.then1, label %if.end1

if.then1:
  %_126 = load ptr, ptr %this.addr
  call void @__Array_Node.doubleStorage(ptr %_126)
  br label %if.end1

if.end1:
  %_127 = load ptr, ptr %v.80.addr
  %_128 = load ptr, ptr %this.addr
  %_129 = getelementptr %class.Array_Node, ptr %_128, i32 0, i32 0
  %_130 = load ptr, ptr %_129
  %_131 = load ptr, ptr %this.addr
  %_132 = getelementptr %class.Array_Node, ptr %_131, i32 0, i32 1
  %_133 = load i32, ptr %_132
  %_134 = getelementptr ptr, ptr %_130, i32 %_133
  %_135 = load ptr, ptr %_134
  store ptr %_127, ptr %_134
  %_136 = load ptr, ptr %this.addr
  %_137 = getelementptr %class.Array_Node, ptr %_136, i32 0, i32 1
  %_138 = load i32, ptr %_137
  %_139 = add i32 %_138, 1
  store i32 %_139, ptr %_137
  br label %return

return:
  ret void
}

define ptr @__Array_Node.pop_back(ptr %this) {
entry:
  %_ret_val = alloca ptr
  store ptr null, ptr %_ret_val
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %_141 = load ptr, ptr %this.addr
  %_142 = getelementptr %class.Array_Node, ptr %_141, i32 0, i32 1
  %_143 = load i32, ptr %_142
  %_144 = sub i32 %_143, 1
  store i32 %_144, ptr %_142
  %_145 = load ptr, ptr %this.addr
  %_146 = getelementptr %class.Array_Node, ptr %_145, i32 0, i32 0
  %_147 = load ptr, ptr %_146
  %_148 = load ptr, ptr %this.addr
  %_149 = getelementptr %class.Array_Node, ptr %_148, i32 0, i32 1
  %_150 = load i32, ptr %_149
  %_151 = getelementptr ptr, ptr %_147, i32 %_150
  %_152 = load ptr, ptr %_151
  store ptr %_152, ptr %_ret_val
  br label %return

return:
  %_140 = load ptr, ptr %_ret_val
  ret ptr %_140
}

define ptr @__Array_Node.back(ptr %this) {
entry:
  %_ret_val = alloca ptr
  store ptr null, ptr %_ret_val
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %_154 = load ptr, ptr %this.addr
  %_155 = getelementptr %class.Array_Node, ptr %_154, i32 0, i32 0
  %_156 = load ptr, ptr %_155
  %_157 = load ptr, ptr %this.addr
  %_158 = getelementptr %class.Array_Node, ptr %_157, i32 0, i32 1
  %_159 = load i32, ptr %_158
  %_160 = sub i32 %_159, 1
  %_161 = getelementptr ptr, ptr %_156, i32 %_160
  %_162 = load ptr, ptr %_161
  store ptr %_162, ptr %_ret_val
  br label %return

return:
  %_153 = load ptr, ptr %_ret_val
  ret ptr %_153
}

define ptr @__Array_Node.front(ptr %this) {
entry:
  %_ret_val = alloca ptr
  store ptr null, ptr %_ret_val
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %_164 = load ptr, ptr %this.addr
  %_165 = getelementptr %class.Array_Node, ptr %_164, i32 0, i32 0
  %_166 = load ptr, ptr %_165
  %_167 = getelementptr ptr, ptr %_166, i32 0
  %_168 = load ptr, ptr %_167
  store ptr %_168, ptr %_ret_val
  br label %return

return:
  %_163 = load ptr, ptr %_ret_val
  ret ptr %_163
}

define i32 @__Array_Node.size(ptr %this) {
entry:
  %_ret_val = alloca i32
  store i32 0, ptr %_ret_val
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %_170 = load ptr, ptr %this.addr
  %_171 = getelementptr %class.Array_Node, ptr %_170, i32 0, i32 1
  %_172 = load i32, ptr %_171
  store i32 %_172, ptr %_ret_val
  br label %return

return:
  %_169 = load i32, ptr %_ret_val
  ret i32 %_169
}

define void @__Array_Node.resize(ptr %this, i32 %newSize.87) {
entry:
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %newSize.87.addr = alloca i32
  store i32 %newSize.87, ptr %newSize.87.addr
  br label %while.cond1

while.cond1:
  %_173 = load ptr, ptr %this.addr
  %_174 = getelementptr %class.Array_Node, ptr %_173, i32 0, i32 0
  %_175 = load ptr, ptr %_174
  %_177 = getelementptr ptr, ptr %_175, i32 -1
  %_176 = load i32, ptr %_177
  %_178 = load i32, ptr %newSize.87.addr
  %_179 = icmp slt i32 %_176, %_178
  br i1 %_179, label %while.body1, label %while.end1

while.body1:
  %_180 = load ptr, ptr %this.addr
  call void @__Array_Node.doubleStorage(ptr %_180)
  br label %while.cond1

while.end1:
  %_181 = load i32, ptr %newSize.87.addr
  %_182 = load ptr, ptr %this.addr
  %_183 = getelementptr %class.Array_Node, ptr %_182, i32 0, i32 1
  %_184 = load i32, ptr %_183
  store i32 %_181, ptr %_183
  br label %return

return:
  ret void
}

define ptr @__Array_Node.get(ptr %this, i32 %i.89) {
entry:
  %_ret_val = alloca ptr
  store ptr null, ptr %_ret_val
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %i.89.addr = alloca i32
  store i32 %i.89, ptr %i.89.addr
  %_186 = load ptr, ptr %this.addr
  %_187 = getelementptr %class.Array_Node, ptr %_186, i32 0, i32 0
  %_188 = load ptr, ptr %_187
  %_189 = load i32, ptr %i.89.addr
  %_190 = getelementptr ptr, ptr %_188, i32 %_189
  %_191 = load ptr, ptr %_190
  store ptr %_191, ptr %_ret_val
  br label %return

return:
  %_185 = load ptr, ptr %_ret_val
  ret ptr %_185
}

define void @__Array_Node.set(ptr %this, i32 %i.90, ptr %v.90) {
entry:
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %i.90.addr = alloca i32
  store i32 %i.90, ptr %i.90.addr
  %v.90.addr = alloca ptr
  store ptr %v.90, ptr %v.90.addr
  %_192 = load ptr, ptr %v.90.addr
  %_193 = load ptr, ptr %this.addr
  %_194 = getelementptr %class.Array_Node, ptr %_193, i32 0, i32 0
  %_195 = load ptr, ptr %_194
  %_196 = load i32, ptr %i.90.addr
  %_197 = getelementptr ptr, ptr %_195, i32 %_196
  %_198 = load ptr, ptr %_197
  store ptr %_192, ptr %_197
  br label %return

return:
  ret void
}

define void @__Array_Node.swap(ptr %this, i32 %i.91, i32 %j.91) {
entry:
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %i.91.addr = alloca i32
  store i32 %i.91, ptr %i.91.addr
  %j.91.addr = alloca i32
  store i32 %j.91, ptr %j.91.addr
  %t.91 = alloca ptr
  %_199 = load ptr, ptr %this.addr
  %_200 = getelementptr %class.Array_Node, ptr %_199, i32 0, i32 0
  %_201 = load ptr, ptr %_200
  %_202 = load i32, ptr %i.91.addr
  %_203 = getelementptr ptr, ptr %_201, i32 %_202
  %_204 = load ptr, ptr %_203
  store ptr %_204, ptr %t.91
  %_205 = load ptr, ptr %this.addr
  %_206 = getelementptr %class.Array_Node, ptr %_205, i32 0, i32 0
  %_207 = load ptr, ptr %_206
  %_208 = load i32, ptr %j.91.addr
  %_209 = getelementptr ptr, ptr %_207, i32 %_208
  %_210 = load ptr, ptr %_209
  %_211 = load ptr, ptr %this.addr
  %_212 = getelementptr %class.Array_Node, ptr %_211, i32 0, i32 0
  %_213 = load ptr, ptr %_212
  %_214 = load i32, ptr %i.91.addr
  %_215 = getelementptr ptr, ptr %_213, i32 %_214
  %_216 = load ptr, ptr %_215
  store ptr %_210, ptr %_215
  %_217 = load ptr, ptr %t.91
  %_218 = load ptr, ptr %this.addr
  %_219 = getelementptr %class.Array_Node, ptr %_218, i32 0, i32 0
  %_220 = load ptr, ptr %_219
  %_221 = load i32, ptr %j.91.addr
  %_222 = getelementptr ptr, ptr %_220, i32 %_221
  %_223 = load ptr, ptr %_222
  store ptr %_217, ptr %_222
  br label %return

return:
  ret void
}

define void @__Array_Node.doubleStorage(ptr %this) {
entry:
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %copy.92 = alloca ptr
  %_224 = load ptr, ptr %this.addr
  %_225 = getelementptr %class.Array_Node, ptr %_224, i32 0, i32 0
  %_226 = load ptr, ptr %_225
  store ptr %_226, ptr %copy.92
  %szCopy.92 = alloca i32
  %_227 = load ptr, ptr %this.addr
  %_228 = getelementptr %class.Array_Node, ptr %_227, i32 0, i32 1
  %_229 = load i32, ptr %_228
  store i32 %_229, ptr %szCopy.92
  %_230 = load ptr, ptr %copy.92
  %_232 = getelementptr ptr, ptr %_230, i32 -1
  %_231 = load i32, ptr %_232
  %_233 = mul i32 %_231, 2
  %_234 = mul i32 %_233, 4
  %_235 = add i32 %_234, 4
  %_236 = call ptr @__malloc(i32 %_235)
  store i32 %_233, ptr %_236
  %_237 = getelementptr ptr, ptr %_236, i32 1
  %_238 = load ptr, ptr %this.addr
  %_239 = getelementptr %class.Array_Node, ptr %_238, i32 0, i32 0
  %_240 = load ptr, ptr %_239
  store ptr %_237, ptr %_239
  %_241 = load ptr, ptr %this.addr
  %_242 = getelementptr %class.Array_Node, ptr %_241, i32 0, i32 1
  %_243 = load i32, ptr %_242
  store i32 0, ptr %_242
  br label %for.cond1

for.cond1:
  %_244 = load ptr, ptr %this.addr
  %_245 = getelementptr %class.Array_Node, ptr %_244, i32 0, i32 1
  %_246 = load i32, ptr %_245
  %_247 = load i32, ptr %szCopy.92
  %_248 = icmp ne i32 %_246, %_247
  br i1 %_248, label %for.body1, label %for.end1

for.body1:
  %_249 = load ptr, ptr %copy.92
  %_250 = load ptr, ptr %this.addr
  %_251 = getelementptr %class.Array_Node, ptr %_250, i32 0, i32 1
  %_252 = load i32, ptr %_251
  %_253 = getelementptr ptr, ptr %_249, i32 %_252
  %_254 = load ptr, ptr %_253
  %_255 = load ptr, ptr %this.addr
  %_256 = getelementptr %class.Array_Node, ptr %_255, i32 0, i32 0
  %_257 = load ptr, ptr %_256
  %_258 = load ptr, ptr %this.addr
  %_259 = getelementptr %class.Array_Node, ptr %_258, i32 0, i32 1
  %_260 = load i32, ptr %_259
  %_261 = getelementptr ptr, ptr %_257, i32 %_260
  %_262 = load ptr, ptr %_261
  store ptr %_254, ptr %_261
  br label %for.step1

for.step1:
  %_263 = load ptr, ptr %this.addr
  %_264 = getelementptr %class.Array_Node, ptr %_263, i32 0, i32 1
  %_265 = load i32, ptr %_264
  %_266 = add i32 %_265, 1
  store i32 %_266, ptr %_264
  br label %for.cond1

for.end1:
  br label %return

return:
  ret void
}

define void @__Heap_Node.Heap_Node(ptr %this) {
entry:
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %_267 = call ptr @__malloc(i32 8)
  call void @__Array_Node.Array_Node(ptr %_267)
  %_268 = load ptr, ptr %this.addr
  %_269 = getelementptr %class.Heap_Node, ptr %_268, i32 0, i32 0
  %_270 = load ptr, ptr %_269
  store ptr %_267, ptr %_269
  br label %return

return:
  ret void
}

define void @__Heap_Node.push(ptr %this, ptr %v.97) {
entry:
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %v.97.addr = alloca ptr
  store ptr %v.97, ptr %v.97.addr
  %_271 = load ptr, ptr %this.addr
  %_272 = getelementptr %class.Heap_Node, ptr %_271, i32 0, i32 0
  %_273 = load ptr, ptr %_272
  %_274 = load ptr, ptr %v.97.addr
  call void @__Array_Node.push_back(ptr %_273, ptr %_274)
  %x.97 = alloca i32
  %_276 = load ptr, ptr %this.addr
  %_275 = call i32 @__Heap_Node.size(ptr %_276)
  %_277 = sub i32 %_275, 1
  store i32 %_277, ptr %x.97
  br label %while.cond1

while.cond1:
  %_278 = load i32, ptr %x.97
  %_279 = icmp sgt i32 %_278, 0
  br i1 %_279, label %while.body1, label %while.end1

while.body1:
  %p.99 = alloca i32
  %_281 = load ptr, ptr %this.addr
  %_282 = load i32, ptr %x.97
  %_280 = call i32 @__Heap_Node.pnt(ptr %_281, i32 %_282)
  store i32 %_280, ptr %p.99
  %_283 = load ptr, ptr %this.addr
  %_284 = getelementptr %class.Heap_Node, ptr %_283, i32 0, i32 0
  %_285 = load ptr, ptr %_284
  %_287 = load i32, ptr %p.99
  %_286 = call ptr @__Array_Node.get(ptr %_285, i32 %_287)
  %_288 = call i32 @__Node.key_(ptr %_286)
  %_289 = load ptr, ptr %this.addr
  %_290 = getelementptr %class.Heap_Node, ptr %_289, i32 0, i32 0
  %_291 = load ptr, ptr %_290
  %_293 = load i32, ptr %x.97
  %_292 = call ptr @__Array_Node.get(ptr %_291, i32 %_293)
  %_294 = call i32 @__Node.key_(ptr %_292)
  %_295 = icmp sge i32 %_288, %_294
  br i1 %_295, label %if.then2, label %if.end2

if.then2:
  br label %while.end1

if.end2:
  %_296 = load ptr, ptr %this.addr
  %_297 = getelementptr %class.Heap_Node, ptr %_296, i32 0, i32 0
  %_298 = load ptr, ptr %_297
  %_299 = load i32, ptr %p.99
  %_300 = load i32, ptr %x.97
  call void @__Array_Node.swap(ptr %_298, i32 %_299, i32 %_300)
  %_301 = load i32, ptr %p.99
  store i32 %_301, ptr %x.97
  br label %while.cond1

while.end1:
  br label %return

return:
  ret void
}

define ptr @__Heap_Node.pop(ptr %this) {
entry:
  %_ret_val = alloca ptr
  store ptr null, ptr %_ret_val
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %res.101 = alloca ptr
  %_303 = load ptr, ptr %this.addr
  %_304 = getelementptr %class.Heap_Node, ptr %_303, i32 0, i32 0
  %_305 = load ptr, ptr %_304
  %_306 = call ptr @__Array_Node.front(ptr %_305)
  store ptr %_306, ptr %res.101
  %_307 = load ptr, ptr %this.addr
  %_308 = getelementptr %class.Heap_Node, ptr %_307, i32 0, i32 0
  %_309 = load ptr, ptr %_308
  %_311 = load ptr, ptr %this.addr
  %_310 = call i32 @__Heap_Node.size(ptr %_311)
  %_312 = sub i32 %_310, 1
  call void @__Array_Node.swap(ptr %_309, i32 0, i32 %_312)
  %_313 = load ptr, ptr %this.addr
  %_314 = getelementptr %class.Heap_Node, ptr %_313, i32 0, i32 0
  %_315 = load ptr, ptr %_314
  %_316 = call ptr @__Array_Node.pop_back(ptr %_315)
  %_317 = load ptr, ptr %this.addr
  call void @__Heap_Node.maxHeapify(ptr %_317, i32 0)
  %_318 = load ptr, ptr %res.101
  store ptr %_318, ptr %_ret_val
  br label %return

return:
  %_302 = load ptr, ptr %_ret_val
  ret ptr %_302
}

define ptr @__Heap_Node.top(ptr %this) {
entry:
  %_ret_val = alloca ptr
  store ptr null, ptr %_ret_val
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %_320 = load ptr, ptr %this.addr
  %_321 = getelementptr %class.Heap_Node, ptr %_320, i32 0, i32 0
  %_322 = load ptr, ptr %_321
  %_323 = call ptr @__Array_Node.front(ptr %_322)
  store ptr %_323, ptr %_ret_val
  br label %return

return:
  %_319 = load ptr, ptr %_ret_val
  ret ptr %_319
}

define i32 @__Heap_Node.size(ptr %this) {
entry:
  %_ret_val = alloca i32
  store i32 0, ptr %_ret_val
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %_325 = load ptr, ptr %this.addr
  %_326 = getelementptr %class.Heap_Node, ptr %_325, i32 0, i32 0
  %_327 = load ptr, ptr %_326
  %_328 = call i32 @__Array_Node.size(ptr %_327)
  store i32 %_328, ptr %_ret_val
  br label %return

return:
  %_324 = load i32, ptr %_ret_val
  ret i32 %_324
}

define i32 @__Heap_Node.lchild(ptr %this, i32 %x.104) {
entry:
  %_ret_val = alloca i32
  store i32 0, ptr %_ret_val
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %x.104.addr = alloca i32
  store i32 %x.104, ptr %x.104.addr
  %_330 = load i32, ptr %x.104.addr
  %_331 = mul i32 %_330, 2
  %_332 = add i32 %_331, 1
  store i32 %_332, ptr %_ret_val
  br label %return

return:
  %_329 = load i32, ptr %_ret_val
  ret i32 %_329
}

define i32 @__Heap_Node.rchild(ptr %this, i32 %x.105) {
entry:
  %_ret_val = alloca i32
  store i32 0, ptr %_ret_val
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %x.105.addr = alloca i32
  store i32 %x.105, ptr %x.105.addr
  %_334 = load i32, ptr %x.105.addr
  %_335 = mul i32 %_334, 2
  %_336 = add i32 %_335, 2
  store i32 %_336, ptr %_ret_val
  br label %return

return:
  %_333 = load i32, ptr %_ret_val
  ret i32 %_333
}

define i32 @__Heap_Node.pnt(ptr %this, i32 %x.106) {
entry:
  %_ret_val = alloca i32
  store i32 0, ptr %_ret_val
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %x.106.addr = alloca i32
  store i32 %x.106, ptr %x.106.addr
  %_338 = load i32, ptr %x.106.addr
  %_339 = sub i32 %_338, 1
  %_340 = sdiv i32 %_339, 2
  store i32 %_340, ptr %_ret_val
  br label %return

return:
  %_337 = load i32, ptr %_ret_val
  ret i32 %_337
}

define void @__Heap_Node.maxHeapify(ptr %this, i32 %x.107) {
entry:
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %x.107.addr = alloca i32
  store i32 %x.107, ptr %x.107.addr
  %l.107 = alloca i32
  %_342 = load ptr, ptr %this.addr
  %_343 = load i32, ptr %x.107.addr
  %_341 = call i32 @__Heap_Node.lchild(ptr %_342, i32 %_343)
  store i32 %_341, ptr %l.107
  %r.107 = alloca i32
  %_345 = load ptr, ptr %this.addr
  %_346 = load i32, ptr %x.107.addr
  %_344 = call i32 @__Heap_Node.rchild(ptr %_345, i32 %_346)
  store i32 %_344, ptr %r.107
  %largest.107 = alloca i32
  %_347 = load i32, ptr %x.107.addr
  store i32 %_347, ptr %largest.107
  %_348 = load i32, ptr %l.107
  %_350 = load ptr, ptr %this.addr
  %_349 = call i32 @__Heap_Node.size(ptr %_350)
  %_351 = icmp slt i32 %_348, %_349
  br i1 %_351, label %logic.rhs1, label %logic.end1

logic.rhs1:
  %_353 = load ptr, ptr %this.addr
  %_354 = getelementptr %class.Heap_Node, ptr %_353, i32 0, i32 0
  %_355 = load ptr, ptr %_354
  %_357 = load i32, ptr %l.107
  %_356 = call ptr @__Array_Node.get(ptr %_355, i32 %_357)
  %_358 = call i32 @__Node.key_(ptr %_356)
  %_359 = load ptr, ptr %this.addr
  %_360 = getelementptr %class.Heap_Node, ptr %_359, i32 0, i32 0
  %_361 = load ptr, ptr %_360
  %_363 = load i32, ptr %largest.107
  %_362 = call ptr @__Array_Node.get(ptr %_361, i32 %_363)
  %_364 = call i32 @__Node.key_(ptr %_362)
  %_365 = icmp sgt i32 %_358, %_364
  br label %logic.end1

logic.end1:
  %_352 = phi i1 [ false, %entry ], [ %_365, %logic.rhs1 ]
  br i1 %_352, label %if.then2, label %if.end2

if.then2:
  %_366 = load i32, ptr %l.107
  store i32 %_366, ptr %largest.107
  br label %if.end2

if.end2:
  %_367 = load i32, ptr %r.107
  %_369 = load ptr, ptr %this.addr
  %_368 = call i32 @__Heap_Node.size(ptr %_369)
  %_370 = icmp slt i32 %_367, %_368
  br i1 %_370, label %logic.rhs3, label %logic.end3

logic.rhs3:
  %_372 = load ptr, ptr %this.addr
  %_373 = getelementptr %class.Heap_Node, ptr %_372, i32 0, i32 0
  %_374 = load ptr, ptr %_373
  %_376 = load i32, ptr %r.107
  %_375 = call ptr @__Array_Node.get(ptr %_374, i32 %_376)
  %_377 = call i32 @__Node.key_(ptr %_375)
  %_378 = load ptr, ptr %this.addr
  %_379 = getelementptr %class.Heap_Node, ptr %_378, i32 0, i32 0
  %_380 = load ptr, ptr %_379
  %_382 = load i32, ptr %largest.107
  %_381 = call ptr @__Array_Node.get(ptr %_380, i32 %_382)
  %_383 = call i32 @__Node.key_(ptr %_381)
  %_384 = icmp sgt i32 %_377, %_383
  br label %logic.end3

logic.end3:
  %_371 = phi i1 [ false, %if.end2 ], [ %_384, %logic.rhs3 ]
  br i1 %_371, label %if.then4, label %if.end4

if.then4:
  %_385 = load i32, ptr %r.107
  store i32 %_385, ptr %largest.107
  br label %if.end4

if.end4:
  %_386 = load i32, ptr %largest.107
  %_387 = load i32, ptr %x.107.addr
  %_388 = icmp eq i32 %_386, %_387
  br i1 %_388, label %if.then5, label %if.end5

if.then5:
  br label %return

if.end5:
  %_389 = load ptr, ptr %this.addr
  %_390 = getelementptr %class.Heap_Node, ptr %_389, i32 0, i32 0
  %_391 = load ptr, ptr %_390
  %_392 = load i32, ptr %x.107.addr
  %_393 = load i32, ptr %largest.107
  call void @__Array_Node.swap(ptr %_391, i32 %_392, i32 %_393)
  %_394 = load ptr, ptr %this.addr
  %_395 = load i32, ptr %largest.107
  call void @__Heap_Node.maxHeapify(ptr %_394, i32 %_395)
  br label %return

return:
  ret void
}

define void @__Node.Node(ptr %this) {
entry:
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  br label %return

return:
  ret void
}

define i32 @__Node.key_(ptr %this) {
entry:
  %_ret_val = alloca i32
  store i32 0, ptr %_ret_val
  %this.addr = alloca ptr
  store ptr %this, ptr %this.addr
  %_397 = load ptr, ptr %this.addr
  %_398 = getelementptr %class.Node, ptr %_397, i32 0, i32 1
  %_399 = load i32, ptr %_398
  %_400 = sub i32 0, %_399
  store i32 %_400, ptr %_ret_val
  br label %return

return:
  %_396 = load i32, ptr %_ret_val
  ret i32 %_396
}

define void @_func_init() {
entry:
  %_401 = call i32 @_func_getInt()
  store i32 %_401, ptr @n.0
  %_402 = call i32 @_func_getInt()
  store i32 %_402, ptr @m.0
  %_403 = call ptr @__malloc(i32 16)
  call void @__EdgeList.EdgeList(ptr %_403)
  store ptr %_403, ptr @g.0
  %_404 = load ptr, ptr @g.0
  %_405 = load i32, ptr @n.0
  %_406 = load i32, ptr @m.0
  call void @__EdgeList.init(ptr %_404, i32 %_405, i32 %_406)
  %i.114 = alloca i32
  store i32 0, ptr %i.114
  store i32 0, ptr %i.114
  br label %for.cond1

for.cond1:
  %_407 = load i32, ptr %i.114
  %_408 = load i32, ptr @m.0
  %_409 = icmp slt i32 %_407, %_408
  br i1 %_409, label %for.body1, label %for.end1

for.body1:
  %u.116 = alloca i32
  %_410 = call i32 @_func_getInt()
  store i32 %_410, ptr %u.116
  %v.116 = alloca i32
  %_411 = call i32 @_func_getInt()
  store i32 %_411, ptr %v.116
  %w.116 = alloca i32
  %_412 = call i32 @_func_getInt()
  store i32 %_412, ptr %w.116
  %_413 = load ptr, ptr @g.0
  %_414 = load i32, ptr %u.116
  %_415 = load i32, ptr %v.116
  %_416 = load i32, ptr %w.116
  call void @__EdgeList.addEdge(ptr %_413, i32 %_414, i32 %_415, i32 %_416)
  br label %for.step1

for.step1:
  %_417 = load i32, ptr %i.114
  %_418 = add i32 %_417, 1
  store i32 %_418, ptr %i.114
  br label %for.cond1

for.end1:
  br label %return

return:
  ret void
}

define ptr @_func_dijkstra(i32 %s.117) {
entry:
  %_ret_val = alloca ptr
  store ptr null, ptr %_ret_val
  %s.117.addr = alloca i32
  store i32 %s.117, ptr %s.117.addr
  %visited.117 = alloca ptr
  %_420 = load i32, ptr @n.0
  %_421 = mul i32 %_420, 4
  %_422 = add i32 %_421, 4
  %_423 = call ptr @__malloc(i32 %_422)
  store i32 %_420, ptr %_423
  %_424 = getelementptr i32, ptr %_423, i32 1
  store ptr %_424, ptr %visited.117
  %d.117 = alloca ptr
  %_425 = load i32, ptr @n.0
  %_426 = mul i32 %_425, 4
  %_427 = add i32 %_426, 4
  %_428 = call ptr @__malloc(i32 %_427)
  store i32 %_425, ptr %_428
  %_429 = getelementptr i32, ptr %_428, i32 1
  store ptr %_429, ptr %d.117
  %i.117 = alloca i32
  store i32 0, ptr %i.117
  store i32 0, ptr %i.117
  br label %for.cond1

for.cond1:
  %_430 = load i32, ptr %i.117
  %_431 = load i32, ptr @n.0
  %_432 = icmp slt i32 %_430, %_431
  br i1 %_432, label %for.body1, label %for.end1

for.body1:
  %_433 = load i32, ptr @INF.0
  %_434 = load ptr, ptr %d.117
  %_435 = load i32, ptr %i.117
  %_436 = getelementptr i32, ptr %_434, i32 %_435
  %_437 = load i32, ptr %_436
  store i32 %_433, ptr %_436
  %_438 = load ptr, ptr %visited.117
  %_439 = load i32, ptr %i.117
  %_440 = getelementptr i32, ptr %_438, i32 %_439
  %_441 = load i32, ptr %_440
  store i32 0, ptr %_440
  br label %for.step1

for.step1:
  %_442 = load i32, ptr %i.117
  %_443 = add i32 %_442, 1
  store i32 %_443, ptr %i.117
  br label %for.cond1

for.end1:
  %_444 = load ptr, ptr %d.117
  %_445 = load i32, ptr %s.117.addr
  %_446 = getelementptr i32, ptr %_444, i32 %_445
  %_447 = load i32, ptr %_446
  store i32 0, ptr %_446
  %q.117 = alloca ptr
  %_448 = call ptr @__malloc(i32 4)
  call void @__Heap_Node.Heap_Node(ptr %_448)
  store ptr %_448, ptr %q.117
  %src.117 = alloca ptr
  %_449 = call ptr @__malloc(i32 8)
  call void @__Node.Node(ptr %_449)
  store ptr %_449, ptr %src.117
  %_450 = load ptr, ptr %src.117
  %_451 = getelementptr %class.Node, ptr %_450, i32 0, i32 1
  %_452 = load i32, ptr %_451
  store i32 0, ptr %_451
  %_453 = load i32, ptr %s.117.addr
  %_454 = load ptr, ptr %src.117
  %_455 = getelementptr %class.Node, ptr %_454, i32 0, i32 0
  %_456 = load i32, ptr %_455
  store i32 %_453, ptr %_455
  %_457 = load ptr, ptr %q.117
  %_458 = load ptr, ptr %src.117
  call void @__Heap_Node.push(ptr %_457, ptr %_458)
  br label %while.cond2

while.cond2:
  %_459 = load ptr, ptr %q.117
  %_460 = call i32 @__Heap_Node.size(ptr %_459)
  %_461 = icmp ne i32 %_460, 0
  br i1 %_461, label %while.body2, label %while.end2

while.body2:
  %node.121 = alloca ptr
  %_462 = load ptr, ptr %q.117
  %_463 = call ptr @__Heap_Node.pop(ptr %_462)
  store ptr %_463, ptr %node.121
  %u.121 = alloca i32
  %_464 = load ptr, ptr %node.121
  %_465 = getelementptr %class.Node, ptr %_464, i32 0, i32 0
  %_466 = load i32, ptr %_465
  store i32 %_466, ptr %u.121
  %_467 = load ptr, ptr %visited.117
  %_468 = load i32, ptr %u.121
  %_469 = getelementptr i32, ptr %_467, i32 %_468
  %_470 = load i32, ptr %_469
  %_471 = icmp eq i32 %_470, 1
  br i1 %_471, label %if.then3, label %if.end3

if.then3:
  br label %while.cond2

if.end3:
  %_472 = load ptr, ptr %visited.117
  %_473 = load i32, ptr %u.121
  %_474 = getelementptr i32, ptr %_472, i32 %_473
  %_475 = load i32, ptr %_474
  store i32 1, ptr %_474
  %k.121 = alloca i32
  store i32 0, ptr %k.121
  %_476 = load ptr, ptr @g.0
  %_477 = getelementptr %class.EdgeList, ptr %_476, i32 0, i32 2
  %_478 = load ptr, ptr %_477
  %_479 = load i32, ptr %u.121
  %_480 = getelementptr i32, ptr %_478, i32 %_479
  %_481 = load i32, ptr %_480
  store i32 %_481, ptr %k.121
  br label %for.cond4

for.cond4:
  %_482 = load i32, ptr %k.121
  %_483 = icmp ne i32 %_482, -1
  br i1 %_483, label %for.body4, label %for.end4

for.body4:
  %v.124 = alloca i32
  %_484 = load ptr, ptr @g.0
  %_485 = getelementptr %class.EdgeList, ptr %_484, i32 0, i32 0
  %_486 = load ptr, ptr %_485
  %_487 = load i32, ptr %k.121
  %_488 = getelementptr ptr, ptr %_486, i32 %_487
  %_489 = load ptr, ptr %_488
  %_490 = getelementptr %class.Edge, ptr %_489, i32 0, i32 1
  %_491 = load i32, ptr %_490
  store i32 %_491, ptr %v.124
  %w.124 = alloca i32
  %_492 = load ptr, ptr @g.0
  %_493 = getelementptr %class.EdgeList, ptr %_492, i32 0, i32 0
  %_494 = load ptr, ptr %_493
  %_495 = load i32, ptr %k.121
  %_496 = getelementptr ptr, ptr %_494, i32 %_495
  %_497 = load ptr, ptr %_496
  %_498 = getelementptr %class.Edge, ptr %_497, i32 0, i32 2
  %_499 = load i32, ptr %_498
  store i32 %_499, ptr %w.124
  %alt.124 = alloca i32
  %_500 = load ptr, ptr %d.117
  %_501 = load i32, ptr %u.121
  %_502 = getelementptr i32, ptr %_500, i32 %_501
  %_503 = load i32, ptr %_502
  %_504 = load i32, ptr %w.124
  %_505 = add i32 %_503, %_504
  store i32 %_505, ptr %alt.124
  %_506 = load i32, ptr %alt.124
  %_507 = load ptr, ptr %d.117
  %_508 = load i32, ptr %v.124
  %_509 = getelementptr i32, ptr %_507, i32 %_508
  %_510 = load i32, ptr %_509
  %_511 = icmp sge i32 %_506, %_510
  br i1 %_511, label %if.then5, label %if.end5

if.then5:
  br label %for.step4

if.end5:
  %_512 = load i32, ptr %alt.124
  %_513 = load ptr, ptr %d.117
  %_514 = load i32, ptr %v.124
  %_515 = getelementptr i32, ptr %_513, i32 %_514
  %_516 = load i32, ptr %_515
  store i32 %_512, ptr %_515
  %_517 = call ptr @__malloc(i32 8)
  call void @__Node.Node(ptr %_517)
  store ptr %_517, ptr %node.121
  %_518 = load i32, ptr %v.124
  %_519 = load ptr, ptr %node.121
  %_520 = getelementptr %class.Node, ptr %_519, i32 0, i32 0
  %_521 = load i32, ptr %_520
  store i32 %_518, ptr %_520
  %_522 = load ptr, ptr %d.117
  %_523 = load i32, ptr %v.124
  %_524 = getelementptr i32, ptr %_522, i32 %_523
  %_525 = load i32, ptr %_524
  %_526 = load ptr, ptr %node.121
  %_527 = getelementptr %class.Node, ptr %_526, i32 0, i32 1
  %_528 = load i32, ptr %_527
  store i32 %_525, ptr %_527
  %_529 = load ptr, ptr %q.117
  %_530 = load ptr, ptr %node.121
  call void @__Heap_Node.push(ptr %_529, ptr %_530)
  br label %for.step4

for.step4:
  %_531 = load ptr, ptr @g.0
  %_532 = getelementptr %class.EdgeList, ptr %_531, i32 0, i32 1
  %_533 = load ptr, ptr %_532
  %_534 = load i32, ptr %k.121
  %_535 = getelementptr i32, ptr %_533, i32 %_534
  %_536 = load i32, ptr %_535
  store i32 %_536, ptr %k.121
  br label %for.cond4

for.end4:
  br label %while.cond2

while.end2:
  %_537 = load ptr, ptr %d.117
  store ptr %_537, ptr %_ret_val
  br label %return

return:
  %_419 = load ptr, ptr %_ret_val
  ret ptr %_419
}

define i32 @main() {
entry:
  %_ret_val = alloca i32
  store i32 0, ptr %_ret_val
  call void @_func__mx_global_var_init()
  call void @_func_init()
  %i.126 = alloca i32
  store i32 0, ptr %i.126
  %j.126 = alloca i32
  store i32 0, ptr %j.126
  store i32 0, ptr %i.126
  br label %for.cond1

for.cond1:
  %_539 = load i32, ptr %i.126
  %_540 = load i32, ptr @n.0
  %_541 = icmp slt i32 %_539, %_540
  br i1 %_541, label %for.body1, label %for.end1

for.body1:
  %d.128 = alloca ptr
  %_543 = load i32, ptr %i.126
  %_542 = call ptr @_func_dijkstra(i32 %_543)
  store ptr %_542, ptr %d.128
  store i32 0, ptr %j.126
  br label %for.cond2

for.cond2:
  %_544 = load i32, ptr %j.126
  %_545 = load i32, ptr @n.0
  %_546 = icmp slt i32 %_544, %_545
  br i1 %_546, label %for.body2, label %for.end2

for.body2:
  %_547 = load ptr, ptr %d.128
  %_548 = load i32, ptr %j.126
  %_549 = getelementptr i32, ptr %_547, i32 %_548
  %_550 = load i32, ptr %_549
  %_551 = load i32, ptr @INF.0
  %_552 = icmp eq i32 %_550, %_551
  br i1 %_552, label %if.then3, label %if.else3

if.then3:
  call void @_func_print(ptr @.str.0)
  br label %if.end3

if.else3:
  %_554 = load ptr, ptr %d.128
  %_555 = load i32, ptr %j.126
  %_556 = getelementptr i32, ptr %_554, i32 %_555
  %_557 = load i32, ptr %_556
  %_553 = call ptr @_func_toString(i32 %_557)
  call void @_func_print(ptr %_553)
  br label %if.end3

if.end3:
  call void @_func_print(ptr @.str.1)
  br label %for.step2

for.step2:
  %_558 = load i32, ptr %j.126
  %_559 = add i32 %_558, 1
  store i32 %_559, ptr %j.126
  br label %for.cond2

for.end2:
  call void @_func_println(ptr @.str.2)
  br label %for.step1

for.step1:
  %_560 = load i32, ptr %i.126
  %_561 = add i32 %_560, 1
  store i32 %_561, ptr %i.126
  br label %for.cond1

for.end1:
  store i32 0, ptr %_ret_val
  br label %return

return:
  %_538 = load i32, ptr %_ret_val
  ret i32 %_538
}

