/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

public class Term extends GrammarDef
{
    public Term(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tokPrimaryCheck())
        {
            new Primary(parser);
        }
        else
        {
            logError("term expected");
        }

        while(parser.tok.getTok().equals(TCscanner.Tokens.MULOP))
        {
            String temp = "";
            if(parser.tok.getTok().equals(TCscanner.Tokens.MULOP))
            {
                //consume mulop
                parser.printer.print(" " + parser.tok.getLex() + ", ");
                temp = parser.tok.getLex();
                parser.getNextToken();
                new Primary(parser);
            }
            else
            {
                logError("mulop expected");
            }
            if(temp.equals("%"))
            {
                parser.codegenerator.insert(new CGModulo());
            }

        }
        return;
    }

    @Override
    public String toString()
    {
        return "reducing term";
    }
}
