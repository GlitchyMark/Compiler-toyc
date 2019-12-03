package codeGen.JVM;

import compiler.TCGlobals;
import symTable.Symbol;
import symTable.SymbolAlreadyDeclared;

public class CGParamVar extends CGInstruction
{
    String varname;
    public CGParamVar(String s) {
        varname = s;
    }

    void codeGen()
    {
        try {
            Symbol sym = target.symtable.getCurrentScope().insert(varname);
            TCGlobals.funcCount++;
        } catch(SymbolAlreadyDeclared sad)
        {
            //TODO: Better error msg
            //TODO: don't rename sad, its sad
            sad.printStackTrace();
        }
    }
}
