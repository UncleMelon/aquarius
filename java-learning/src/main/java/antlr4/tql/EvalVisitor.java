package antlr4.tql;

import java.util.ArrayList;
import java.util.List;

public class EvalVisitor extends TqlBaseVisitor {

    List<Filter> filters = new ArrayList<>();

    @Override
    public Object visitWhereClause(TqlParser.WhereClauseContext ctx) {
        visit(ctx.logicExpression());
        return filters;
    }

    @Override
    public Object visitParens(TqlParser.ParensContext ctx) {
        visit(ctx.logicExpression());
        return null;
    }

    @Override
    public Object visitLogicOperation(TqlParser.LogicOperationContext ctx) {
        visit(ctx.logicExpression(0));
        visit(ctx.logicExpression(1));
        return null;
    }

    @Override
    public Object visitComparisonOperation(TqlParser.ComparisonOperationContext ctx) {
        Object column = visit(ctx.fullColumnName());
        Object value = visit(ctx.value());
        Object op = visit(ctx.comparisonOperator());
        Filter filter = new Filter();
        filter.setColumn(column.toString());
        filter.setOp(op.toString());
        filter.setRefData(value.toString());
        filters.add(filter);
        return null;
    }

    @Override
    public Object visitLogicOperator(TqlParser.LogicOperatorContext ctx) {
        return ctx.getText();
    }

    @Override
    public Object visitComparisonOperator(TqlParser.ComparisonOperatorContext ctx) {
        return ctx.getText();
    }

    @Override
    public Object visitFullColumnName(TqlParser.FullColumnNameContext ctx) {
        return ctx.columnName().ID().getText();
    }

    @Override
    public Object visitField(TqlParser.FieldContext ctx) {
        return ctx.columnName().ID().getText();
    }

    @Override
    public Object visitInterval(TqlParser.IntervalContext ctx) {
        return ctx.columnName().ID().getText();
    }

    @Override
    public Object visitFunctionArg(TqlParser.FunctionArgContext ctx) {
        return ctx.columnName().ID().getText();
    }

    @Override
    public Object visitAggregateWindowedFunction(TqlParser.AggregateWindowedFunctionContext ctx) {
        String aggOperator = ctx.agg.getText();
        System.out.println(aggOperator);
        if (ctx.functionArg() != null) {
            Object tt = visit(ctx.functionArg());
            System.out.println((tt == null)? "functionArg is null": ("functionArg is not null" + tt));
        }
        return null;
    }

    @Override
    public Object visitGroupByItem(TqlParser.GroupByItemContext ctx) {
        if (ctx.fullColumnName() != null)  {
            Object tt = visit(ctx.fullColumnName());
            System.out.println((tt == null)? "fullColumnName is null": ("fullColumnName is not null" + tt));
        } else {
            Object field = visit(ctx.field());
            Object interval = visit(ctx.interval());
            System.out.println((field == null)? "field is null": ("field is not null" + field));
            System.out.println((interval == null)? "interval is null": ("interval is not null" + interval));
        }
        return null;
    }

    @Override
    public Object visitByCaluse(TqlParser.ByCaluseContext ctx) {
        visit(ctx.groupByItem(0));
        visit(ctx.groupByItem(1));
        return null;
    }

    @Override
    public Object visitGroupByClause(TqlParser.GroupByClauseContext ctx) {
        if (ctx.aggregateWindowedFunction() != null)   visit(ctx.aggregateWindowedFunction());
        visit(ctx.byCaluse());
        return null;
    }

    @Override
    public Object visitValue(TqlParser.ValueContext ctx) {
        if (ctx.textLiteral() != null) return visit(ctx.textLiteral());
        if (ctx.columnName() != null) return visit(ctx.columnName());
        return null;
    }

    @Override
    public Object visitTextLiteral(TqlParser.TextLiteralContext ctx) {
        return ctx.TEXT_STRING().getText();
    }
}
