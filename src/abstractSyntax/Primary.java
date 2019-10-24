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
            //consumes identifier
            parser.getNextToken();
            //TODO: finish this loose thread
            if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
            {
                new FunctionCall(parser);
            }
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.NUMBER))
        {
            //consume number
            parser.getNextToken();
            return;
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.STRING))
        {
            //consume string
            parser.getNextToken();
            return;
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.CHARLITERAL))
        {
            //consume char
            parser.getNextToken();
            return;
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume left paren
            parser.getNextToken();
            new Expression(parser);

            if (parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
            {
                //consume right paren
                parser.getNextToken();
                return;
            }
            else
            {
                logError("expected (");
            }
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
}
