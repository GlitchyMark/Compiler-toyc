package codeGen.JVM;

public class CGReturn extends CGInstruction
{
    boolean ret;
    public CGReturn(boolean ret)
    {
        this.ret = ret;
    }

    void codeGen()
    {
        if(!ret)
            target.code.add("sipush 0");

        target.code.add("ireturn");
        //target.code.add(".end method");
    }
}
