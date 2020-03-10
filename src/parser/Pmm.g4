grammar Pmm;	

@header{
	import ast.*;
	import ast.error.*;
	import java.util.*;
}

program returns [Program ast]:
    { List<VarDefinition> globalVarDef = new ArrayList<VarDefinition>();
	List<FuncDefinition> globalFuncDef = new ArrayList<FuncDefinition>(); }
	(definitions { globalVarDef.addAll($definitions.varList); globalFuncDef.addAll($definitions.funcList); })* main
	{ globalFuncDef.add($main.ast);
	$ast = new Program($main.ast.getLine(), $main.ast.getColumn(), globalVarDef, globalFuncDef); }
	;

definitions returns [List<VarDefinition> varList, List<FuncDefinition> funcList]:
    { List<VarDefinition> varList = new ArrayList<VarDefinition>();
	List<FuncDefinition> funcList = new ArrayList<FuncDefinition>(); }
	((vardef { for(VarDefinition va : $vardef.ast)
	    if(varList.contains(va))
    		new ErrorType(va.getLine(), va.getColumn(), "Variable " + va.getName() + " already defined.");
    	else
			varList.add(va); })
	| (funcdef { funcList.add($funcdef.ast); }))
	{ $varList = varList; $funcList = funcList; }
	;

main returns [FuncDefinition ast]:
    { List<Statement> stList = new ArrayList<Statement>();
	List<VarDefinition> varList = new ArrayList<VarDefinition>(); }
	tok='def' mdef='main' '(' ')' ':' '{'
	(vardef { for(VarDefinition va : $vardef.ast)
	    if(varList.contains(va))
    		new ErrorType(va.getLine(), va.getColumn(), "Variable " + va.getName() + " already defined.");
    	else
			varList.add(va); })*
	(statement {stList.addAll($statement.ast);})* '}' EOF
	{ $ast = new FuncDefinition($mdef.getLine(), $mdef.getCharPositionInLine()+1, new FuncType($mdef.getLine(), $mdef.getCharPositionInLine()+1, ast.Void.getInstance(), new ArrayList<VarDefinition>()), $mdef.text, varList, stList); }
	;

statement returns [List<Statement> ast]:
	{ List<Statement> listaReturn = new ArrayList<Statement>(); }
    e1=expression '=' e2=expression ';'
    { listaReturn.add(new Assignment($e1.start.getLine(), $e1.start.getCharPositionInLine()+1, $e1.ast, $e2.ast));
    $ast = listaReturn; }

    | { List<Statement> listaReturn = new ArrayList<Statement>(); }
    'print' (ex1=expression { listaReturn.add(new Print($ex1.start.getLine(), $ex1.start.getCharPositionInLine()+1, $ex1.ast)); })
        (',' (ex2=expression { listaReturn.add(new Print($ex2.start.getLine(), $ex2.start.getCharPositionInLine()+1, $ex2.ast)); }))* ';'
    { $ast = listaReturn; }

    | { List<Statement> listaReturn = new ArrayList<Statement>(); }
    'input' (ex1=expression { listaReturn.add(new Input($ex1.start.getLine(), $ex1.start.getCharPositionInLine()+1, $ex1.ast)); })
        (',' (ex2=expression { listaReturn.add(new Input($ex2.start.getLine(), $ex2.start.getCharPositionInLine()+1, $ex2.ast)); }))* ';'
    { $ast = listaReturn; }

    | { List<Statement> listaReturn = new ArrayList<Statement>();
    List<Statement> listaif = new ArrayList<Statement>();
    List<Statement> listaelse = new ArrayList<Statement>(); }
    tok='if' cond=expression ':' ((st1=statement {listaif.addAll($st1.ast);}) | '{' (st2=statement {listaif.addAll($st2.ast);})* '}')
        ('else' ((st3=statement {listaelse.addAll($st3.ast);}) | '{' (st4=statement {listaelse.addAll($st4.ast);})* '}'))?
    { listaReturn.add(new IfElse($tok.getLine(), $tok.getCharPositionInLine()+1, $cond.ast, listaif, listaelse));
     $ast = listaReturn; }

	| { List<Statement> listaReturn = new ArrayList<Statement>();
    List<Statement> lista = new ArrayList<Statement>(); }
    whi='while' cond=expression ':' ((st1=statement { lista.addAll($st1.ast); } ) | '{' (st2=statement { lista.addAll($st2.ast); })* '}')
    { listaReturn.add(new While($whi.getLine(), $whi.getCharPositionInLine()+1, $cond.ast, lista));
     $ast = listaReturn; }

    | { List<Statement> listaReturn = new ArrayList<Statement>(); }
    ret='return' expression ';' { listaReturn.add(new Return($ret.getLine(), $ret.getCharPositionInLine()+1, $expression.ast));
    $ast = listaReturn; }

	| { List<Statement> listaReturn = new ArrayList<Statement>();
	List<Expression> params = new ArrayList<Expression>(); }
	ID '(' (ex1=expression { params.add($ex1.ast); } (',' ex2=expression { params.add($ex2.ast); })*)? ')' ';'
    { listaReturn.add(new Invocation($ID.getLine(), $ID.getCharPositionInLine()+1, new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text), params));
    $ast = listaReturn; }
	;

