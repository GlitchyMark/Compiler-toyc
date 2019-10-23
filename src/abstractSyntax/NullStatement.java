package abstractSyntax;

import parser.*;

public class NullStatement extends GrammarDef
{
    public NullStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolon
            parser.getNextToken();
        }
        else
        {
            logError("; expected");
        }
    }
}
