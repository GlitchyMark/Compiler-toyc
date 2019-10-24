package abstractSyntax;

import parser.*;

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
                parser.printer.println("funcCall(");
                parser.printer.indent();
                parser.printer.print(thing);
                new FunctionCall(parser);
                parser.printer.outdent();
                parser.printer.println("");
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
            parser.printer.print(" " + parser.tok.getLex() +", ");
            //consume string
            parser.getNextToken();
            return;
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.CHARLITERAL))
        {
            parser.printer.print(" " + parser.tok.getLex() +", ");
            //consume char
            parser.getNextToken();
            return;
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume left paren
            parser.printer.println("expr(");
            parser.printer.indent();
            parser.getNextToken();
            new Expression(parser);
            parser.printer.outdent();
            parser.printer.println("");
            parser.printer.print(")");

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
            parser.printer.outdent();
            parser.printer.println("hello");
            parser.printer.print(")");
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.ADDOP))
        {
            //consume addop
            parser.getNextToken();
            new Primary(parser);
            return;
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.NOT))
        {
            //consume not
            parser.getNextToken();
            new Primary(parser);
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
