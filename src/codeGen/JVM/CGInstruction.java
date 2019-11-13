package codeGen.JVM;

import symTable.SymTable;

public class CGInstruction {
    public JVMtargetCode target;

    public CGInstruction()
    {

    }

    void codeGen()
    {

    }

    void processinst(JVMtargetCode target)
    {
        this.target = target;
    }

    void codeGen(String s)
    {
    }
}
