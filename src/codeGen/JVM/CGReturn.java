package codeGen.JVM;

public class CGReturn extends CGInstruction
{
    void codeGen()
    {
        target.code.add("ireturn");
        target.code.add(".end method");
    }
}
