package antlr4.tql;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        //新建一个CharStream，从标准输入读取数据
        InputStream is = new BufferedInputStream(System.in);
        CharStream input = CharStreams.fromStream(is);
        TqlLexer lexer = new TqlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TqlParser parser = new TqlParser(tokens);
        ParseTree tree = parser.whereClause();
        EvalVisitor eval = new EvalVisitor();
        List<Filter> filters = (List<Filter>)eval.visit(tree);
        filters.stream().forEach( (Filter filter) -> System.out.println(filter.toString()));
        System.out.println();
        System.out.println();
        System.out.println();
        ParseTree tree1 = parser.groupByClause();
        eval.visit(tree1);
    }
}
