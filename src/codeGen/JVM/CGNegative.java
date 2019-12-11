package codeGen.JVM;

public class CGNegative extends CGInstruction
{
    void codeGen()
    {
        target.code.add("ineg");
    }
}
