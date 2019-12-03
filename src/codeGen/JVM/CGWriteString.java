package codeGen.JVM;

public class CGWriteString extends CGInstruction
{
    void codeGen()
    {
        target.code.add("new java/lang/StringBuilder");
        target.code.add("dup");
        target.code.add("invokespecial java/lang/StringBuilder/<init>()V");
    }
}
