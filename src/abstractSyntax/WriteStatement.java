/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

public class WriteStatement extends GrammarDef
{
    public WriteStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        parser.printer.print("writeState(");
        if(parser.tok.getTok().equals(TCscanner.Tokens.WRITE))
        {
            //consume write
            parser.getNextToken();
        }
        else
        {
            logError("write expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume lparen
            parser.getNextToken();
            new ActualParameters(parser);
        }

        else
        {
            logError("( expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            parser.getNextToken();
        }
        else
        {
            logError(") expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolon
            parser.getNextToken();
            //return;
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
        return "reducing WriteStatement";
    }
}
