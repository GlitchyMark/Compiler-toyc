package codeGen.JVM;

public class CGWhileCheck extends CGInstruction
{
    String labelY;

    public CGWhileCheck(String labelY)
    {
        this.labelY = labelY;
    }

    void codeGen()
    {
        target.code.add("sipush 1");
        target.code.add("if_icmplt " + labelY);
    }
}
