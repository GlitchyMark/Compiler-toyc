/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import compiler.TCGlobals;
import parser.*;
import codeGen.JVM.*;

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
            TCscanner.Tokens temp = parser.tok.getTok();
            parser.printer.print(",");
            parser.printer.print("expr(");
            new Expression(parser);
            if(TCGlobals.isWrite && temp.equals(TCscanner.Tokens.STRING))
            {
                parser.codegenerator.insert(new CGStringBuildOne());
            }
            if(TCGlobals.isWrite && (temp.equals(TCscanner.Tokens.ID) || temp.equals(TCscanner.Tokens.NUMBER)))
            {
                parser.codegenerator.insert(new CGStringBuildTwo());
            }
            parser.printer.print("),");
            while(parser.tok.getTok().equals(TCscanner.Tokens.COMMA))
            {
                //consume comma
                parser.getNextToken();
                if(parser.tokPrimaryCheck())
                {
                    TCscanner.Tokens temp1 = parser.tok.getTok();

                    parser.printer.print(",");
                    parser.printer.print("expr(");
                    new Expression(parser);
                    if(TCGlobals.isWrite && temp1.equals(TCscanner.Tokens.STRING))
                    {
                        parser.codegenerator.insert(new CGStringBuildOne());
                    }
                    if(TCGlobals.isWrite && (temp1.equals(TCscanner.Tokens.ID) || temp.equals(TCscanner.Tokens.NUMBER)))
                    {
                        parser.codegenerator.insert(new CGStringBuildTwo());
                    }
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
