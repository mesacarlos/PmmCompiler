package ast;

import java.util.List;

public class Program extends AbstractASTNode {
	private List<VarDefinition> varDefinitions;
	private List<FuncDefinition> funcDefinitions;

	public Program(int line, int column, List<VarDefinition> varDefinitions, List<FuncDefinition> funcDefinitions) {
		super(line, column);
		this.varDefinitions = varDefinitions;
		this.funcDefinitions = funcDefinitions;
	}

	public List<VarDefinition> getVarDefinitions() {
		return varDefinitions;
	}

	public void setVarDefinitions(List<VarDefinition> varDefinitions) {
		this.varDefinitions = varDefinitions;
	}

	public List<FuncDefinition> getFuncDefinitions() {
		return funcDefinitions;
	}

	public void setFuncDefinitions(List<FuncDefinition> funcDefinitions) {
		this.funcDefinitions = funcDefinitions;
	}

}