/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;

public class ActualParameters extends GrammarDef
{
    public ActualParameters(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tokPrimaryCheck())
        {
            parser.printer.print(",");
            parser.printer.print("expr(");
            new Expression(parser);
            parser.printer.print("),");
            while(parser.tok.getTok().equals(TCscanner.Tokens.COMMA))
            {
                //consume comma
                parser.getNextToken();
                if(parser.tokPrimaryCheck())
                {
                    parser.printer.print(",");
                    parser.printer.print("expr(");
                    new Expression(parser);
                    parser.printer.print("),");
                }
                else
                {
                    logError("primary expected");
                }
            }
        }
    }

    @Override
    public String toString()
    {
        return "reducing ActualParameters";
    }
}
