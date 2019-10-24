package abstractSyntax;

import parser.*;

public class Definition extends GrammarDef {
    public Definition(TCparser tcp) {
        super(tcp);
    }
String buffer;
    @Override
    void parseDefinition() {
        if(parser.tok.getTok().equals(TCscanner.Tokens.INT))
        {
            buffer = "int";
            new Type(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.CHAR))
        {
            buffer = "char";
            new Type(parser);
        }
        else
        {
            buffer = "NULL";
            logError("");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
        {
            buffer = parser.tok.getLex() + ", " + buffer;
            //consumes ID
            parser.getNextToken();
        }
        else
        {
            logError("missing id");
        }


        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            parser.printer.printlnspaces("funcDef(" + buffer + ", ");
            parser.printer.indent();
            new FunctionDefinition(parser);
            parser.printer.println(")");
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            parser.printer.printspaces("varDef(" + buffer);
            //Consumes semicolon
            parser.getNextToken();
            parser.printer.println(")");
            return;
        }
        else
        {
            logError("missing '(' or ';'");
        }
    }
}
