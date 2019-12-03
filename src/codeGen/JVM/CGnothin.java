package codeGen.JVM;

public class CGnothin extends CGInstruction
{
    String label;
    public CGnothin(String label)
    {
        this.label = label;
    }

    void codeGen()
    {
        target.symtable.setNewCurrentScope(label);
    }

}
