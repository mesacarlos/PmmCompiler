package ast;

import ast.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class VarDefinition extends AbstractDefinition {
	private int offset;
	private int scope;
	private List<Expression> values;
	private List<Assignment> assignments = new ArrayList<Assignment>();

	public VarDefinition(int line, int column, Type type, String name) {
		super(line, column, type, name);
	}

	public VarDefinition(int line, int column, Type type, String name, List<Expression> values) {
		super(line, column, type, name);
		this.values = values;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getOffset() {
		return offset;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	public int getScope() {
		return scope;
	}

	public List<Expression> getValues(){
		return values;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VarDefinition other = (VarDefinition) obj;
		if (!getName().equals(other.getName()))
			return false;
		return true;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}