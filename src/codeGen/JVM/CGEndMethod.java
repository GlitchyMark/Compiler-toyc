package codeGen.JVM;

public class CGEndMethod extends CGInstruction
{
    void codeGen()
    {
        target.code.add("ireturn");
        target.code.add(".end method");
    }
}
