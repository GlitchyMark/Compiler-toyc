package codeGen.JVM;

public class CGMul extends CGInstruction
{
    void codeGen()
    {
        target.code.add("imul");
    }
}
