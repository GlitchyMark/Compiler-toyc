package abstractSyntax;

import parser.*;

public class WhileStatement extends GrammarDef
{
    public WhileStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tok.getTok().equals(TCscanner.Tokens.WHILE))
        {
            //consume while
            parser.getNextToken();
        }
        else
        {
            logError("while expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume lparen
            parser.getNextToken();
            new Expression(parser);
        }
        else
        {
            logError("( expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            parser.getNextToken();
            new Statement(parser);
        }
        else
        {
            logError(") expected");
        }
    }

    @Override
    public String toString()
    {
        return "reducing WhileStatement";
    }
}
