package abstractSyntax;

import parser.*;

public class IfStatement extends GrammarDef
{
    public IfStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        parser.printer.println("ifState(");
        parser.printer.indent();
        if(parser.tok.getTok().equals(TCscanner.Tokens.IF))
        {
            //consume if
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
            new Expression(parser);
            parser.printer.println("");
            parser.printer.outdent();
            parser.printer.println(")");
        }
        else
        {
            logError("'(' expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            parser.getNextToken();
            new Statement(parser);
            if(parser.tok.getTok().equals(TCscanner.Tokens.ELSE))
                parser.printer.println(",");
            else
                parser.printer.println("");
        }
        else
        {
            logError("')' expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.ELSE))
        {
            //consume else
            parser.getNextToken();
            if(parser.tokStatementCheck())
            {
                new Statement(parser);
            }
        }

        parser.printer.outdent();
        parser.printer.println("");
        parser.printer.print(")");

        return;
    }

    @Override
    public String toString()
    {
        return "reducing IfStatement";
    }
}
