package codeGen.JVM;

public class CGIfGoto extends CGInstruction
{
    String labelY;
    public CGIfGoto(String labelY)
    {
        this.labelY = labelY;
    }

    void codeGen()
    {
        target.code.add("goto " + labelY);
    }

}