expression returns [Expression ast]:
	'(' expression ')'
    { $ast = $expression.ast; }

    | arr=expression '[' pos=expression ']'
    { $ast = new ArrayAccess($arr.start.getLine(), $arr.start.getCharPositionInLine()+1, $arr.ast, $pos.ast); }

    | e1=expression '.' ID
    { $ast = new FieldAccess($e1.start.getLine(), $e1.start.getCharPositionInLine()+1, $e1.ast, $ID.text); }

    | '(' tipo ')' expression
    { $ast = new Cast($tipo.start.getLine(), $tipo.start.getCharPositionInLine()+1, $tipo.ast, $expression.ast); }

    | min='-' expression
    { $ast = new UnaryMinus($min.getLine(), $min.getCharPositionInLine()+1, $expression.ast); }

    | excl='!' expression
    { $ast = new UnaryNot($excl.getLine(), $excl.getCharPositionInLine()+1, $expression.ast); }

    | e1=expression o=('*'|'/'|'%') e2=expression
    { $ast = new Arithmetic($e1.start.getLine(), $e1.start.getCharPositionInLine()+1, $o.text, $e1.ast, $e2.ast); }

    | e1=expression o=('+'|'-') e2=expression
    { $ast = new Arithmetic($e1.start.getLine(), $e1.start.getCharPositionInLine()+1, $o.text, $e1.ast, $e2.ast); }

    | e1=expression o=('>'|'>='|'<'|'<='|'!='|'==') e2=expression
    { $ast = new Comparison($e1.start.getLine(), $e1.start.getCharPositionInLine()+1, $o.text, $e1.ast, $e2.ast); }

	| e1=expression o=('&&'|'||') e2=expression
	{ $ast = new Logical($e1.start.getLine(), $e1.start.getCharPositionInLine()+1, $o.text, $e1.ast, $e2.ast); }

	| { List<Expression> params = new ArrayList<Expression>(); }
	ID '(' (ex1=expression { params.add($ex1.ast); } (',' ex2=expression { params.add($ex2.ast); })*)? ')'
	{ $ast = new Invocation($ID.getLine(), $ID.getCharPositionInLine()+1, new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text), params); }

	| ID
	{ $ast = new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text); }

	| REAL_CONSTANT
	{ $ast = new RealLiteral($REAL_CONSTANT.getLine(), $REAL_CONSTANT.getCharPositionInLine()+1, LexerHelper.lexemeToReal($REAL_CONSTANT.text)); }

	| INT_CONSTANT
	{ $ast = new IntLiteral($INT_CONSTANT.getLine(), $INT_CONSTANT.getCharPositionInLine()+1, LexerHelper.lexemeToInt($INT_CONSTANT.text)); }

	| CHAR_CONSTANT
	{ $ast = new CharLiteral($CHAR_CONSTANT.getLine(), $CHAR_CONSTANT.getCharPositionInLine()+1, LexerHelper.lexemeToChar($CHAR_CONSTANT.text)); }
	;

