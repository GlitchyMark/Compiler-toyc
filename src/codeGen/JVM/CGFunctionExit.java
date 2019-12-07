package codeGen.JVM;

import symTable.SymbolNotFound;

public class CGFunctionExit extends CGInstruction{
    public CGFunctionExit()
    {
    }
    void codeGen()
    {
        if(!target.symtable.getLabel().equals("main"))
            target.code.add("ret 0");
        target.symtable = target.symtable.getOwner();
    }
}
