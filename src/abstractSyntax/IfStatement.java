/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

public class IfStatement extends GrammarDef
{
    public IfStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        parser.printer.print("ifState(");
        if(parser.tok.getTok().equals(TCscanner.Tokens.IF))
        {
            //consume if
            parser.getNextToken();
        }
        else
        {
            logError("");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume lparen
            parser.getNextToken();
            parser.printer.print("expr(");
            new Expression(parser);
            parser.printer.print("),");
        }
        else
        {
            logError("'(' expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            parser.getNextToken();
            new Statement(parser);
        }
        else
        {
            logError("')' expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.ELSE))
            parser.printer.print(",");
        else
            parser.printer.print("");

        if(parser.tok.getTok().equals(TCscanner.Tokens.ELSE))
        {
            //consume else
            parser.getNextToken();
            if(parser.tokStatementCheck())
            {
                new Statement(parser);
            }
        }
        parser.printer.print(")");

        return;
    }

    @Override
    public String toString()
    {
        return "reducing IfStatement";
    }
}
