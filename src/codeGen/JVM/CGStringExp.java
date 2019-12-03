package codeGen.JVM;

public class CGStringExp extends CGInstruction
{
    String str;
    public CGStringExp(String str)
    {
        this.str = str;
    }

    void codeGen()
    {
        target.code.add("ldc \"" + str + "\"");
    }
}
