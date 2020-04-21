package ast.visitor.codegenerator;

import ast.*;
import ast.visitor.AbstractVisitor;

public class OffsetVisitor extends AbstractVisitor {
	// calculo de offset de variables globales: suma de los tamaños de variables anteriores
	// calculo de offset de variables locales: BP - suma de tamaños de variables anteriores (incluyendose a si misma)
	// calculo offset parametros: BP + 4 + tamaños de argumentos declarados a la derecha (sin sumarse a si mismo)

	private int sumaGlobalesAnteriores = 0;
	private int sumaLocalesAnteriores = 0;

	@Override
	public Object visit(FuncDefinition obj, Object params) {
		//Pasamos al FuncType la definicion de funcion para poder añadirle el tamaño total de sus parametros
		obj.getType().accept(this, obj);
		for (VarDefinition va : obj.getVariables())
			va.accept(this, params);

		//Guardamos en la definicion de funcion la cantidad total de bytes necesarias para variables locales
		// y reiniciamos el contador para las siguientes funciones
		obj.setTotalBytesLocales(sumaLocalesAnteriores);
		sumaLocalesAnteriores = 0;
		for (Statement stm : obj.getSentences())
			stm.accept(this, params);
		return null;
	}

	@Override
	public Object visit(FuncType obj, Object params) {
		obj.getType().accept(this, params);

		int sumaParametrosDerecha = 4;
		for (int i = obj.getVarDefinitions().size()-1; i >= 0; i--) { // Es parametro
			obj.getVarDefinitions().get(i).setOffset(sumaParametrosDerecha);
			sumaParametrosDerecha += obj.getVarDefinitions().get(i).getType().numberOfBytes();
		}
		FuncDefinition fun = (FuncDefinition) params;
		fun.setTotalBytesParams(sumaParametrosDerecha-4);
		return null;
	}

	@Override
	public Object visit(Struct obj, Object params) {
		int sumaOffsetAnteriores = 0;
		for(FieldDefinition def : obj.getFieldDefinitions()) {
			def.accept(this, params);
			//Calculamos Offset de los campos
			def.setOffset(sumaOffsetAnteriores);
			sumaOffsetAnteriores += def.getType().numberOfBytes();
		}
		return null;
	}

	@Override
	public Object visit(VarDefinition obj, Object params) {
		obj.getType().accept(this, params);

		if(obj.getScope() == 0) { //Es global
			obj.setOffset(sumaGlobalesAnteriores);
			sumaGlobalesAnteriores += obj.getType().numberOfBytes();
		}else{ //Es local
			sumaLocalesAnteriores -= obj.getType().numberOfBytes();
			obj.setOffset(sumaLocalesAnteriores);
		}
		return null;
	}
}