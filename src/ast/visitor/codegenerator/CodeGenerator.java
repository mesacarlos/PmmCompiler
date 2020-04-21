package ast.visitor.codegenerator;

import ast.*;
import ast.Integer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CodeGenerator {
	private PrintWriter out;
	private int label = 0;

	public CodeGenerator(String outputFilename, String sourceFilename) {
		try {
			out = new PrintWriter(outputFilename);
			source(sourceFilename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void comment(String comment) {
		out.println("'" + comment);
		out.flush();
	}

	/***************************
	 * PUSH INSTRUCTIONS *
	 ***************************/
	public void push(Type type, int cons) {
		out.println("\tpush" + type.suffix() + "\t" + cons);
		out.flush();
	}

	public void push(Type type, double cons) {
		out.println("\tpush" + type.suffix() + "\t" + cons);
		out.flush();
	}

	public void pusha(int addr) {
		out.println("\tpusha\t" + addr);
		out.flush();
	}

	public void pushBP() {
		out.println("\tpush\t" + "bp");
		out.flush();
	}

	/***************************
	 * LOAD INSTRUCTIONS *
	 ***************************/
	public void load(Type type) {
		out.println("\tload" + type.suffix());
		out.flush();
	}

	public void store(Type type) {
		out.println("\tstore" + type.suffix());
		out.flush();
	}

	/***************************
	 * POP AND DUP *
	 ***************************/
	public void pop(Type type) {
		out.println("\tpop" + type.suffix());
		out.flush();
	}

	public void dup(Type type) {
		out.println("\tdup" + type.suffix());
		out.flush();
	}

	/***************************
	 * ARITHMETIC *
	 ***************************/
	public void add(Type type) { // No debe recibir un char
		out.println("\tadd" + type.suffix());
		out.flush();
	}

	public void sub(Type type) { // No debe recibir un char
		out.println("\tsub" + type.suffix());
		out.flush();
	}

	public void mul(Type type) { // No debe recibir un char
		out.println("\tmul" + type.suffix());
		out.flush();
	}

	public void div(Type type) { // No debe recibir un char
		out.println("\tdiv" + type.suffix());
		out.flush();
	}

	public void mod(Type type) { // No debe recibir un char
		out.println("\tmod" + type.suffix());
		out.flush();
	}

	/***************************
	 * COMPARISON *
	 ***************************/
	public void gt(Type type) { // No debe recibir un char
		out.println("\tgt" + type.suffix());
		out.flush();
	}

	public void lt(Type type) { // No debe recibir un char
		out.println("\tlt" + type.suffix());
		out.flush();
	}

	public void ge(Type type) { // No debe recibir un char
		out.println("\tge" + type.suffix());
		out.flush();
	}

	public void le(Type type) { // No debe recibir un char
		out.println("\tle" + type.suffix());
		out.flush();
	}

	public void eq(Type type) { // No debe recibir un char
		out.println("\teq" + type.suffix());
		out.flush();
	}

	public void ne(Type type) { // No debe recibir un char
		out.println("\tne" + type.suffix());
		out.flush();
	}

	/***************************
	 * LOGICAL *
	 ***************************/
	public void and() {
		out.println("\tand");
		out.flush();
	}

	public void or() {
		out.println("\tor");
		out.flush();
	}

	public void not() {
		out.println("\tnot");
		out.flush();
	}

	/***************************
	 * INPUT/OUTPUT *
	 ***************************/
	public void out(Type type) {
		out.println("\tout" + type.suffix());
		out.flush();
	}

	public void in(Type type) {
		out.println("\tin" + type.suffix());
		out.flush();
	}

	/***************************
	 * CONVERSIONS *
	 ***************************/
	public void b2i() {
		out.println("\tb2i");
		out.flush();
	}

	public void i2f() {
		out.println("\ti2f");
		out.flush();
	}

	public void f2i() {
		out.println("\tf2i");
		out.flush();
	}

	public void i2b() {
		out.println("\ti2b");
		out.flush();
	}

	/***************************
	 * JUMPS *
	 ***************************/
	public void label(String id) {
		out.println("\t" + id + ":");
		out.flush();
	}

	public void jmp(String label) {
		out.println("\tjmp\t" + label);
		out.flush();
	}

	public void jz(String label) {
		out.println("\tjz\t" + label);
		out.flush();
	}

	public void jnz(String label) {
		out.println("\tjnz\t" + label);
		out.flush();
	}

	/***************************
	 * FUNCTIONS *
	 ***************************/
	public void call(String label) {
		out.println("\tcall\t" + label);
		out.flush();
	}

	public void enter(int space) {
		out.println("\tenter\t" + space);
		out.flush();
	}

	public void ret(int bytesToReturn, int localVariablesBytes, int paramsBytes) {
		out.println("\tret\t" + bytesToReturn + ", " + localVariablesBytes + ", " + paramsBytes);
		out.flush();
	}

	public void halt() {
		out.println("\thalt");
		out.flush();
	}

	/***************************
	 * DEBUG *
	 ***************************/
	public void source(String fileName) {
		out.println("#source \"" + fileName + "\"");
		out.flush();
	}

	public void line(int line) {
		out.println("#line " + line);
		out.flush();
	}

	/***************************
	 * EXTRA FUNCTIONS *
	 ***************************/
	public void mainInvocation() {
		call("main");
		halt();
	}

	//MAPL solo soporta 4 conversiones. Para poder hacerlas todas, se debe convertir primero a Entero y Luego al tipo requerido
	public void convertTo(Type of, Type a) {
		if (of.equals(a)) // if(of.equivalent(a))
			return;
		if (of instanceof Char) {
			out.println("\tb2i");
			if (a instanceof Real)
				out.println("\ti2f");
		}
		if (of instanceof Integer) {
			if (a instanceof Real)
				out.println("\ti2f");
			else if (a instanceof Char)
				out.println("\ti2b");
		}
		if (of instanceof Real) {
			out.println("\tf2i");
			if (a instanceof Char)
				out.println("\ti2b");
		}
		out.flush();
	}

	public void arithmetic(String operador, Type type) {
		switch (operador) {
			case "+":
				add(type);
				break;
			case "-":
				sub(type);
				break;
			case "*":
				mul(type);
				break;
			case "/":
				div(type);
				break;
			case "%":
				mod(type);
				break;
		}
	}

	public void logic(String operador) {
		switch (operador) {
			case "&&":
				and();
				break;
			case "||":
				or();
				break;
		}
	}

	public void comparison(String operador, Type type) {
		switch (operador) {
			case ">":
				gt(type);
				break;
			case ">=":
				ge(type);
				break;
			case "<":
				lt(type);
				break;
			case "<=":
				le(type);
				break;
			case "!=":
				ne(type);
				break;
			case "==":
				eq(type);
				break;
		}
	}

	public int getLabel() {
		label++;
		return label;
	}
}