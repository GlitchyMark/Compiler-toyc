package abstractSyntax;

import parser.*;

public class FormalParamList extends GrammarDef {
    public FormalParamList(TCparser tcp) {
        super(tcp);
    }
    String buffer;
    @Override
    void parseDefinition() {
        if (parser.tok.getTok().equals(TCscanner.Tokens.INT)) {
            buffer = "int";
            new Type(parser);
        } else if (parser. tok.getTok().equals(TCscanner.Tokens.CHAR)) {
            buffer = "char";
            new Type(parser);
        } else {
            logError("missing type");
        }

        if (parser.tok.getTok().equals(TCscanner.Tokens.ID)) {
            buffer = parser.tok.getLex() + ", " + buffer;
            //consumes identifier
            parser.getNextToken();
        } else {
            logError("missing id token");
        }
        parser.printer.printlnspaces("varDef(" + buffer + "), ");

        while (parser.tok.getTok().equals(TCscanner.Tokens.COMMA)) {
            buffer = "";
            if (parser.tok.getTok().equals(TCscanner.Tokens.COMMA)) {
                //consume comma
                parser.getNextToken();
            } else {
                logError("missing ','");
            }

            if (parser.tok.getTok().equals(TCscanner.Tokens.INT)) {
                buffer = "int";
                new Type(parser);
            } else if (parser.tok.getTok().equals(TCscanner.Tokens.CHAR)) {
                buffer = "char";
                new Type(parser);
            } else {
                logError("missing type");
            }

            if (parser.tok.getTok().equals(TCscanner.Tokens.ID)) {
                buffer = parser.tok.getLex() + ", " + buffer;
                //consume ID
               parser. getNextToken();
            }
            parser.printer.printlnspaces("varDef(" + buffer + "),");
        }
    }
}
