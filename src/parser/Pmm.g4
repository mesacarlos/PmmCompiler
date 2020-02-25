grammar Pmm;	

program: (CHAR_CONSTANT)+ EOF
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
    '0' | ([1-9][0-9]+)
    ;

ID:
    [a-zA-Z_][a-zA-Z0-9_]*
    ;

CHAR_CONSTANT:
    '\'' (.|('\\' [0-9]+)|'\\n'|'\\t') '\''
	;