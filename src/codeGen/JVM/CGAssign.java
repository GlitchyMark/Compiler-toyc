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
            LAS = target.symtable.getCurrentScope().getLastAccessedSym();
            gottenSym = true;

            if(!TCGlobals.lazyCheck)
                target.code.add("pop");
        }
        else {
            try {
                if (LAS.getName().contains("GLOBAL")) {
                    target.code.add("putstatic test/" + LAS.getName().replace("GLOBAL", "") + " I");
                } else
                    target.code.add("istore " + LAS.getOffset());
            }
            catch(NullPointerException e)
            {
                e.printStackTrace();
                //target.code.add("istore " + LAS.getOffset());
            }
        }
    }
}
