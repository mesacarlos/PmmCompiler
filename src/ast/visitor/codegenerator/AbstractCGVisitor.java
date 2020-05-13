package ast.visitor.codegenerator;

import ast.Integer;
import ast.Void;
import ast.*;
import ast.error.ErrorType;
import ast.visitor.Visitor;

public abstract class AbstractCGVisitor implements Visitor{

	@Override
	public Object visit(Arithmetic obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Array obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(ArrayAccess obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Assignment obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Cast obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Char obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(CharLiteral obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Comparison obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(FieldAccess obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(FieldDefinition obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(FuncDefinition obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(FuncType obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(IfElse obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Input obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Integer obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(IntLiteral obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Invocation obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Logical obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Print obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Program obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Real obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(RealLiteral obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Return obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Struct obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Swap obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(UnaryMinus obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(UnaryNot obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(VarDefinition obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Variable obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Void obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(While obj, Object params) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(ErrorType obj, Object params) {
		throw new IllegalStateException();
	}
}