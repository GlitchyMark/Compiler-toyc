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
        //TODO: figure out how the fuck this printing work
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
                logError("= expected");
            }
        }
        return;
    }
}
