package ast;

import ast.visitor.Visitor;

import java.lang.reflect.Field;

public class FieldDefinition extends AbstractDefinition {
	private int offset;

	public FieldDefinition(int line, int column, Type type, String name) {
		super(line, column, type, name);
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getOffset() {
		return offset;
	}

	@Override
	public void setScope(int scope) {
		//Los campos no tienen scope
	}

	@Override
	public int getScope() {
		//Los campos no tienen scope
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FieldDefinition other = (FieldDefinition) obj;
		if (!getName().equals(other.getName()))
			return false;
		return true;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}