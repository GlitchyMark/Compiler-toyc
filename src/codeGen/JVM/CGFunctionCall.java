package codeGen.JVM;

import symTable.SymbolNotFound;

public class CGFunctionCall extends CGInstruction{
    String label;
    public CGFunctionCall(String function)
    {
        //TODO: Store current address in register 0
        label = function;
        target.symtable = target.symtable;
        try {
            target.symtable = target.symtable.getSymTable(function);
        }
        catch(SymbolNotFound error) {error.printStackTrace();}
    }

    void codeGen()
    {
        target.code.add("jsr " + label);
    }

}
