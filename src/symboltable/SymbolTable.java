package symboltable;

import java.util.*;
import ast.Definition;

public class SymbolTable {
	
	private int scope = 0;
	private List<Map<String, Definition>> table;
	public SymbolTable()  {
		table = new ArrayList<Map<String, Definition>>();
		table.add(new HashMap<String, Definition>());
	}

	public void set() {
		table.add(new HashMap<String, Definition>());
		scope++;
	}
	
	public void reset() {
		table.remove(table.size() - 1);
		scope--;
	}
	
	public boolean insert(Definition definition) {
		if(findInCurrentScope(definition.getName()) != null)
			return false;
		table.get(table.size() - 1).put(definition.getName(), definition);
		definition.setScope(scope);
		return true;
	}
	
	public Definition find(String id) {
		for(int i = table.size() - 1; i >= 0; i--){
			Definition def = table.get(i).get(id);
			if(def != null)
				return def;
		}
		return null;
	}

	public Definition findInCurrentScope(String id) {
		return table.get(table.size() - 1).get(id);
	}
}
