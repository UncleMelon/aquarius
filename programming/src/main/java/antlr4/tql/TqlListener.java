// Generated from /Users/matthew_wu/Documents/IdeaProjects/aquarius/programming/src/main/java/antlr4/Tql.g4 by ANTLR 4.7
package antlr4.tql;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TqlParser}.
 */
public interface TqlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TqlParser#columnName}.
	 * @param ctx the parse tree
	 */
	void enterColumnName(TqlParser.ColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TqlParser#columnName}.
	 * @param ctx the parse tree
	 */
	void exitColumnName(TqlParser.ColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link TqlParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(TqlParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link TqlParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(TqlParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link TqlParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(TqlParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link TqlParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(TqlParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parens}
	 * labeled alternative in {@link TqlParser#logicExpression}.
	 * @param ctx the parse tree
	 */
	void enterParens(TqlParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link TqlParser#logicExpression}.
	 * @param ctx the parse tree
	 */
	void exitParens(TqlParser.ParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicOperation}
	 * labeled alternative in {@link TqlParser#logicExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicOperation(TqlParser.LogicOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicOperation}
	 * labeled alternative in {@link TqlParser#logicExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicOperation(TqlParser.LogicOperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inOperation}
	 * labeled alternative in {@link TqlParser#logicExpression}.
	 * @param ctx the parse tree
	 */
	void enterInOperation(TqlParser.InOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inOperation}
	 * labeled alternative in {@link TqlParser#logicExpression}.
	 * @param ctx the parse tree
	 */
	void exitInOperation(TqlParser.InOperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparisonOperation}
	 * labeled alternative in {@link TqlParser#logicExpression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperation(TqlParser.ComparisonOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparisonOperation}
	 * labeled alternative in {@link TqlParser#logicExpression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperation(TqlParser.ComparisonOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TqlParser#groupByClause}.
	 * @param ctx the parse tree
	 */
	void enterGroupByClause(TqlParser.GroupByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link TqlParser#groupByClause}.
	 * @param ctx the parse tree
	 */
	void exitGroupByClause(TqlParser.GroupByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link TqlParser#byCaluse}.
	 * @param ctx the parse tree
	 */
	void enterByCaluse(TqlParser.ByCaluseContext ctx);
	/**
	 * Exit a parse tree produced by {@link TqlParser#byCaluse}.
	 * @param ctx the parse tree
	 */
	void exitByCaluse(TqlParser.ByCaluseContext ctx);
	/**
	 * Enter a parse tree produced by {@link TqlParser#groupByItem}.
	 * @param ctx the parse tree
	 */
	void enterGroupByItem(TqlParser.GroupByItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link TqlParser#groupByItem}.
	 * @param ctx the parse tree
	 */
	void exitGroupByItem(TqlParser.GroupByItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link TqlParser#aggregateWindowedFunction}.
	 * @param ctx the parse tree
	 */
	void enterAggregateWindowedFunction(TqlParser.AggregateWindowedFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TqlParser#aggregateWindowedFunction}.
	 * @param ctx the parse tree
	 */
	void exitAggregateWindowedFunction(TqlParser.AggregateWindowedFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TqlParser#logicOperator}.
	 * @param ctx the parse tree
	 */
	void enterLogicOperator(TqlParser.LogicOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TqlParser#logicOperator}.
	 * @param ctx the parse tree
	 */
	void exitLogicOperator(TqlParser.LogicOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(TqlParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(TqlParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valueFunction}
	 * labeled alternative in {@link TqlParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValueFunction(TqlParser.ValueFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valueFunction}
	 * labeled alternative in {@link TqlParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValueFunction(TqlParser.ValueFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code timeOfFunction}
	 * labeled alternative in {@link TqlParser#value}.
	 * @param ctx the parse tree
	 */
	void enterTimeOfFunction(TqlParser.TimeOfFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code timeOfFunction}
	 * labeled alternative in {@link TqlParser#value}.
	 * @param ctx the parse tree
	 */
	void exitTimeOfFunction(TqlParser.TimeOfFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code asciiValue}
	 * labeled alternative in {@link TqlParser#value}.
	 * @param ctx the parse tree
	 */
	void enterAsciiValue(TqlParser.AsciiValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code asciiValue}
	 * labeled alternative in {@link TqlParser#value}.
	 * @param ctx the parse tree
	 */
	void exitAsciiValue(TqlParser.AsciiValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unicodeValue}
	 * labeled alternative in {@link TqlParser#value}.
	 * @param ctx the parse tree
	 */
	void enterUnicodeValue(TqlParser.UnicodeValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unicodeValue}
	 * labeled alternative in {@link TqlParser#value}.
	 * @param ctx the parse tree
	 */
	void exitUnicodeValue(TqlParser.UnicodeValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TqlParser#fullColumnName}.
	 * @param ctx the parse tree
	 */
	void enterFullColumnName(TqlParser.FullColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TqlParser#fullColumnName}.
	 * @param ctx the parse tree
	 */
	void exitFullColumnName(TqlParser.FullColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link TqlParser#functionArg}.
	 * @param ctx the parse tree
	 */
	void enterFunctionArg(TqlParser.FunctionArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link TqlParser#functionArg}.
	 * @param ctx the parse tree
	 */
	void exitFunctionArg(TqlParser.FunctionArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link TqlParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(TqlParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link TqlParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(TqlParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link TqlParser#interval}.
	 * @param ctx the parse tree
	 */
	void enterInterval(TqlParser.IntervalContext ctx);
	/**
	 * Exit a parse tree produced by {@link TqlParser#interval}.
	 * @param ctx the parse tree
	 */
	void exitInterval(TqlParser.IntervalContext ctx);
}