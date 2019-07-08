//Define a grammar called Tql
grammar Tql;

AND:  A N D ;
OR: O R ;
GROUP: G R O U P ;
BY: B Y ;
MAX: M A X ;
SUM: S U M ;
AVG: A V G ;
MIN: M I N ;
COUNT: C O U N T ;
DATE_HISTOGRAM: D A T E SC H I S T O G R A M ;
STARTOF: S T A R T O F ;
ENDOF: E N D O F ;
IN: I N ;
fragment A      : [aA];
fragment B      : [bB];
fragment C      : [cC];
fragment D      : [dD];
fragment E      : [eE];
fragment F      : [fF];
fragment G      : [gG];
fragment H      : [hH];
fragment I      : [iI];
fragment J      : [jJ];
fragment K      : [kK];
fragment L      : [lL];
fragment M      : [mM];
fragment N      : [nN];
fragment O      : [oO];
fragment P      : [pP];
fragment Q      : [qQ];
fragment R      : [rR];
fragment S      : [sS];
fragment T      : [tT];
fragment U      : [uU];
fragment V      : [vV];
fragment W      : [wW];
fragment X      : [xX];
fragment Y      : [yY];
fragment Z      : [zZ];
fragment SC      : [_];
fragment DEC_DIGIT:                  [0-9];
fragment LETTER:                         [a-zA-Z];

ID:  ( DEC_DIGIT | LETTER |  '_' | '$' | '-' | ':' | '@' | '#' )+ ;        //ID必须字母开始

fragment
TEXT_STRING :  ( DEC_DIGIT | LETTER |
                '_' | ':' | ';' | '.' | '?' | '/' | '!' | '@' | '#' | '$' | '%' | '^' | '-' | '+' | '*' | '\\' |    // 英文符号
                '\u4E00'..'\u9FA5' | '\uF900'..'\uFA2D'  |                  // 中文字符
                '\u3002' | '\uFF1F' | '\uFF01' | '\uFF0C' | '\u3001' | '\uFF1B' | '\uFF1A' | '\u2018' | '\u2019' | '\u201C' | '\u201D' | '\uFF08' | '\uFF09' | '\u3010' | '\u3011'  //中文符号
               )+ ;

columnName : ID ;

stat: whereClause? groupByClause?
    ;

whereClause
    :  logicExpression
    ;

logicExpression
            : logicExpression logicOperator logicExpression      # logicOperation
            | fullColumnName  comparisonOperator value  # comparisonOperation
            | '(' logicExpression ')'  # parens
            | fullColumnName IN '(' value (',' value)*  ')' # inOperation
            ;

groupByClause
    : GROUP aggregateWindowedFunction?  byCaluse
    ;

byCaluse
    : BY groupByItem (',' groupByItem)*
    ;

groupByItem
    : fullColumnName
    | DATE_HISTOGRAM '(' field ',' interval ')'
    ;

aggregateWindowedFunction
    : agg=(AVG | MAX | MIN | SUM) '(' functionArg ')'
    | agg=COUNT '()'
    ;

logicOperator
            :  AND | '&&' | OR | '||' ;

comparisonOperator
            : '=' | '>' | '<' | '<=' | '>=' | '~'
            ;

value
    : columnName '()'               # valueFunction
    | tf=(STARTOF | ENDOF) '(' columnName (',' columnName)? ')'    # timeOfFunction
    | columnName                    # asciiValue
    | L_S_STRING                   # unicodeValue
    ;


L_S_STRING  : '\'' (('\'' '\'') | ('\\' '\'') | ~('\''))* '\'' ;


fullColumnName: columnName ;
functionArg:  columnName ;
field: columnName ;
interval: columnName ;


WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines