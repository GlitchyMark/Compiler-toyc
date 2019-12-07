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
        //TODO: Figure something out here that makes
        //TODO: return statements actually function in branches.
        target.code.add("goto " + labelY);
    }

}
