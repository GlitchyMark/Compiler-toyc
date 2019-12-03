package codeGen.JVM;

import symTable.SymbolNotFound;

public class CGFunctionCall extends CGInstruction{

    public CGFunctionCall(String function)
    {

        try {
            target.symtable = target.symtable.getSymTable(function);
        }
        catch(SymbolNotFound error) {error.printStackTrace();}
    }

    void codeGen()
    {

    }

}
