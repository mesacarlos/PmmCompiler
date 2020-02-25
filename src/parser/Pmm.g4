grammar Pmm;	

program:
	(definitions)* main
	;

definitions:
	vardef
	| funcdef
	;

main:
	'def' 'main' '(' '):' '{'
	(vardef)*
	(statement)* '}' EOF
	;

statement:
    expression '=' expression ';'
    | 'print' (expression) (',' (expression))* ';'
    | 'input' (expression) (',' (expression))* ';'
    | 'if' expression ':' ((statement) | '{' (statement)* '}') ('else' ((statement) | '{' (statement)* '}'))?
    | 'while' expression ':' ((statement) | '{' (statement)* '}')
    | 'return' expression ';'
    | ID '(' (expression (',' expression)*)? ')' ';'
    ;

expression:
    '(' expression ')'
    | expression '[' expression ']'
    | expression '.' ID
    | '(' tipo ')' expression
    | '-' expression
    | '!' expression
    | expression ('*'|'/'|'%') expression
    | expression ('+'|'-') expression
    | expression ('>'|'>='|'<'|'<='|'!='|'==') expression
    | expression ('&&'|'||') expression
    | ID '(' (expression (',' expression)*)? ')'
    | ID
    | REAL_CONSTANT
    | INT_CONSTANT
    | CHAR_CONSTANT
    ;

vardef:
	ID (',' ID)* ':' tipo ';'
	;

funcdef:
    'def' ID
        '(' ((ID':' tipo) (',' ID':' tipo)*)? '):' tipo?
        '{' (vardef)* (statement)* '}'
	;

tipo:
	'struct' '{' (vardef)* '}'
	| '[' INT_CONSTANT ']' tipo
	| 'int'
	| 'double'
	| 'char'
	;









TRASH:
    [ \n\t\r] -> skip
    ;

COMMENT:
    '#' .*?'\r'? ('\n'|EOF) -> skip
    ;

COMMENTML:
    '"""' .*? '"""' -> skip
    ;

fragment
ELEVADO:
    ([Ee] '-'? [0-9]+)
    |([Ee] '+'? [0-9]+)
    ;

REAL_CONSTANT:
    ([0-9]* '.' [0-9]+ ELEVADO?)
    |([0-9]+ '.' [0-9]* ELEVADO?)
    |[0-9]+ ELEVADO
    ;

INT_CONSTANT:
    '0' | ([1-9][0-9]*)
    ;

ID:
    [a-zA-Z_][a-zA-Z0-9_]*
    ;

CHAR_CONSTANT:
    '\'' (.|('\\' [0-9]+)|'\\n'|'\\t') '\''
	;