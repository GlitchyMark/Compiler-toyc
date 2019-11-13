/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

public class NewLineStatement extends GrammarDef
{
    public NewLineStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tok.getTok().equals(TCscanner.Tokens.NEWLINE))
        {
            parser.printer.print("newLineState()");
            //consume new line
            parser.getNextToken();
        }
        else
        {
            logError("newline expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolion
            parser.getNextToken();
            return;
        }
        else
        {
            logError("; expected");
        }
    }

    @Override
    public String toString()
    {
        return "reducing NewlineStatement";
    }
}
