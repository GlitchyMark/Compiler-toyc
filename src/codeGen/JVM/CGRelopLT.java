package codeGen.JVM;

public class CGRelopLT extends CGInstruction
{
    /**
     * pushes a 1 or a 0 onto the stack depending on if the comparison passes/fails
     */
    void codeGen()
    {
        String temp1 = target.addLabel();
        String temp2 = target.addLabel();
        target.code.add("if_icmpge " + temp1);
        target.code.add("sipush 1");
        target.code.add("goto " + temp2);
        target.code.add(temp1 + ":");
        target.code.add("sipush 0");
        target.code.add(temp2 + ":");
    }
}
