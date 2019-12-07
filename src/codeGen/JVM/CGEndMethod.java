package codeGen.JVM;

public class CGEndMethod extends CGInstruction
{
    void codeGen()
    {
        target.code.add(".end method");
    }
}
