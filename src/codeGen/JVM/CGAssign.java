package codeGen.JVM;

import compiler.TCGlobals;
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
            gottenSym = true;
            if(!TCGlobals.lazyCheck)
                target.code.add("pop");
        }
        else {
            target.code.add("istore " + LAS.getOffset());
        }
    }
}
