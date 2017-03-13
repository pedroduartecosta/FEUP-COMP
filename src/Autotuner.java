import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;

public class Autotuner {
    public static void main(String[] args) {
        String filePath = "/home/bernardo/Documents/FEUP-COMP/out/production/FEUP-COMP/test.c";

        ANTLRFileStream fileStream = null;
        try {
            fileStream = new ANTLRFileStream(filePath);
        } catch (IOException e) {
            System.err.println("Error opening file at: " + filePath);
            e.printStackTrace();
        }

        AutotunerLexer autotunerLexer = new AutotunerLexer(fileStream);
        CommonTokenStream tokenStream = new CommonTokenStream(autotunerLexer);
        AutotunerParser autotunerParser = new AutotunerParser(tokenStream);
        ParseTree parseTree = autotunerParser.pragma();
        AutotunerListener autotunerListener = new AutotunerListener() {
            @Override
            public void enterPragma(AutotunerParser.PragmaContext ctx) {
                System.out.println(ctx);
            }

            @Override
            public void exitPragma(AutotunerParser.PragmaContext ctx) {
                System.out.println(ctx);
            }

            @Override
            public void visitTerminal(TerminalNode terminalNode) {
                System.out.println(terminalNode);
            }

            @Override
            public void visitErrorNode(ErrorNode errorNode) {
                System.out.println(errorNode);
            }

            @Override
            public void enterEveryRule(ParserRuleContext parserRuleContext) {
                System.out.println(parserRuleContext);
            }

            @Override
            public void exitEveryRule(ParserRuleContext parserRuleContext) {
                System.out.println(parserRuleContext);
            }
        };

        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        parseTreeWalker.DEFAULT.walk(autotunerListener, parseTree);
    }
}