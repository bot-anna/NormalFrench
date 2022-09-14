grammar NormalFrench;

file
: code
| EOF
;

code
: statement '.' code
| statement '.'
;

statement
: declare
| assign
| print
| crement
| loop
;

declare
: datatype ID
;

datatype
: 'baguette'
| '
 assign
 : ID 'jemapelle' expression
 ;

 expression
 : ID
 | INT
 | add
 | sub
 | lowerthan
 | greaterthan
 | crement
 ;

 unaryexpression
 : ID
 | INT
 ;

 crement
 : increment
 | decrement
 ;

 add
 : unaryexpression 'pierre' unaryexpression
 ;

 sub
 : unaryexpression 'cheval' unaryexpression
 ;

 lowerthan
 : unaryexpression 'voulevouz' unaryexpression
 ;

 greaterthan
 : unaryexpression 'moncherie' unaryexpression
 ;

 increment
 : unaryexpression 'jeanpierre'
 ;

 decrement
 : unaryexpression 'licorne'
 ;

 print
 : 'SACREBLEU' ID
 ;

 loop
 : 'merde' '('

 ID: ('a'..'z')+ ;
 INT: ('0'..'9')+ ;
 WS: [ \n\t\r]+ -> skip ;

