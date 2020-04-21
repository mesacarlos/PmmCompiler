package parser;

public class LexerHelper {
	
	public static int lexemeToInt(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}

	public static char lexemeToChar(String text) {
		if(text.startsWith("'\\")){
			//Si empieza con barra puede ser \n o \t o \\ascii
			if(text.equals("'\\n'"))
				return '\n';
			if(text.equals("'\\t'"))
				return '\t';
			int codigoAscii = Integer.parseInt(text.replaceAll("\\D+", ""));
			return (char)codigoAscii;
		}else
			return text.charAt(1); //Si no empieza con \ es un caracter 'x'

		/*
		try {
			text = text.replace("'", "");

			if(text.equals("\\n"))
				return '\n';

			if(text.equals("\\t"))
				return '\t';

			if(Character.isDigit(text.charAt(text.length()-1)))
				text = text.replace("\\", "");
			try{
				int valor = Integer.parseInt(text);
				return (char)valor;
			}catch(Exception e) {
				return text.charAt(0);
			}
		}catch(NumberFormatException e) {
			System.out.println(e);
		}
		return (char)0;
		*/
	}

	public static Double lexemeToReal(String text) {
		try {
			return Double.parseDouble(text);
		}catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1.0;
	}
	
}
