package abstractSyntax;

import parser.*;

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
            if(parser.tok.getTok().equals(TCscanner.Tokens.ASSIGNOP))
            {
                //consume assignop
                parser.getNextToken();
                new RelopExpression(parser);
            }
            else
            {
                logError("'=' expected");
            }
        }
        return;
    }

    @Override
    public String toString()
    {
        return "reducing Expression";
    }
}
