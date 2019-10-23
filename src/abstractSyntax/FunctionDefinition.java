package abstractSyntax;

import parser.*;

public class FunctionDefinition extends GrammarDef {
    public FunctionDefinition(TCparser tcp) {
        super(tcp);
    }

    @Override
    void parseDefinition() {
        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            functionHeader();
        }
        else
        {
            logError();
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.LCURLY))
        {
            functionBody();
            return;
        }
        else
        {
            logError();
        }
    }
}
