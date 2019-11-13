package codeGen.JVM;

import symTable.Symbol;
import symTable.SymbolAlreadyDeclared;

public class CGVar extends CGInstruction{
    String varname;
    public CGVar(String s) {
        varname = s;
    }

    void codeGen(String s)
    {
        try {
            target.symtable.insert(varname);
        } catch(SymbolAlreadyDeclared sad)
        {
            //TODO: Better error msg
            sad.printStackTrace();
        }
    }
}
