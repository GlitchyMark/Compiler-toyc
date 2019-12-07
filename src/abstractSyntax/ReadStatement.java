/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

public class ReadStatement extends GrammarDef
{
    public ReadStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        parser.printer.print("readState(");
        parser.printer.indent();
        if(parser.tok.getTok().equals(TCscanner.Tokens.READ))
        {
            //consume read
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
        }
        else
        {
            logError("( expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
        {
            parser.printer.print(parser.tok.getLex());
            //consume ID
            parser.codegenerator.insert(new CGReadIn(parser.tok.getLex()));
            parser.getNextToken();
            while(parser.tok.getTok().equals(TCscanner.Tokens.COMMA))
            {
                parser.printer.print(", ");
                //consume comma
                parser.getNextToken();
                if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
                {
                    parser.printer.print(parser.tok.getLex());
                    parser.codegenerator.insert(new CGReadIn(parser.tok.getLex()));
                    //consume ID
                    parser.getNextToken();
                }
                else
                {
                    logError("ID expected");
                }
            }
        }
        else
        {
            logError("ID expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume Rparen
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
        return "reducing ReadStatement";
    }
}
