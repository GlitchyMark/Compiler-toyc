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
        return;
    }
}
