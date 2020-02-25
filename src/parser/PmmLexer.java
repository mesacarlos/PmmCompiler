// Generated from C:/Users/SrCha/Desktop/Proyectos/Pmm/src/parser\Pmm.g4 by ANTLR 4.8
package parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PmmLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TRASH=1, COMMENT=2, COMMENTML=3, REAL_CONSTANT=4, INT_CONSTANT=5, ID=6, 
		CHAR_CONSTANT=7;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"TRASH", "COMMENT", "COMMENTML", "ELEVADO", "REAL_CONSTANT", "INT_CONSTANT", 
			"ID", "CHAR_CONSTANT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TRASH", "COMMENT", "COMMENTML", "REAL_CONSTANT", "INT_CONSTANT", 
			"ID", "CHAR_CONSTANT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public PmmLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Pmm.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\t\u0090\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2"+
		"\3\2\3\3\3\3\7\3\32\n\3\f\3\16\3\35\13\3\3\3\5\3 \n\3\3\3\5\3#\n\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\7\4,\n\4\f\4\16\4/\13\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\5\59\n\5\3\5\6\5<\n\5\r\5\16\5=\3\5\3\5\5\5B\n\5\3\5\6\5E\n"+
		"\5\r\5\16\5F\5\5I\n\5\3\6\7\6L\n\6\f\6\16\6O\13\6\3\6\3\6\6\6S\n\6\r\6"+
		"\16\6T\3\6\5\6X\n\6\3\6\6\6[\n\6\r\6\16\6\\\3\6\3\6\7\6a\n\6\f\6\16\6"+
		"d\13\6\3\6\5\6g\n\6\3\6\6\6j\n\6\r\6\16\6k\3\6\5\6o\n\6\3\7\3\7\3\7\6"+
		"\7t\n\7\r\7\16\7u\5\7x\n\7\3\b\3\b\7\b|\n\b\f\b\16\b\177\13\b\3\t\3\t"+
		"\3\t\3\t\6\t\u0085\n\t\r\t\16\t\u0086\3\t\3\t\3\t\3\t\5\t\u008d\n\t\3"+
		"\t\3\t\4\33-\2\n\3\3\5\4\7\5\t\2\13\6\r\7\17\b\21\t\3\2\t\5\2\13\f\17"+
		"\17\"\"\3\3\f\f\4\2GGgg\3\2\62;\3\2\63;\5\2C\\aac|\6\2\62;C\\aac|\2\u00a6"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\3\23\3\2\2\2\5\27\3\2\2\2\7&\3\2\2\2\tH\3\2\2\2\13n"+
		"\3\2\2\2\rw\3\2\2\2\17y\3\2\2\2\21\u0080\3\2\2\2\23\24\t\2\2\2\24\25\3"+
		"\2\2\2\25\26\b\2\2\2\26\4\3\2\2\2\27\33\7%\2\2\30\32\13\2\2\2\31\30\3"+
		"\2\2\2\32\35\3\2\2\2\33\34\3\2\2\2\33\31\3\2\2\2\34\37\3\2\2\2\35\33\3"+
		"\2\2\2\36 \7\17\2\2\37\36\3\2\2\2\37 \3\2\2\2 \"\3\2\2\2!#\t\3\2\2\"!"+
		"\3\2\2\2#$\3\2\2\2$%\b\3\2\2%\6\3\2\2\2&\'\7$\2\2\'(\7$\2\2()\7$\2\2)"+
		"-\3\2\2\2*,\13\2\2\2+*\3\2\2\2,/\3\2\2\2-.\3\2\2\2-+\3\2\2\2.\60\3\2\2"+
		"\2/-\3\2\2\2\60\61\7$\2\2\61\62\7$\2\2\62\63\7$\2\2\63\64\3\2\2\2\64\65"+
		"\b\4\2\2\65\b\3\2\2\2\668\t\4\2\2\679\7/\2\28\67\3\2\2\289\3\2\2\29;\3"+
		"\2\2\2:<\t\5\2\2;:\3\2\2\2<=\3\2\2\2=;\3\2\2\2=>\3\2\2\2>I\3\2\2\2?A\t"+
		"\4\2\2@B\7-\2\2A@\3\2\2\2AB\3\2\2\2BD\3\2\2\2CE\t\5\2\2DC\3\2\2\2EF\3"+
		"\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2H\66\3\2\2\2H?\3\2\2\2I\n\3\2\2\2"+
		"JL\t\5\2\2KJ\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2NP\3\2\2\2OM\3\2\2\2"+
		"PR\7\60\2\2QS\t\5\2\2RQ\3\2\2\2ST\3\2\2\2TR\3\2\2\2TU\3\2\2\2UW\3\2\2"+
		"\2VX\5\t\5\2WV\3\2\2\2WX\3\2\2\2Xo\3\2\2\2Y[\t\5\2\2ZY\3\2\2\2[\\\3\2"+
		"\2\2\\Z\3\2\2\2\\]\3\2\2\2]^\3\2\2\2^b\7\60\2\2_a\t\5\2\2`_\3\2\2\2ad"+
		"\3\2\2\2b`\3\2\2\2bc\3\2\2\2cf\3\2\2\2db\3\2\2\2eg\5\t\5\2fe\3\2\2\2f"+
		"g\3\2\2\2go\3\2\2\2hj\t\5\2\2ih\3\2\2\2jk\3\2\2\2ki\3\2\2\2kl\3\2\2\2"+
		"lm\3\2\2\2mo\5\t\5\2nM\3\2\2\2nZ\3\2\2\2ni\3\2\2\2o\f\3\2\2\2px\7\62\2"+
		"\2qs\t\6\2\2rt\t\5\2\2sr\3\2\2\2tu\3\2\2\2us\3\2\2\2uv\3\2\2\2vx\3\2\2"+
		"\2wp\3\2\2\2wq\3\2\2\2x\16\3\2\2\2y}\t\7\2\2z|\t\b\2\2{z\3\2\2\2|\177"+
		"\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\20\3\2\2\2\177}\3\2\2\2\u0080\u008c\7)\2"+
		"\2\u0081\u008d\13\2\2\2\u0082\u0084\7^\2\2\u0083\u0085\t\5\2\2\u0084\u0083"+
		"\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\u008d\3\2\2\2\u0088\u0089\7^\2\2\u0089\u008d\7p\2\2\u008a\u008b\7^\2"+
		"\2\u008b\u008d\7v\2\2\u008c\u0081\3\2\2\2\u008c\u0082\3\2\2\2\u008c\u0088"+
		"\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\7)\2\2\u008f"+
		"\22\3\2\2\2\31\2\33\37\"-8=AFHMTW\\bfknuw}\u0086\u008c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}