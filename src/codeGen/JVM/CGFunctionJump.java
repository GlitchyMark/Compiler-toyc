package codeGen.JVM;

import symTable.SymbolNotFound;

public class CGFunctionJump extends CGInstruction{
    String label;

    public CGFunctionJump(String function)
    {
        //TODO: Store current address in register 0
        label = function;
    }

    void codeGen()
    {
        target.code.add("jsr " + label);
    }

}
