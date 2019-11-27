package codeGen.JVM;

public class CGModulo extends CGInstruction
{
    void codeGen()
    {
        target.code.add("irem");
    }
}
