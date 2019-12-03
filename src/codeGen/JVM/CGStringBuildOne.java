package codeGen.JVM;

public class CGStringBuildOne extends CGInstruction
{
    void codeGen()
    {
        target.code.add("invokevirtual java/lang/StringBuilder/append(Ljava/lang/String;)Ljava/lang/StringBuilder;");
    }
}
