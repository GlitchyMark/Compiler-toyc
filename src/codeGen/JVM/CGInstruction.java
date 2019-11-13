package codeGen.JVM;

import symTable.SymTable;

public class CGInstruction {
    public JVMtargetCode target = new JVMtargetCode();

    public CGInstruction()
    {

    }

    void processinst(JVMtargetCode target)
    {
        this.target = target;
        codeGen();
    }

    void codeGen()
    {

    }
}
