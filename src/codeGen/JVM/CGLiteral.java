package codeGen.JVM;

import symTable.Symbol;
import symTable.SymbolAlreadyDeclared;

public class CGLiteral extends CGInstruction{
    String varname;
    public CGLiteral(String s) {
        varname = s;
    }

    void codeGen()
    {
        target.code.add("sipush " + varname);
    }
}
