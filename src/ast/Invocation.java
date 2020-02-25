package ast;

import java.util.List;

public class Invocation extends AbstractExpression implements Statement {
	private String name;
	private List<Expression> parameters;
	private Definition functionDefinition;

	public Invocation(int line, int column, String name, List<Expression> parameters) {
		super(line, column);
		this.name = name;
		this.parameters = parameters;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setParameters(List<Expression> parameters) {
		this.parameters = parameters;
	}

	public List<Expression> getParameters() {
		return parameters;
	}

	public void setDefinition(Definition def) {
		this.functionDefinition = def;
	}
	
	public Definition getDefinition() {
		return functionDefinition;
	}
	
}