package ast.visitor;

import ast.*;
import ast.Void;
import ast.error.ErrorType;

public interface Visitor {
	public Object visit(Arithmetic obj, Object params);
	public Object visit(Array obj, Object params);
	public Object visit(ArrayAccess obj, Object params);
	public Object visit(Assignment obj, Object params);
	public Object visit(Cast obj, Object params);
	public Object visit(Char obj, Object params);
	public Object visit(CharLiteral obj, Object params);
	public Object visit(Comparison obj, Object params);
	public Object visit(FieldAccess obj, Object params);
	public Object visit(FieldDefinition obj, Object params);
	public Object visit(FuncDefinition obj, Object params);
	public Object visit(FuncType obj, Object params);
	public Object visit(IfElse obj, Object params);
	public Object visit(Input obj, Object params);
	public Object visit(ast.Integer obj, Object params);
	public Object visit(IntLiteral obj, Object params);
	public Object visit(Invocation obj, Object params);
	public Object visit(Logical obj, Object params);
	public Object visit(Print obj, Object params);
	public Object visit(Program obj, Object params);
	public Object visit(Real obj, Object params);
	public Object visit(RealLiteral obj, Object params);
	public Object visit(Return obj, Object params);
	public Object visit(Struct obj, Object params);
	public Object visit(UnaryMinus obj, Object params);
	public Object visit(UnaryNot obj, Object params);
	public Object visit(VarDefinition obj, Object params);
	public Object visit(Variable obj, Object params);
	public Object visit(Void obj, Object params);
	public Object visit(While obj, Object params);
	public Object visit(ErrorType obj, Object params);
}