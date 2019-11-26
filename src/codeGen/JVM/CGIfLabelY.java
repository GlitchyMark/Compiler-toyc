package codeGen.JVM;

public class CGIfLabelY extends CGInstruction
{
    String labelY;

    public CGIfLabelY(String labelY)
    {
        this.labelY = labelY;
    }

    void codeGen()
    {
        target.code.add(labelY + ":");
    }
}
