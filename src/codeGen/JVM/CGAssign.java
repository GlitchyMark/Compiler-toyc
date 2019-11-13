package codeGen.JVM;

import symTable.Symbol;
import symTable.SymbolAlreadyDeclared;

public class CGAssign extends CGInstruction {
    Symbol LAS;
    boolean gottenSym = false;
    public CGAssign()
    {

    }
    void codeGen()
    {
        if(!gottenSym) {
            LAS = target.symtable.getLastAccessedSym();
        }
        else {
            target.code.add("istore " + LAS.getOffset());
        }
    }
}
