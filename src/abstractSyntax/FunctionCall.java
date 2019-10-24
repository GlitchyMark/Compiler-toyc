package abstractSyntax;

import parser.*;

public class FunctionCall extends GrammarDef
{
    public FunctionCall(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume lparen
            parser.getNextToken();
        }
        else
        {
            logError("'(' expected");
        }

        if(parser.tokPrimaryCheck())
        {
            new ActualParameters(parser);
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            parser.getNextToken();
        }
        else
        {
            logError(") expected");
        }
    }

    @Override
    public String toString()
    {
        return "reducing FunctionCall";
    }
}
