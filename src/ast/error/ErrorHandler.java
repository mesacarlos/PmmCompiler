package ast.error;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {
	private List<ErrorType> errors;
	private static ErrorHandler instance;

	private ErrorHandler() {
		this.errors = new ArrayList<ErrorType>();
	}
	
	public static ErrorHandler getInstance() {
		if(instance == null)
			instance = new ErrorHandler();
		return instance;
	}
	
	public boolean hasErrors() {
		return !errors.isEmpty();
	}
	
	public void showErrors(PrintStream out) {
		for(ErrorType error : errors)
			out.println(error);
	}
	
	public void addError(ErrorType error) {
		errors.add(error);
	}
	
}