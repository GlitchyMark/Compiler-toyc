package codeGen.JVM;

import symTable.Symbol;
import symTable.SymbolAlreadyDeclared;

public class CGVar extends CGInstruction{
    String varname;
    public CGVar(String s) {
        varname = s;
    }

    void codeGen()
    {
        try {
            Symbol sym = target.symtable.insert(varname);
            target.code.add("iconst_0");
            target.code.add("istore " + sym.getOffset());
        } catch(SymbolAlreadyDeclared sad)
        {
            //TODO: Better error msg
            sad.printStackTrace();
        }
    }
}
