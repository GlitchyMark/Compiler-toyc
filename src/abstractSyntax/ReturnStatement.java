/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

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
            parser.codegenerator.insert(new CGReturn(true));
            parser.printer.print("),");
        }
        else
        {
            parser.codegenerator.insert(new CGReturn(false));
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
