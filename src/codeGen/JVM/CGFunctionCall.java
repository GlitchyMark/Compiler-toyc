package codeGen.JVM;


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
        target.code.add("invokestatic test/" + j.formatted);
    }

}
