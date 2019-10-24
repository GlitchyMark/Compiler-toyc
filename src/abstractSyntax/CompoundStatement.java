package abstractSyntax;

import parser.*;

public class CompoundStatement extends GrammarDef
{
    public CompoundStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tok.getTok().equals(TCscanner.Tokens.LCURLY))
        {
            //consume l curly
            parser.getNextToken();
        }
        else
        {
            logError("{ expected");
        }

        while(parser.tok.getTok().equals(TCscanner.Tokens.INT) || parser.tok.getTok().equals(TCscanner.Tokens.CHAR))
        {
            if(parser.tok.getTok().equals(TCscanner.Tokens.INT))
            {
                new Type(parser);
            }
            else if(parser.tok.getTok().equals(TCscanner.Tokens.CHAR))
            {
                new Type(parser);
            }
            else
            {
                logError("");
            }

            if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
            {
                //consume ID
                parser.getNextToken();
            }
            else
            {
                logError("");
            }

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

        while(parser.tokStatementCheck())
        {
            new Statement(parser);
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RCURLY))
        {
            //consume r curly
            parser.getNextToken();
        }
        else
        {
            logError("} expected");
        }
    }

    @Override
    public String toString()
    {
        return "reducing CompoundStatement";
    }
}
