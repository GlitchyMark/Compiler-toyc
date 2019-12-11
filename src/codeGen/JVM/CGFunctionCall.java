package codeGen.JVM;


import compiler.TCGlobals;

public class CGFunctionCall extends CGInstruction{
    String label;
    JVMFunc j;
    boolean lonestatement;
    public CGFunctionCall(String function, boolean loneStatement)
    {
        label = function;
        lonestatement = loneStatement;
    }

    void codeGen()
    {
        j = target.funcMan.getFunc(label);
        target.code.add("invokestatic " + TCGlobals.className + "/" + j.formatted);
        if(lonestatement)
            target.code.add("pop");
    }

}
