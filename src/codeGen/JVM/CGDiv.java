package codeGen.JVM;

public class CGDiv extends CGInstruction
{
    void codeGen()
    {
        target.code.add("idiv");
    }
}
