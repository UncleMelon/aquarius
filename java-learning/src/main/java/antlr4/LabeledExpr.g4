
grammar LabeledExpr;
import CommonLexerRules;

prog: stat+ ;

stat:   expr  NEWLINE           # printExpr
    |   ID '=' expr NEWLINE     # assign
    |   'clear'                 # clear
    |   NEWLINE                 # blank
    ;

expr:   expr op=(MUL | DIV) expr        # MulDiv
    |   expr op=(ADD | SUB) expr        # AddSub
    |   INT                             # int
    |   ID                              # id
    |   '(' expr ')'                    # parens
    ;

//可以将下列符号当成常量来引用
MUL	:	'*' ;
DIV	:	'/'	;
ADD	:	'+'	;
SUB	:	'-'	;