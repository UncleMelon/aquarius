
//
//grammar Rows;
//
//@parser::members {
//    int col;
//    public RowParser(TokenStream input, int col) {
//        this(input);
//        this.col = col;
//    }
//}
//
//file:   (row NL)+ ;     // NL是换行符： '\r'? '\n'
//row
//locals [int i=0]
//    : ( STUFF
//        {
//            $i++;
//            if ( $i == col ) System.out.println($STUFF.text);
//        }
//      )+
//    ;
//
//
//TAB:     '\t' -> skip ;
//NL:      '\r'?  '\n' ;
//STUFF:   ~[\t\r\n]+ ;       //  匹配除tab符和换行符之外的任何字符
