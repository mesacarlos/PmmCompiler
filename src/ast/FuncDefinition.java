package ast;

import ast.visitor.Visitor;

import java.util.List;

public class FuncDefinition extends AbstractDefinition {
	private List<VarDefinition> variables;
	private List<Statement> sentences;
	private int totalBytesLocales;
	private int totalBytesParams;

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

	public void setTotalBytesLocales(int bytes){
		this.totalBytesLocales = bytes;
	}

	public int getTotalBytesLocales(){
		return totalBytesLocales;
	}

	public void setTotalBytesParams(int bytes){
		this.totalBytesParams = bytes;
	}

	public int getTotalBytesParams(){
		return totalBytesParams;
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

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}