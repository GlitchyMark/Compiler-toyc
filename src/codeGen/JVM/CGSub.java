package codeGen.JVM;

public class CGSub extends CGInstruction{

    void codeGen()
    {
        target.code.add("isub");
    }
}