vardef returns [List<VarDefinition> ast]:
    { List<Variable> lista = new ArrayList<Variable>(); }
    id1=ID { lista.add(new Variable($id1.getLine(), $id1.getCharPositionInLine()+1, $id1.text)); }
    (',' id2=ID { lista.add(new Variable($id2.getLine(), $id2.getCharPositionInLine()+1, $id2.text)); })*
    ':' tipo ';' { List<VarDefinition> returnList = new ArrayList<VarDefinition>();
    for(Variable var : lista)
        returnList.add(new VarDefinition(var.getLine(),	var.getColumn(), $tipo.ast, var.getName()));
    $ast = returnList; }
	;

funcdef returns [FuncDefinition ast]:
	{ List<VarDefinition> listaParametros = new ArrayList<VarDefinition>();
    List<VarDefinition> varDefList = new ArrayList<VarDefinition>();
    List<Statement> statementList = new ArrayList<Statement>(); }
    'def' nom=ID '(' ((param=ID':' paramType=tipo
        { listaParametros.add(new VarDefinition($param.getLine(), $param.getCharPositionInLine()+1, $paramType.ast, $param.text)); })
        (',' params=ID':' paramsType=tipo
            { listaParametros.add(new VarDefinition($params.getLine(), $params.getCharPositionInLine()+1, $paramsType.ast, $params.text)); })*)? ')' ':' returnType=tipo? '{'
            (vardef { for(VarDefinition va : $vardef.ast)
                        if(varDefList.contains(va))
                            new ErrorType(va.getLine(), va.getColumn(), "Variable " + va.getName() + " already defined.");
                        else
                            varDefList.add(va); })*
            (statement { statementList.addAll($statement.ast); })* '}'
            { if($returnType.ctx == null)
                    $ast = new FuncDefinition($nom.getLine(), $nom.getCharPositionInLine()+1, new FuncType($nom.getLine(), $nom.getCharPositionInLine()+1, ast.Void.getInstance(), listaParametros), $nom.text, varDefList, statementList);
                else
                    $ast = new FuncDefinition($nom.getLine(), $nom.getCharPositionInLine()+1, new FuncType($nom.getLine(), $nom.getCharPositionInLine()+1, $returnType.ast, listaParametros), $nom.text, varDefList, statementList); }
	;

tipo returns [Type ast]:
	{ List<FieldDefinition> lista = new ArrayList<FieldDefinition>(); }
		tok='struct' '{' (vardef {
		    for(VarDefinition va : $vardef.ast){
		        FieldDefinition campo = new FieldDefinition(va.getLine(), va.getColumn(), va.getType(), va.getName());
		            if(lista.contains(campo))
                        new ErrorType(va.getLine(), va.getColumn(), "Field " + va.getName() + " already defined.");
                    else
                        lista.add(campo);
		    }
	    }
		)* '}'
		{ $ast = new Struct($tok.getLine(), $tok.getCharPositionInLine()+1, lista); }

	| cor='[' INT_CONSTANT ']' tipo
	{ $ast = new Array($cor.getLine(), $cor.getCharPositionInLine()+1, $tipo.ast, LexerHelper.lexemeToInt($INT_CONSTANT.text)); }

	| 'int'
	{ $ast = ast.Integer.getInstance(); }

	| 'double'
	{ $ast = Real.getInstance(); }

	| 'char'
	{ $ast = Char.getInstance(); }
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