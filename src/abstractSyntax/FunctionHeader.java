package abstractSyntax;

import parser.*;

public class FunctionHeader extends GrammarDef{
    public FunctionHeader(TCparser tcp) {
        super(tcp);
    }

    @Override
    void parseDefinition() {
        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            parser.getNextToken();
        }
        else
        {
            logError("missing '('");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.INT))
        {
            new FormalParamList(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.CHAR))
        {
            new FormalParamList(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consumes r paren
            parser.getNextToken();
            return;
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            parser.getNextToken();
            return;
        }
        else
        {
            logError("missing ')'");
        }
    }
}