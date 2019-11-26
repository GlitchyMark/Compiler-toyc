/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

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
            String temp = "";
            if(parser.tok.getTok().equals(TCscanner.Tokens.RELOP))
            {
                //consume relop
                temp = parser.tok.getLex();
                parser.printer.print(" " + parser.tok.getLex() + ", ");
                parser.getNextToken();
                new SimpleExpression(parser);
            }
            else
            {
                logError("relop expected");
            }

            if(temp.equals("<"))
            {
                parser.codegenerator.insert(new CGRelopLT());
            }
            else if(temp.equals("<="))
            {
                parser.codegenerator.insert(new CGRelopLTE());
            }
            else if(temp.equals(">"))
            {
                parser.codegenerator.insert(new CGRelopGT());
            }
            else if(temp.equals(">="))
            {
                parser.codegenerator.insert(new CGRelopGTE());
            }
            else if(temp.equals("=="))
            {
                parser.codegenerator.insert(new CGRelopEQ());
            }
            else if(temp.equals("!="))
            {
                parser.codegenerator.insert(new CGRelopNE());
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
