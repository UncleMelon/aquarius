// Generated from /Users/matthew_wu/Documents/IdeaProjects/aquarius/programming/src/main/java/antlr4/Tql.g4 by ANTLR 4.7
package antlr4.tql;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TqlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, AND=13, OR=14, GROUP=15, BY=16, MAX=17, SUM=18, 
		AVG=19, MIN=20, COUNT=21, DATE_HISTOGRAM=22, STARTOF=23, ENDOF=24, IN=25, 
		ID=26, L_S_STRING=27, WS=28;
	public static final int
		RULE_columnName = 0, RULE_stat = 1, RULE_whereClause = 2, RULE_logicExpression = 3, 
		RULE_groupByClause = 4, RULE_byCaluse = 5, RULE_groupByItem = 6, RULE_aggregateWindowedFunction = 7, 
		RULE_logicOperator = 8, RULE_comparisonOperator = 9, RULE_value = 10, 
		RULE_fullColumnName = 11, RULE_functionArg = 12, RULE_field = 13, RULE_interval = 14;
	public static final String[] ruleNames = {
		"columnName", "stat", "whereClause", "logicExpression", "groupByClause", 
		"byCaluse", "groupByItem", "aggregateWindowedFunction", "logicOperator", 
		"comparisonOperator", "value", "fullColumnName", "functionArg", "field", 
		"interval"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "','", "'()'", "'&&'", "'||'", "'='", "'>'", "'<'", 
		"'<='", "'>='", "'~'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "AND", "OR", "GROUP", "BY", "MAX", "SUM", "AVG", "MIN", "COUNT", 
		"DATE_HISTOGRAM", "STARTOF", "ENDOF", "IN", "ID", "L_S_STRING", "WS"
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

	@Override
	public String getGrammarFileName() { return "Tql.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TqlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ColumnNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TqlParser.ID, 0); }
		public ColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterColumnName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitColumnName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnNameContext columnName() throws RecognitionException {
		ColumnNameContext _localctx = new ColumnNameContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_columnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public GroupByClauseContext groupByClause() {
			return getRuleContext(GroupByClauseContext.class,0);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0 || _la==ID) {
				{
				setState(32);
				whereClause();
				}
			}

			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GROUP) {
				{
				setState(35);
				groupByClause();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhereClauseContext extends ParserRuleContext {
		public LogicExpressionContext logicExpression() {
			return getRuleContext(LogicExpressionContext.class,0);
		}
		public WhereClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterWhereClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitWhereClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitWhereClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereClauseContext whereClause() throws RecognitionException {
		WhereClauseContext _localctx = new WhereClauseContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_whereClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			logicExpression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicExpressionContext extends ParserRuleContext {
		public LogicExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicExpression; }
	 
		public LogicExpressionContext() { }
		public void copyFrom(LogicExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParensContext extends LogicExpressionContext {
		public LogicExpressionContext logicExpression() {
			return getRuleContext(LogicExpressionContext.class,0);
		}
		public ParensContext(LogicExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterParens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitParens(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitParens(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicOperationContext extends LogicExpressionContext {
		public List<LogicExpressionContext> logicExpression() {
			return getRuleContexts(LogicExpressionContext.class);
		}
		public LogicExpressionContext logicExpression(int i) {
			return getRuleContext(LogicExpressionContext.class,i);
		}
		public LogicOperatorContext logicOperator() {
			return getRuleContext(LogicOperatorContext.class,0);
		}
		public LogicOperationContext(LogicExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterLogicOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitLogicOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitLogicOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InOperationContext extends LogicExpressionContext {
		public FullColumnNameContext fullColumnName() {
			return getRuleContext(FullColumnNameContext.class,0);
		}
		public TerminalNode IN() { return getToken(TqlParser.IN, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public InOperationContext(LogicExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterInOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitInOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitInOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ComparisonOperationContext extends LogicExpressionContext {
		public FullColumnNameContext fullColumnName() {
			return getRuleContext(FullColumnNameContext.class,0);
		}
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ComparisonOperationContext(LogicExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterComparisonOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitComparisonOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitComparisonOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicExpressionContext logicExpression() throws RecognitionException {
		return logicExpression(0);
	}

	private LogicExpressionContext logicExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LogicExpressionContext _localctx = new LogicExpressionContext(_ctx, _parentState);
		LogicExpressionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_logicExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				_localctx = new ComparisonOperationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(41);
				fullColumnName();
				setState(42);
				comparisonOperator();
				setState(43);
				value();
				}
				break;
			case 2:
				{
				_localctx = new ParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(45);
				match(T__0);
				setState(46);
				logicExpression(0);
				setState(47);
				match(T__1);
				}
				break;
			case 3:
				{
				_localctx = new InOperationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(49);
				fullColumnName();
				setState(50);
				match(IN);
				setState(51);
				match(T__0);
				setState(52);
				value();
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(53);
					match(T__2);
					setState(54);
					value();
					}
					}
					setState(59);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(60);
				match(T__1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(70);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LogicOperationContext(new LogicExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_logicExpression);
					setState(64);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(65);
					logicOperator();
					setState(66);
					logicExpression(5);
					}
					} 
				}
				setState(72);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class GroupByClauseContext extends ParserRuleContext {
		public TerminalNode GROUP() { return getToken(TqlParser.GROUP, 0); }
		public ByCaluseContext byCaluse() {
			return getRuleContext(ByCaluseContext.class,0);
		}
		public AggregateWindowedFunctionContext aggregateWindowedFunction() {
			return getRuleContext(AggregateWindowedFunctionContext.class,0);
		}
		public GroupByClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupByClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterGroupByClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitGroupByClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitGroupByClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupByClauseContext groupByClause() throws RecognitionException {
		GroupByClauseContext _localctx = new GroupByClauseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_groupByClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(GROUP);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MAX) | (1L << SUM) | (1L << AVG) | (1L << MIN) | (1L << COUNT))) != 0)) {
				{
				setState(74);
				aggregateWindowedFunction();
				}
			}

			setState(77);
			byCaluse();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ByCaluseContext extends ParserRuleContext {
		public TerminalNode BY() { return getToken(TqlParser.BY, 0); }
		public List<GroupByItemContext> groupByItem() {
			return getRuleContexts(GroupByItemContext.class);
		}
		public GroupByItemContext groupByItem(int i) {
			return getRuleContext(GroupByItemContext.class,i);
		}
		public ByCaluseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_byCaluse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterByCaluse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitByCaluse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitByCaluse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ByCaluseContext byCaluse() throws RecognitionException {
		ByCaluseContext _localctx = new ByCaluseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_byCaluse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(BY);
			setState(80);
			groupByItem();
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(81);
				match(T__2);
				setState(82);
				groupByItem();
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GroupByItemContext extends ParserRuleContext {
		public FullColumnNameContext fullColumnName() {
			return getRuleContext(FullColumnNameContext.class,0);
		}
		public TerminalNode DATE_HISTOGRAM() { return getToken(TqlParser.DATE_HISTOGRAM, 0); }
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public IntervalContext interval() {
			return getRuleContext(IntervalContext.class,0);
		}
		public GroupByItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupByItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterGroupByItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitGroupByItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitGroupByItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupByItemContext groupByItem() throws RecognitionException {
		GroupByItemContext _localctx = new GroupByItemContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_groupByItem);
		try {
			setState(96);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				fullColumnName();
				}
				break;
			case DATE_HISTOGRAM:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				match(DATE_HISTOGRAM);
				setState(90);
				match(T__0);
				setState(91);
				field();
				setState(92);
				match(T__2);
				setState(93);
				interval();
				setState(94);
				match(T__1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AggregateWindowedFunctionContext extends ParserRuleContext {
		public Token agg;
		public FunctionArgContext functionArg() {
			return getRuleContext(FunctionArgContext.class,0);
		}
		public TerminalNode AVG() { return getToken(TqlParser.AVG, 0); }
		public TerminalNode MAX() { return getToken(TqlParser.MAX, 0); }
		public TerminalNode MIN() { return getToken(TqlParser.MIN, 0); }
		public TerminalNode SUM() { return getToken(TqlParser.SUM, 0); }
		public TerminalNode COUNT() { return getToken(TqlParser.COUNT, 0); }
		public AggregateWindowedFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregateWindowedFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterAggregateWindowedFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitAggregateWindowedFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitAggregateWindowedFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AggregateWindowedFunctionContext aggregateWindowedFunction() throws RecognitionException {
		AggregateWindowedFunctionContext _localctx = new AggregateWindowedFunctionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_aggregateWindowedFunction);
		int _la;
		try {
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MAX:
			case SUM:
			case AVG:
			case MIN:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				((AggregateWindowedFunctionContext)_localctx).agg = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MAX) | (1L << SUM) | (1L << AVG) | (1L << MIN))) != 0)) ) {
					((AggregateWindowedFunctionContext)_localctx).agg = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(99);
				match(T__0);
				setState(100);
				functionArg();
				setState(101);
				match(T__1);
				}
				break;
			case COUNT:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				((AggregateWindowedFunctionContext)_localctx).agg = match(COUNT);
				setState(104);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicOperatorContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(TqlParser.AND, 0); }
		public TerminalNode OR() { return getToken(TqlParser.OR, 0); }
		public LogicOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterLogicOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitLogicOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitLogicOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicOperatorContext logicOperator() throws RecognitionException {
		LogicOperatorContext _localctx = new LogicOperatorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_logicOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << AND) | (1L << OR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparisonOperatorContext extends ParserRuleContext {
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitComparisonOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitComparisonOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	 
		public ValueContext() { }
		public void copyFrom(ValueContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ValueFunctionContext extends ValueContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public ValueFunctionContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterValueFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitValueFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitValueFunction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnicodeValueContext extends ValueContext {
		public TerminalNode L_S_STRING() { return getToken(TqlParser.L_S_STRING, 0); }
		public UnicodeValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterUnicodeValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitUnicodeValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitUnicodeValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AsciiValueContext extends ValueContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public AsciiValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterAsciiValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitAsciiValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitAsciiValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TimeOfFunctionContext extends ValueContext {
		public Token tf;
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public TerminalNode STARTOF() { return getToken(TqlParser.STARTOF, 0); }
		public TerminalNode ENDOF() { return getToken(TqlParser.ENDOF, 0); }
		public TimeOfFunctionContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterTimeOfFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitTimeOfFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitTimeOfFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_value);
		int _la;
		try {
			setState(125);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new ValueFunctionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				columnName();
				setState(112);
				match(T__3);
				}
				break;
			case 2:
				_localctx = new TimeOfFunctionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				((TimeOfFunctionContext)_localctx).tf = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==STARTOF || _la==ENDOF) ) {
					((TimeOfFunctionContext)_localctx).tf = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(115);
				match(T__0);
				setState(116);
				columnName();
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(117);
					match(T__2);
					setState(118);
					columnName();
					}
				}

				setState(121);
				match(T__1);
				}
				break;
			case 3:
				_localctx = new AsciiValueContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(123);
				columnName();
				}
				break;
			case 4:
				_localctx = new UnicodeValueContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(124);
				match(L_S_STRING);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FullColumnNameContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public FullColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fullColumnName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterFullColumnName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitFullColumnName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitFullColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FullColumnNameContext fullColumnName() throws RecognitionException {
		FullColumnNameContext _localctx = new FullColumnNameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_fullColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			columnName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionArgContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public FunctionArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterFunctionArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitFunctionArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitFunctionArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionArgContext functionArg() throws RecognitionException {
		FunctionArgContext _localctx = new FunctionArgContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_functionArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			columnName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			columnName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntervalContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public IntervalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interval; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).enterInterval(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TqlListener ) ((TqlListener)listener).exitInterval(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TqlVisitor ) return ((TqlVisitor<? extends T>)visitor).visitInterval(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntervalContext interval() throws RecognitionException {
		IntervalContext _localctx = new IntervalContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_interval);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			columnName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return logicExpression_sempred((LogicExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean logicExpression_sempred(LogicExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36\u008a\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\5\3$\n"+
		"\3\3\3\5\3\'\n\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\7\5:\n\5\f\5\16\5=\13\5\3\5\3\5\5\5A\n\5\3\5\3\5\3\5\3"+
		"\5\7\5G\n\5\f\5\16\5J\13\5\3\6\3\6\5\6N\n\6\3\6\3\6\3\7\3\7\3\7\3\7\7"+
		"\7V\n\7\f\7\16\7Y\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bc\n\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\5\tl\n\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\5\fz\n\f\3\f\3\f\3\f\3\f\5\f\u0080\n\f\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\20\2\3\b\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36\2\6\3\2\23\26\4\2\7\b\17\20\3\2\t\16\3\2\31\32\2\u0088\2 \3\2\2\2"+
		"\4#\3\2\2\2\6(\3\2\2\2\b@\3\2\2\2\nK\3\2\2\2\fQ\3\2\2\2\16b\3\2\2\2\20"+
		"k\3\2\2\2\22m\3\2\2\2\24o\3\2\2\2\26\177\3\2\2\2\30\u0081\3\2\2\2\32\u0083"+
		"\3\2\2\2\34\u0085\3\2\2\2\36\u0087\3\2\2\2 !\7\34\2\2!\3\3\2\2\2\"$\5"+
		"\6\4\2#\"\3\2\2\2#$\3\2\2\2$&\3\2\2\2%\'\5\n\6\2&%\3\2\2\2&\'\3\2\2\2"+
		"\'\5\3\2\2\2()\5\b\5\2)\7\3\2\2\2*+\b\5\1\2+,\5\30\r\2,-\5\24\13\2-.\5"+
		"\26\f\2.A\3\2\2\2/\60\7\3\2\2\60\61\5\b\5\2\61\62\7\4\2\2\62A\3\2\2\2"+
		"\63\64\5\30\r\2\64\65\7\33\2\2\65\66\7\3\2\2\66;\5\26\f\2\678\7\5\2\2"+
		"8:\5\26\f\29\67\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<>\3\2\2\2=;\3\2"+
		"\2\2>?\7\4\2\2?A\3\2\2\2@*\3\2\2\2@/\3\2\2\2@\63\3\2\2\2AH\3\2\2\2BC\f"+
		"\6\2\2CD\5\22\n\2DE\5\b\5\7EG\3\2\2\2FB\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI"+
		"\3\2\2\2I\t\3\2\2\2JH\3\2\2\2KM\7\21\2\2LN\5\20\t\2ML\3\2\2\2MN\3\2\2"+
		"\2NO\3\2\2\2OP\5\f\7\2P\13\3\2\2\2QR\7\22\2\2RW\5\16\b\2ST\7\5\2\2TV\5"+
		"\16\b\2US\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2X\r\3\2\2\2YW\3\2\2\2Z"+
		"c\5\30\r\2[\\\7\30\2\2\\]\7\3\2\2]^\5\34\17\2^_\7\5\2\2_`\5\36\20\2`a"+
		"\7\4\2\2ac\3\2\2\2bZ\3\2\2\2b[\3\2\2\2c\17\3\2\2\2de\t\2\2\2ef\7\3\2\2"+
		"fg\5\32\16\2gh\7\4\2\2hl\3\2\2\2ij\7\27\2\2jl\7\6\2\2kd\3\2\2\2ki\3\2"+
		"\2\2l\21\3\2\2\2mn\t\3\2\2n\23\3\2\2\2op\t\4\2\2p\25\3\2\2\2qr\5\2\2\2"+
		"rs\7\6\2\2s\u0080\3\2\2\2tu\t\5\2\2uv\7\3\2\2vy\5\2\2\2wx\7\5\2\2xz\5"+
		"\2\2\2yw\3\2\2\2yz\3\2\2\2z{\3\2\2\2{|\7\4\2\2|\u0080\3\2\2\2}\u0080\5"+
		"\2\2\2~\u0080\7\35\2\2\177q\3\2\2\2\177t\3\2\2\2\177}\3\2\2\2\177~\3\2"+
		"\2\2\u0080\27\3\2\2\2\u0081\u0082\5\2\2\2\u0082\31\3\2\2\2\u0083\u0084"+
		"\5\2\2\2\u0084\33\3\2\2\2\u0085\u0086\5\2\2\2\u0086\35\3\2\2\2\u0087\u0088"+
		"\5\2\2\2\u0088\37\3\2\2\2\r#&;@HMWbky\177";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}