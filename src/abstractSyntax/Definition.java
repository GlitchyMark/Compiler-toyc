package abstractSyntax;

import parser.*;

public class Definition extends GrammarDef {
    public Definition(TCparser tcp) {
        super(tcp);
    }

    @Override
    void parseDefinition() {
        if(parser.tok.getTok().equals(TCscanner.Tokens.INT))
        {
            type();
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.CHAR))
        {
            type();
        }
        else
        {
            logError();
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
        {
            //consumes ID
            parser.getNextToken();
        }
        else
        {
            logError();
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            functionDefinition();
        }
        else if(tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //Consumes semicolon
            parser.getNextToken();
            return;
        }
        else
        {
            logError();
        }
    }
}
