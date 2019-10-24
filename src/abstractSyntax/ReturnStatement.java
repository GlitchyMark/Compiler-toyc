/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;

public class ReturnStatement extends GrammarDef
{
    public ReturnStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        parser.printer.print("returnState(");
        if(parser.tok.getTok().equals(TCscanner.Tokens.RETURN))
        {
            //consume return
            parser.getNextToken();
        }
        else
        {
            logError("return expected");
        }

        if(parser.tokPrimaryCheck())
        {
            parser.printer.print("expr(");
            new Expression(parser);
            parser.printer.print("),");
        }
        if (parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolon
            parser.getNextToken();
        }
        else
        {
            logError("; expected");
        }
        parser.printer.print(")");
    }

    @Override
    public String toString()
    {
        return "reducing ReturnStatement";
    }
}
