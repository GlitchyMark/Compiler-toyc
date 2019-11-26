package codeGen.JVM;

public class CGIfCmp extends CGInstruction
{
    String labelX="labelx";
    String labelY="labely";
    public CGIfCmp()
    {

    }

    void codeGen()
    {
        labelX = target.addLabel();
        labelY = target.addLabel();
        target.code.add("sipush 1");
        target.code.add("if_icmplt " + labelX);
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
