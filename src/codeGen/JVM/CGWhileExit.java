package codeGen.JVM;

public class CGWhileExit extends CGInstruction
{
    String labelX;
    String labelY;
    public CGWhileExit(String labelX, String labelY)
    {
        this.labelX = labelX;
        this.labelY = labelY;
    }

    void codeGen()
    {
        target.code.add("goto " + labelX);
        target.code.add(labelY + ":");
    }
}
