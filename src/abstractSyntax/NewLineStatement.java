package abstractSyntax;

import parser.*;

public class NewLineStatement extends GrammarDef
{
    public NewLineStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tok.getTok().equals(TCscanner.Tokens.NEWLINE))
        {
            //consume new line
            parser.getNextToken();
        }
        else
        {
            logError("newline expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolion
            parser.getNextToken();
            return;
        }
        else
        {
            logError("; expected");
        }
    }
}
