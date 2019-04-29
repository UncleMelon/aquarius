// Generated from /Users/matthew_wu/Documents/IdeaProjects/practice-based-learning/java-learning/src/main/java/antlr4/Tql.g4 by ANTLR 4.7
package antlr4;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TqlLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, AND=13, OR=14, GROUP=15, BY=16, MAX=17, SUM=18, 
		AVG=19, MIN=20, COUNT=21, DATE_HISTOGRAM=22, STARTOF=23, ENDOF=24, IN=25, 
		ID=26, TEXT_STRING=27, WS=28;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "AND", "OR", "GROUP", "BY", "MAX", "SUM", "AVG", 
		"MIN", "COUNT", "DATE_HISTOGRAM", "STARTOF", "ENDOF", "IN", "A", "B", 
		"C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", 
		"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "SC", "HEX_DIGIT", "DEC_DIGIT", 
		"LETTER", "ID", "TEXT_STRING", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "','", "'()'", "'&&'", "'||'", "'='", "'>'", "'<'", 
		"'<='", "'>='", "'~'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "AND", "OR", "GROUP", "BY", "MAX", "SUM", "AVG", "MIN", "COUNT", 
		"DATE_HISTOGRAM", "STARTOF", "ENDOF", "IN", "ID", "TEXT_STRING", "WS"
	};
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


	public TqlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Tql.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\36\u0127\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\3\2\3\2\3"+
		"\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35"+
		"\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3"+
		"\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61"+
		"\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39"+
		"\69\u0118\n9\r9\169\u0119\3:\6:\u011d\n:\r:\16:\u011e\3;\6;\u0122\n;\r"+
		";\16;\u0123\3;\3;\2\2<\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\2\67\29\2;\2=\2?\2A\2C\2E\2G\2I\2K\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\2_\2"+
		"a\2c\2e\2g\2i\2k\2m\2o\2q\34s\35u\36\3\2#\4\2CCcc\4\2DDdd\4\2EEee\4\2"+
		"FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4"+
		"\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWw"+
		"w\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\3\2aa\4\2\62;CH\3\2\62;\4"+
		"\2C\\c|\b\2&&//\62<C\\aac|\n\2))/\60\62<C\\aac|\u4e02\u9fa7\uf902\ufa2f"+
		"\5\2\13\f\17\17\"\"\2\u010b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3"+
		"\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37"+
		"\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3"+
		"\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2q\3\2\2\2\2s\3"+
		"\2\2\2\2u\3\2\2\2\3w\3\2\2\2\5y\3\2\2\2\7{\3\2\2\2\t}\3\2\2\2\13\u0080"+
		"\3\2\2\2\r\u0083\3\2\2\2\17\u0086\3\2\2\2\21\u0088\3\2\2\2\23\u008a\3"+
		"\2\2\2\25\u008c\3\2\2\2\27\u008f\3\2\2\2\31\u0092\3\2\2\2\33\u0094\3\2"+
		"\2\2\35\u0098\3\2\2\2\37\u009b\3\2\2\2!\u00a1\3\2\2\2#\u00a4\3\2\2\2%"+
		"\u00a8\3\2\2\2\'\u00ac\3\2\2\2)\u00b0\3\2\2\2+\u00b4\3\2\2\2-\u00ba\3"+
		"\2\2\2/\u00c9\3\2\2\2\61\u00d1\3\2\2\2\63\u00d7\3\2\2\2\65\u00da\3\2\2"+
		"\2\67\u00dc\3\2\2\29\u00de\3\2\2\2;\u00e0\3\2\2\2=\u00e2\3\2\2\2?\u00e4"+
		"\3\2\2\2A\u00e6\3\2\2\2C\u00e8\3\2\2\2E\u00ea\3\2\2\2G\u00ec\3\2\2\2I"+
		"\u00ee\3\2\2\2K\u00f0\3\2\2\2M\u00f2\3\2\2\2O\u00f4\3\2\2\2Q\u00f6\3\2"+
		"\2\2S\u00f8\3\2\2\2U\u00fa\3\2\2\2W\u00fc\3\2\2\2Y\u00fe\3\2\2\2[\u0100"+
		"\3\2\2\2]\u0102\3\2\2\2_\u0104\3\2\2\2a\u0106\3\2\2\2c\u0108\3\2\2\2e"+
		"\u010a\3\2\2\2g\u010c\3\2\2\2i\u010e\3\2\2\2k\u0110\3\2\2\2m\u0112\3\2"+
		"\2\2o\u0114\3\2\2\2q\u0117\3\2\2\2s\u011c\3\2\2\2u\u0121\3\2\2\2wx\7*"+
		"\2\2x\4\3\2\2\2yz\7+\2\2z\6\3\2\2\2{|\7.\2\2|\b\3\2\2\2}~\7*\2\2~\177"+
		"\7+\2\2\177\n\3\2\2\2\u0080\u0081\7(\2\2\u0081\u0082\7(\2\2\u0082\f\3"+
		"\2\2\2\u0083\u0084\7~\2\2\u0084\u0085\7~\2\2\u0085\16\3\2\2\2\u0086\u0087"+
		"\7?\2\2\u0087\20\3\2\2\2\u0088\u0089\7@\2\2\u0089\22\3\2\2\2\u008a\u008b"+
		"\7>\2\2\u008b\24\3\2\2\2\u008c\u008d\7>\2\2\u008d\u008e\7?\2\2\u008e\26"+
		"\3\2\2\2\u008f\u0090\7@\2\2\u0090\u0091\7?\2\2\u0091\30\3\2\2\2\u0092"+
		"\u0093\7\u0080\2\2\u0093\32\3\2\2\2\u0094\u0095\5\65\33\2\u0095\u0096"+
		"\5O(\2\u0096\u0097\5;\36\2\u0097\34\3\2\2\2\u0098\u0099\5Q)\2\u0099\u009a"+
		"\5W,\2\u009a\36\3\2\2\2\u009b\u009c\5A!\2\u009c\u009d\5W,\2\u009d\u009e"+
		"\5Q)\2\u009e\u009f\5]/\2\u009f\u00a0\5S*\2\u00a0 \3\2\2\2\u00a1\u00a2"+
		"\5\67\34\2\u00a2\u00a3\5e\63\2\u00a3\"\3\2\2\2\u00a4\u00a5\5M\'\2\u00a5"+
		"\u00a6\5\65\33\2\u00a6\u00a7\5c\62\2\u00a7$\3\2\2\2\u00a8\u00a9\5Y-\2"+
		"\u00a9\u00aa\5]/\2\u00aa\u00ab\5M\'\2\u00ab&\3\2\2\2\u00ac\u00ad\5\65"+
		"\33\2\u00ad\u00ae\5_\60\2\u00ae\u00af\5A!\2\u00af(\3\2\2\2\u00b0\u00b1"+
		"\5M\'\2\u00b1\u00b2\5E#\2\u00b2\u00b3\5O(\2\u00b3*\3\2\2\2\u00b4\u00b5"+
		"\59\35\2\u00b5\u00b6\5Q)\2\u00b6\u00b7\5]/\2\u00b7\u00b8\5O(\2\u00b8\u00b9"+
		"\5[.\2\u00b9,\3\2\2\2\u00ba\u00bb\5;\36\2\u00bb\u00bc\5\65\33\2\u00bc"+
		"\u00bd\5[.\2\u00bd\u00be\5=\37\2\u00be\u00bf\5i\65\2\u00bf\u00c0\5C\""+
		"\2\u00c0\u00c1\5E#\2\u00c1\u00c2\5Y-\2\u00c2\u00c3\5[.\2\u00c3\u00c4\5"+
		"Q)\2\u00c4\u00c5\5A!\2\u00c5\u00c6\5W,\2\u00c6\u00c7\5\65\33\2\u00c7\u00c8"+
		"\5M\'\2\u00c8.\3\2\2\2\u00c9\u00ca\5Y-\2\u00ca\u00cb\5[.\2\u00cb\u00cc"+
		"\5\65\33\2\u00cc\u00cd\5W,\2\u00cd\u00ce\5[.\2\u00ce\u00cf\5Q)\2\u00cf"+
		"\u00d0\5? \2\u00d0\60\3\2\2\2\u00d1\u00d2\5=\37\2\u00d2\u00d3\5O(\2\u00d3"+
		"\u00d4\5;\36\2\u00d4\u00d5\5Q)\2\u00d5\u00d6\5? \2\u00d6\62\3\2\2\2\u00d7"+
		"\u00d8\5E#\2\u00d8\u00d9\5O(\2\u00d9\64\3\2\2\2\u00da\u00db\t\2\2\2\u00db"+
		"\66\3\2\2\2\u00dc\u00dd\t\3\2\2\u00dd8\3\2\2\2\u00de\u00df\t\4\2\2\u00df"+
		":\3\2\2\2\u00e0\u00e1\t\5\2\2\u00e1<\3\2\2\2\u00e2\u00e3\t\6\2\2\u00e3"+
		">\3\2\2\2\u00e4\u00e5\t\7\2\2\u00e5@\3\2\2\2\u00e6\u00e7\t\b\2\2\u00e7"+
		"B\3\2\2\2\u00e8\u00e9\t\t\2\2\u00e9D\3\2\2\2\u00ea\u00eb\t\n\2\2\u00eb"+
		"F\3\2\2\2\u00ec\u00ed\t\13\2\2\u00edH\3\2\2\2\u00ee\u00ef\t\f\2\2\u00ef"+
		"J\3\2\2\2\u00f0\u00f1\t\r\2\2\u00f1L\3\2\2\2\u00f2\u00f3\t\16\2\2\u00f3"+
		"N\3\2\2\2\u00f4\u00f5\t\17\2\2\u00f5P\3\2\2\2\u00f6\u00f7\t\20\2\2\u00f7"+
		"R\3\2\2\2\u00f8\u00f9\t\21\2\2\u00f9T\3\2\2\2\u00fa\u00fb\t\22\2\2\u00fb"+
		"V\3\2\2\2\u00fc\u00fd\t\23\2\2\u00fdX\3\2\2\2\u00fe\u00ff\t\24\2\2\u00ff"+
		"Z\3\2\2\2\u0100\u0101\t\25\2\2\u0101\\\3\2\2\2\u0102\u0103\t\26\2\2\u0103"+
		"^\3\2\2\2\u0104\u0105\t\27\2\2\u0105`\3\2\2\2\u0106\u0107\t\30\2\2\u0107"+
		"b\3\2\2\2\u0108\u0109\t\31\2\2\u0109d\3\2\2\2\u010a\u010b\t\32\2\2\u010b"+
		"f\3\2\2\2\u010c\u010d\t\33\2\2\u010dh\3\2\2\2\u010e\u010f\t\34\2\2\u010f"+
		"j\3\2\2\2\u0110\u0111\t\35\2\2\u0111l\3\2\2\2\u0112\u0113\t\36\2\2\u0113"+
		"n\3\2\2\2\u0114\u0115\t\37\2\2\u0115p\3\2\2\2\u0116\u0118\t \2\2\u0117"+
		"\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2"+
		"\2\2\u011ar\3\2\2\2\u011b\u011d\t!\2\2\u011c\u011b\3\2\2\2\u011d\u011e"+
		"\3\2\2\2\u011e\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011ft\3\2\2\2\u0120"+
		"\u0122\t\"\2\2\u0121\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0121\3\2"+
		"\2\2\u0123\u0124\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\b;\2\2\u0126"+
		"v\3\2\2\2\6\2\u0119\u011e\u0123\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}