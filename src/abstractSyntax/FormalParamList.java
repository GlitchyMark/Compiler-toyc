package abstractSyntax;

import parser.*;

public class FormalParamList extends GrammarDef {
    public FormalParamList(TCparser tcp) {
        super(tcp);
    }

    @Override
    void parseDefinition() {
        if (parser.tok.getTok().equals(TCscanner.Tokens.INT)) {
            new Type(parser);
        } else if (parser. tok.getTok().equals(TCscanner.Tokens.CHAR)) {
            new Type(parser);
        } else {
            logError();
        }

        if (parser.tok.getTok().equals(TCscanner.Tokens.ID)) {
            //consumes identifier
            parser.getNextToken();
        } else {
            logError();
        }

        while (parser.tok.getTok().equals(TCscanner.Tokens.COMMA)) {
            if (parser.tok.getTok().equals(TCscanner.Tokens.COMMA)) {
                //consume comma
                parser.getNextToken();
            } else {
                logError();
            }

            if (parser.tok.getTok().equals(TCscanner.Tokens.INT)) {
                new Type(parser);
            } else if (parser.tok.getTok().equals(TCscanner.Tokens.CHAR)) {
                new Type(parser);
            } else {
                logError();
            }

            if (parser.tok.getTok().equals(TCscanner.Tokens.ID)) {
                //consume ID
               parser. getNextToken();
            }
        }
    }
}
