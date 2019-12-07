package codeGen.JVM;

import symTable.Symbol;
import symTable.SymbolAlreadyDeclared;

public class CGVarDef extends CGInstruction{
    String varname;
    public CGVarDef(String s) {
        varname = s;
    }

    void codeGen()
    {
        try {
            Symbol sym = target.symtable.insert(varname);
        } catch(SymbolAlreadyDeclared sad)
        {
            //TODO: Better error msg
            //TODO: don't rename sad, its sad
            sad.printStackTrace();
        }
    }
}
