package ast;

import java.util.List;

public class FuncDefinition extends AbstractDefinition {
	private List<VarDefinition> variables;
	private List<Statement> sentences;

	public FuncDefinition(int line, int column, FuncType type, String name, List<VarDefinition> variables, List<Statement> sentences) {
		super(line, column, type, name);
		this.variables = variables;
		this.sentences = sentences;
	}

	public void setVariables(List<VarDefinition> variables) {
		this.variables = variables;
	}

	public List<VarDefinition> getVariables() {
		return variables;
	}

	public void setSentences(List<Statement> sentences) {
		this.sentences = sentences;
	}

	public List<Statement> getSentences() {
		return sentences;
	}

	@Override
	public void setScope(int scope) {
		//Las funciones no tienen scope
	}

	@Override
	public int getScope() {
		//Las funciones no tienen scope
		return 0;
	}

}