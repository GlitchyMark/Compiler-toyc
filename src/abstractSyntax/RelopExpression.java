/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;

public class RelopExpression extends GrammarDef
{
    public RelopExpression(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        //TODO: figure out printing for this one too
        if(parser.tokPrimaryCheck())
        {
            new SimpleExpression(parser);
        }
        else
        {
            logError("");
        }

        while(parser.tok.getTok().equals(TCscanner.Tokens.RELOP))
        {
            if(parser.tok.getTok().equals(TCscanner.Tokens.RELOP))
            {
                //consume relop
                parser.printer.print(" " + parser.tok.getLex() + ", ");
                parser.getNextToken();
                new SimpleExpression(parser);
            }
            else
            {
                logError("relop expected");
            }
        }
        return;
    }

    @Override
    public String toString()
    {
        return "reducing RelopExpression";
    }
}
