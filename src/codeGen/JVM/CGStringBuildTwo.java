package codeGen.JVM;

public class CGStringBuildTwo extends CGInstruction
{
    void codeGen()
    {
        target.code.add("invokevirtual java/lang/StringBuilder/append(I)Ljava/lang/StringBuilder;");
    }
}
