package ast;

import java.util.ArrayList;
import java.util.List;

public class Program extends AbstractASTNode {
	private List<Definition> definitions;

	public Program(int line, int column, List<VarDefinition> varDefinitions, List<FuncDefinition> funcDefinitions) {
		super(line, column);
		this.definitions = new ArrayList<Definition>();
		this.definitions.addAll(varDefinitions);
		this.definitions.addAll(funcDefinitions);
	}

	public void setDefinitions(List<Definition> definitions) {
		this.definitions = definitions;
	}

	public List<Definition> getDefinitions() {
		return definitions;
	}

}