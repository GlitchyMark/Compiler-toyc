package codeGen.JVM;

public class CGIfLabelX extends CGInstruction
{
    String labelX;
    public CGIfLabelX(String labelX)
    {
        this.labelX = labelX;
    }

    void codeGen()
    {
        target.code.add(labelX + ":");
    }
}
