package codeGen.JVM;


import compiler.TCGlobals;

public class CGFunctionCall extends CGInstruction{
    String label;
    JVMFunc j;
    public CGFunctionCall(String function)
    {
        label = function;
    }

    void codeGen()
    {
        j = target.funcMan.getFunc(label);
        target.code.add("invokestatic " + TCGlobals.className + "/" + j.formatted);
    }

}
