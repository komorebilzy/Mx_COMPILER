lexer grammar MxLexerRule;

Add:'+';
Sub:'-';
Mul:'*';
Div:'/';
Mod:'%';
AddSelf:'++';
SubSelf:'--';


Less:'<';
Greater:'>';
LEqual:'<=';
GEqual:'>=';
NEqual:'!=';
EEqual:'==';
And:'&&';
Or:'||';
Not:'!';

BOr:'|';
Xor:'^';
BAnd:'&';
BNot:'~';
RShift:'>>';
LShift:'<<';

LParen:'(';
RParen:')';
LBracket:'[';
RBracket:']';
LBrace:'{';
RBrace:'}';

Assign:'=';

Semi:';';
Comma:',';
Dot:'.';
Quote:'"';
Arrow:'->';

Int:'int';
Bool:'bool';
Str:'string';
Void:'void';
New:'new';
Null:'null';
Class:'class';
This:'this';

True:'true';
False:'false';

If:'if';
Else:'else';
While:'while';
For:'for';
Return:'return';
Break:'break';
Continue:'continue';

Identifier:([a-zA-Z])([0-9a-zA-Z]|'_')*;   //定义变量名
IntConst:[1-9][0-9]*|'0';    //定义整数
StringConst:'"'(ESC|.)*?'"';    //定义string值
fragment ESC:'\\"'|'\\\\'|'\\n'|[ -~];    //[ -~]表示除回车符和换行符之外的 ASCII 可打印字符
//fragment使得ESC只能被其他规则调用 而不能作为独立的词法单元

WhiteSpace:[ \t\r\n]+ ->skip;    //排除情况：‘ ’(这个不可忽略) '\n' '\t' '\r'
LineComment:'//' ~[\r\n]* ->skip;
ParaComment:'/*' .*? '*/' ->skip;







