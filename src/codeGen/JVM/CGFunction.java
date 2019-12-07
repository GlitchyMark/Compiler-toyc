package codeGen.JVM;

public class CGFunction extends CGInstruction{

    String label;
    public CGFunction(String lex)
    {
        label = lex;
    }

    void codeGen()
    {
        target.symtable = target.symtable.addScope(label);
        target.code.add(label + ":");
        target.code.add("astore_0");
    }

}
