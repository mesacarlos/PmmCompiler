import ast.error.ErrorHandler;
import ast.visitor.IdentificationVisitor;
import ast.visitor.TypeCheckingVisitor;
import ast.visitor.codegenerator.CodeGenerator;
import ast.visitor.codegenerator.ExecuteCGVisitor;
import ast.visitor.codegenerator.OffsetVisitor;
import parser.*;

import org.antlr.v4.runtime.*;

import ast.Program;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;

public class Main {
	
	public static void main(String... args) throws Exception {
		if (args.length<1) {
			System.err.println("Please, pass me the input file.");
		        return;
		}
		//args[0] = "labExamen.input.txt";
		// create a lexer that feeds off of input CharStream
		CharStream input = CharStreams.fromFileName(args[0]);
		PmmLexer lexer = new PmmLexer(input);

		// create a parser that feeds off the tokens buffer
		CommonTokenStream tokens = new CommonTokenStream(lexer); 
		PmmParser parser = new PmmParser(tokens);	
		Program ast = parser.program().ast;

		//Visitors
		ast.accept(new IdentificationVisitor(), null);
		ast.accept(new TypeCheckingVisitor(), null);

		//Si hay errores en este punto, paramos. Si no, generamos codigo
		if(ErrorHandler.getInstance().hasErrors()){
			ErrorHandler.getInstance().showErrors(System.err);
			return;
		}

		ast.accept(new OffsetVisitor(), null);

		//Generacion de código
		CodeGenerator cg = new CodeGenerator("output.txt", args[0]);
		ast.accept(new ExecuteCGVisitor(cg), null);
		
		// * Check errors 
		if(ErrorHandler.getInstance().hasErrors()){
			// * Show errors
			ErrorHandler.getInstance().showErrors(System.err);
		}else{
			// * The AST is shown
			IntrospectorModel model=new IntrospectorModel("Program", ast);
			new IntrospectorTree("Introspector", model);
		}		
	}
}
