package abstractSyntax;

import parser.*;

public class ReadStatement extends GrammarDef
{
    public ReadStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tok.getTok().equals(TCscanner.Tokens.READ))
        {
            //consume read
            parser.getNextToken();
        }
        else
        {
            logError("");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume lparen
            parser.getNextToken();
        }
        else
        {
            logError("( expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
        {
            //consume ID
            parser.getNextToken();
            while(parser.tok.getTok().equals(TCscanner.Tokens.COMMA))
            {
                //consume comma
                parser.getNextToken();
                if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
                {
                    //consume ID
                    parser.getNextToken();
                }
                else
                {
                    logError("ID expected");
                }
            }
        }
        else
        {
            logError("ID expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume Rparen
            parser.getNextToken();
        }
        else
        {
            logError(") expected");
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

    @Override
    public String toString()
    {
        return "reducing ReadStatement";
    }
}
