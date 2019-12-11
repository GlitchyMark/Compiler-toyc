/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;
import symTable.Symbol;

public class Expression extends GrammarDef
{
    public Expression(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tokPrimaryCheck())
        {
            new RelopExpression(parser);
        }
        else
        {
            logError("");
        }
        while(parser.tok.getTok().equals(TCscanner.Tokens.ASSIGNOP))
        {
            CGAssign cga = new CGAssign(parser.codegenerator.statementStartingAssign);
            parser.codegenerator.statementStartingAssign = true;
            parser.codegenerator.insert(cga);
            if(parser.tok.getTok().equals(TCscanner.Tokens.ASSIGNOP))
            {
                //consume assignop
                parser.printer.print(" =, ");
                parser.getNextToken();
                parser.printer.print("expr(");
                new RelopExpression(parser);
                parser.printer.print("),");
            }
            else
            {
                logError("'=' expected");
            }
            parser.codegenerator.insert(cga);
        }
        return;
    }

    @Override
    public String toString()
    {
        return "reducing Expression";
    }
}
