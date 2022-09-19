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
| inish
;

inish
: declare 'jemapelle' expression
;

declare
: 'baguette' ID
;

 assign
 : ID 'jemapelle' expression
 ;

 expression
 : ID
 | INT
 | add
 | sub
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

 increment
 : unaryexpression 'jeanpierre'
 ;

 decrement
 : unaryexpression 'licorne'
 ;

 condition
 : unaryexpression conditionvariable unaryexpression
 | unaryexpression
 ;
 conditionvariable
 : 'moncherie'
 | 'voulevouz'
 | 'fromage'
 ;
 print
 : 'SACREBLEU' ID
 ;

 loop
 : 'merde' '(' condition ')' enterConditionedSegment code exitConditionedSegment
 ;

 enterConditionedSegment
 : 'bonjour'
 ;
 exitConditionedSegment
 : 'aurevoir'
 ;

 ID: ('a'..'z')+ ;
 INT: ('0'..'9')+ ;
 WS: [ \n\t\r]+ -> skip ;

