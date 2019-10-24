package abstractSyntax;

import parser.*;

public class ReturnStatement extends GrammarDef
{
    public ReturnStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tok.getTok().equals(TCscanner.Tokens.RETURN))
        {
            //consume return
            parser.getNextToken();
        }
        else
        {
            logError("return expected");
        }

        if(parser.tokPrimaryCheck())
        {
            new Expression(parser);
        }
        if (parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolon
            parser.getNextToken();
            return;
        }
        else
        {
            logError("; expected");
        }
    }

    @Override
    public String toString()
    {
        return "reducing ReturnStatement";
    }
}
