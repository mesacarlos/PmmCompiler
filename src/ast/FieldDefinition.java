package ast;

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



}