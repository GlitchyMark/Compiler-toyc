package codeGen.JVM;

import symTable.Symbol;
import symTable.SymbolAlreadyDeclared;

public class CGLiteral extends CGInstruction{
    String litname;
    public CGLiteral(String s) {
        litname = s;
    }

    void codeGen()
    {
        target.code.add("sipush " + litname);
        target.lastLiteral = litname;
    }
}
