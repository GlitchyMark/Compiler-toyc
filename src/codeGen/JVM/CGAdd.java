package codeGen.JVM;


public class CGAdd extends CGInstruction
{

    void codeGen()
    {
        target.code.add("iadd");
    }
}
