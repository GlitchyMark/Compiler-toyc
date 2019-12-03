package codeGen.JVM;

import symTable.SymbolNotFound;

public class CGFunctionExit extends CGInstruction{
    public CGFunctionExit(String function)
    {
    }
    void codeGen()
    {
        target.code.add("goto " + register);
        target.symtable = target.symtable.getPreviousTable();
    }
}
