/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

public class ExpressionStatement extends GrammarDef
{
    public ExpressionStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        parser.printer.print("exprState(");
        parser.printer.indent();
        if(parser.tokPrimaryCheck())
        {
            parser.printer.print("expr(");
            new Expression(parser);
            parser.printer.print("),");
        }
        else
        {
            logError("expression expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolon
            parser.getNextToken();
            //return;
        }
        else
        {
            logError("semicolon expected");
        }
        parser.printer.print(")");
    }

    @Override
    public String toString()
    {
        return "reducing ExpressionStatement";
    }
}
