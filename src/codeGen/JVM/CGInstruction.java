package codeGen.JVM;

import symTable.SymTable;

public class CGInstruction {
    public JVMtargetCode target;
    SymTable symtable;

    public CGInstruction(JVMtargetCode target)
    {
        this.target = target;
    }

    void codeGen()
    {

    }

    void codeGen(String s)
    {
    }
}
