package codeGen.JVM;

import symTable.Symbol;
import symTable.SymbolAlreadyDeclared;

public class CGAssign extends CGInstruction {
    Symbol LAS;
    public CGAssign()
    {
        LAS = target.symtable.getLastAccessedSym();
    }
    void codeGen()
    {
        target.code.add("return");
        target.code.add(".end method");
    }
}
