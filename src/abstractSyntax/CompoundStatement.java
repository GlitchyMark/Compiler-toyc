/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

public class CompoundStatement extends GrammarDef
{
    public CompoundStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        String buffer = "";

        parser.printer.print("blockState(");
        if(parser.tok.getTok().equals(TCscanner.Tokens.LCURLY))
        {
            //consume l curly
            parser.getNextToken();
        }
        else
        {
            logError("{ expected");
        }

        while(parser.tok.getTok().equals(TCscanner.Tokens.INT) || parser.tok.getTok().equals(TCscanner.Tokens.CHAR))
        {
            if(parser.tok.getTok().equals(TCscanner.Tokens.INT))
            {
                buffer = "int";
                new Type(parser);
            }
            else if(parser.tok.getTok().equals(TCscanner.Tokens.CHAR))
            {
                buffer = "char";
                new Type(parser);
            }
            else
            {
                logError("");
            }

            if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
            {
                parser.codegenerator.insert(CGVar.class, parser.tok.getLex());
                buffer = parser.tok.getLex() + ", " + buffer;
                parser.printer.print("varDef(" + buffer + ")");
                //consume ID
                parser.getNextToken();
            }
            else
            {
                logError("missing 'ID'");
            }

            if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
            {
                //consume semicolon
                parser.getNextToken();
            }
            else
            {
                logError("';' expected");
            }
        }

        if(buffer.length() > 0)
            parser.printer.print(",");

        while(parser.tokStatementCheck())
        {
            new Statement(parser);

            if(parser.tokStatementCheck())
                parser.printer.print(",");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RCURLY))
        {
            //consume r curly
            parser.getNextToken();
        }
        else
        {
            logError("} expected");
        }
        parser.printer.print(")");
    }

    @Override
    public String toString()
    {
        return "reducing CompoundStatement";
    }
}
