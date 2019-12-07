package codeGen.JVM;

import symTable.SymbolNotFound;

public class CGFunctionCall extends CGInstruction{
    String label;

    public CGFunctionCall(String function)
    {
        //TODO: Store current address in register 0
        label = function;
    }

    void codeGen()
    {
        try {
            target.symtable = target.symtable.getSymTable(label);
        }
        catch(SymbolNotFound error) {
            error.printStackTrace();
        }
    }

}
