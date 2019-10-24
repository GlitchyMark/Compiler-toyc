package abstractSyntax;

import parser.*;

public class Term extends GrammarDef
{
    public Term(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tokPrimaryCheck())
        {
            new Primary(parser);
        }
        else
        {
            logError("term expected");
        }

        while(parser.tok.getTok().equals(TCscanner.Tokens.MULOP))
        {
            if(parser.tok.getTok().equals(TCscanner.Tokens.MULOP))
            {
                //consume mulop
                parser.printer.print(" " + parser.tok.getLex() + ", ");
                parser.getNextToken();
                new Primary(parser);
            }
            else
            {
                logError("mulop expected");
            }
        }
        return;
    }

    @Override
    public String toString()
    {
        return "reducing term";
    }
}
