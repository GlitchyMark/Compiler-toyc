/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;
import symTable.SymbolNotFound;

public class Primary extends GrammarDef
{
    public Primary(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        //TODO: figure out why CGVarGet is being called when it shouldn't
        //TODO: make a check in CGVarGet that prevents uninitialized vars from being called
        if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
        {
            String identifier = parser.tok.getLex();
            //consumes identifier
            //try {
            parser.codegenerator.insert(new CGVarGet(identifier));
            //} //catch(SymbolNotFound e) {e.printStackTrace();}
            parser.getNextToken();
            //TODO: finish this loose thread
            if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
            {
                parser.printer.print("funcCall(");
                parser.printer.print(identifier);
                parser.codegenerator.buffer = identifier;
                new FunctionCall(parser);
                parser.printer.print(")");
            }
            else
            {
                parser.printer.print(" " + identifier + ", ");
            }
            parser.codegenerator.statementStarting = false;
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.NUMBER))
        {
            parser.codegenerator.insert(new CGLiteral(parser.tok.getLex()));
            parser.printer.print(" " + parser.tok.getLex() +", ");
            //consume number
            parser.getNextToken();
            return;
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.STRING))
        {
            parser.codegenerator.insert(new CGStringExp(parser.tok.getLex()));
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
            parser.codegenerator.insert(new CGNegative());
            parser.printer.print(")");
            return;
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.NOT))
        {
            //consume not
            parser.getNextToken();
            parser.printer.print("not(");
            new Primary(parser);
            parser.codegenerator.insert(new CGNot());
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
