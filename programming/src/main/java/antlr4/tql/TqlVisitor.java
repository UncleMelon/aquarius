// Generated from /Users/matthew_wu/Documents/IdeaProjects/aquarius/programming/src/main/java/antlr4/Tql.g4 by ANTLR 4.7
package antlr4.tql;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TqlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TqlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TqlParser#columnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnName(TqlParser.ColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link TqlParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(TqlParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link TqlParser#whereClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereClause(TqlParser.WhereClauseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link TqlParser#logicExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(TqlParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicOperation}
	 * labeled alternative in {@link TqlParser#logicExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicOperation(TqlParser.LogicOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inOperation}
	 * labeled alternative in {@link TqlParser#logicExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInOperation(TqlParser.InOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonOperation}
	 * labeled alternative in {@link TqlParser#logicExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperation(TqlParser.ComparisonOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link TqlParser#groupByClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupByClause(TqlParser.GroupByClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link TqlParser#byCaluse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitByCaluse(TqlParser.ByCaluseContext ctx);
	/**
	 * Visit a parse tree produced by {@link TqlParser#groupByItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupByItem(TqlParser.GroupByItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link TqlParser#aggregateWindowedFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregateWindowedFunction(TqlParser.AggregateWindowedFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TqlParser#logicOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicOperator(TqlParser.LogicOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link TqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(TqlParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valueFunction}
	 * labeled alternative in {@link TqlParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueFunction(TqlParser.ValueFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code timeOfFunction}
	 * labeled alternative in {@link TqlParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimeOfFunction(TqlParser.TimeOfFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code asciiValue}
	 * labeled alternative in {@link TqlParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsciiValue(TqlParser.AsciiValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unicodeValue}
	 * labeled alternative in {@link TqlParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnicodeValue(TqlParser.UnicodeValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TqlParser#fullColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullColumnName(TqlParser.FullColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link TqlParser#functionArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionArg(TqlParser.FunctionArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link TqlParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(TqlParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link TqlParser#interval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterval(TqlParser.IntervalContext ctx);
}