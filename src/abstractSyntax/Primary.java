/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

public class Primary extends GrammarDef
{
    public Primary(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
        {
            String thing = parser.tok.getLex();
            //consumes identifier
            parser.getNextToken();
            //TODO: finish this loose thread
            if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
            {
                parser.printer.print("funcCall(");
                parser.printer.print(thing);
                new FunctionCall(parser);
                parser.printer.print(")");
            }else
                parser.printer.print(" " + thing +", ");
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.NUMBER))
        {
            parser.printer.print(" " + parser.tok.getLex() +", ");
            //consume number
            parser.getNextToken();
            return;
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.STRING))
        {
            parser.printer.print(" \"" + parser.tok.getLex().replace(")", "\u2001").replace("(", "\u2002") +"\", ");
            //consume string
            parser.getNextToken();
            return;
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.CHARLITERAL))
        {
            parser.printer.print(" " + parser.tok.getLex().replace(")", "\u2001").replace("(", "\u2002")  +", ");
            //consume char
            parser.getNextToken();
            return;
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume left paren
            parser.printer.print("expr(");
            parser.getNextToken();
            new Expression(parser);
            parser.printer.print("),");

            if (parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
            {
                //consume right paren
                //parser.printer.outdent();
                parser.getNextToken();
                return;
            }
            else
            {
                logError("expected ')'");
            }
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.ADDOP))
        {
            //consume addop
            parser.getNextToken();
            parser.printer.print("minus( ");
            new Primary(parser);
            parser.printer.print(")");
            return;
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.NOT))
        {
            //consume not
            parser.getNextToken();
            parser.printer.print("not(");
            new Primary(parser);
            parser.printer.print(")");
            return;
        }
        else
        {
            logError("expecting primary");
        }
    }

    @Override
    public String toString()
    {
        return "reducing Primary";
    }
}
