package abstractSyntax;

import parser.*;

public class FunctionBody extends GrammarDef{
    public FunctionBody(TCparser tcp) {
        super(tcp);
    }

    @Override
    void parseDefinition() {
        if (parser.tok.getTok().equals(TCscanner.Tokens.LCURLY)) {
            new CompoundStatement(parser);
            return;
        } else {
            logError("missing '{'");
        }
    }
}
