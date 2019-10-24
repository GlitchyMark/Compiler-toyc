package abstractSyntax;

import parser.*;

public class ActualParameters extends GrammarDef
{
    public ActualParameters(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tokPrimaryCheck())
        {
            new Expression(parser);
            while(parser.tok.getTok().equals(TCscanner.Tokens.COMMA))
            {
                //consume comma
                parser.getNextToken();
                if(parser.tokPrimaryCheck())
                {
                    new Expression(parser);
                }
                else
                {
                    logError(", expected");
                }
            }
        }
    }

    @Override
    public String toString()
    {
        return "reducing ActualParameters";
    }
}
