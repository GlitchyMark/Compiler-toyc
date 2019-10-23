package abstractSyntax;

import parser.*;

public class SimpleExpression extends GrammarDef
{
    public SimpleExpression(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition() {
        //TODO: PRINJTING
        if(parser.tokPrimaryCheck())
        {
            new Term(parser);
        }
        else
        {
            logError("");
        }

        while(parser.tok.getTok().equals(TCscanner.Tokens.ADDOP))
        {
            if(parser.tok.getTok().equals(TCscanner.Tokens.ADDOP))
            {
                //consume addop
                parser.getNextToken();
                new Term(parser);
            }
            else
            {
                logError("addop expected");
            }
        }
        return;
    }
}
