package abstractSyntax;

import parser.TCparser;

public class Type extends GrammarDef {
    public Type(TCparser tcp) {
        super(tcp);
    }

    @Override
    void parseDefinition() {
        parser.getNextToken();
    }
}
