package ast;

import ast.visitor.Visitor;

import java.util.List;

public class Struct extends AbstractType {
	private List<FieldDefinition> fieldDefinitions;

	public Struct(int line, int column, List<FieldDefinition> fieldDefinitions) {
		super(line, column);
		this.fieldDefinitions = fieldDefinitions;
	}

	public void setFieldDefinitions(List<FieldDefinition> definitions) {
		this.fieldDefinitions = definitions;
	}

	public List<FieldDefinition> getFieldDefinitions() {
		return fieldDefinitions;
	}

	public Type dot(String string){
		for(FieldDefinition field : fieldDefinitions){
			if(field.getName().equals(string))
				return field.getType();
		}
		return null;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}