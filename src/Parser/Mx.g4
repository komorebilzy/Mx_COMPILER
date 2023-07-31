grammar Mx;
import MxLexerRule;

program : (varDef|funcDef|classDef)*EOF;

varDef : type varDefUnit (Comma varDefUnit)* Semi;
type:typeName ('['']')*;
typeName:baseType|Identifier;
baseType:Int|Bool|Str;

varDefUnit:Identifier (Assign expr)?;

funcDef : returnType Identifier '(' parameterList? ')' '{' suite '}';  //parameterList? =>int main(){}
returnType:type|Void;
suite:statement*;
parameterList:type Identifier (Comma type Identifier)*;

classDef : Class Identifier '{' (varDef|classBuild|funcDef)* '}' Semi;
classBuild:Identifier '(' ')' '{' suite '}';


/*======================statement=========================*/
statement
:'{'suite'}'
| varDef
| ifStmt | whileStmt | forStmt
| breakStmt | continueStmt | returnStmt
| exprStmt;

ifStmt: If '(' expr ')' statement (Else statement)?;

whileStmt: While '(' expr ')' statement;

forStmt: For '(' forInit exprStmt expr? ')' statement;
forInit: varDef|exprStmt;
exprStmt: expr? Semi;

breakStmt: Break Semi;
continueStmt: Continue Semi;
returnStmt: Return expr?Semi;


/*======================expression=====================*/
expr
: '('expr')'                                     #parenExpr
| New typeName (newArrayUnit)* ('('')')?         #newExpr   //包括new array和new class
| expr op=Dot Identifier                         #memberExpr
| expr ('[' expr ']')+                           #arrayExpr
//| expr op=Dot Identifier'(' exprList? ')'        #funcExpr
| expr '(' exprList? ')'                         #funcExpr
| op=(AddSelf|SubSelf) expr                      #preAddExpr
| <assoc=right> expr op=(AddSelf|SubSelf)        #unaryExpr
| <assoc=right> op=(Not|BNot|Sub) expr           #unaryExpr
| expr op=(Mul|Div|Mod) expr                     #binaryExpr
| expr op=(Add|Sub) expr                         #binaryExpr
| expr op=(LShift|RShift) expr                   #binaryExpr
| expr op=(Less|Greater|LEqual|GEqual) expr      #binaryExpr
| expr op=(EEqual|NEqual) expr                   #binaryExpr
| expr op=BAnd expr                              #binaryExpr
| expr op=Xor expr                               #binaryExpr
| expr op=BOr expr                               #binaryExpr
| expr op=And expr                               #binaryExpr
| expr op=Or expr                                #binaryExpr
| expr op='?' expr op=':'expr                    #ternaryExpr
| <assoc=right> expr op=Assign expr              #assignExpr
| primary                                        #atomExpr
;

primary:
IntConst|StringConst|True|False|Null
|Identifier
|This;

newArrayUnit:'['expr?']';

exprList: expr (Comma expr)*;






