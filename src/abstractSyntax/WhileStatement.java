/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

public class WhileStatement extends GrammarDef
{
    public WhileStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        parser.printer.print("whileState(");
        parser.printer.indent();
        if(parser.tok.getTok().equals(TCscanner.Tokens.WHILE))
        {
            //consume while
            parser.getNextToken();
        }
        else
        {
            logError("while expected");
        }
        CGWhileEnter cgWhile = new CGWhileEnter();
        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume lparen
            parser.getNextToken();
            parser.printer.print("expr(");
            //label1 here, needs to check the relop every time
            //cgWhile;
            parser.codegenerator.insert(cgWhile);

            new Expression(parser);

            parser.codegenerator.insert(new CGWhileCheck(cgWhile.getLabelY()));
            //check to exit to label2 here
            parser.printer.print("),");
        }
        else
        {
            logError("( expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            parser.getNextToken();
            new Statement(parser);

            parser.codegenerator.insert(new CGWhileExit(cgWhile.getLabelX(), cgWhile.getLabelY()));
            //goto label1 here
            //label2 here, for exit
        }
        else
        {
            logError(") expected");
        }
        parser.printer.print(")");
    }

    @Override
    public String toString()
    {
        return "reducing WhileStatement";
    }
}
