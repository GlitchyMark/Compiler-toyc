/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;

public class NullStatement extends GrammarDef
{
    public NullStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            parser.printer.print("breakState()");
            //consume semicolon
            parser.getNextToken();
        }
        else
        {
            logError("; expected");
        }
    }

    @Override
    public String toString()
    {
        return "reducing NullStatement";
    }
}
