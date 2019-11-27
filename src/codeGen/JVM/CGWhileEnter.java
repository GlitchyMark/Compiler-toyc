package codeGen.JVM;

public class CGWhileEnter extends CGInstruction
{
    String labelX="labelx";
    String labelY="labely";
    public CGWhileEnter()
    {

    }

    void codeGen()
    {
        labelX = target.addLabel();
        labelY = target.addLabel();
        target.code.add(labelX + ":");
    }

    public String getLabelX()
    {
        return labelX;
    }

    public String getLabelY()
    {
        return labelY;
    }
}
