package codeGen.JVM;

import compiler.TCGlobals;

public class CGGlobalVar extends CGInstruction
{
    String name;

    public CGGlobalVar(String name)
    {
        this.name = name;
    }

    void codeGen()
    {
        String temp = ".field  static " + name + " I";
        target.code.add(4, temp);
        TCGlobals.sorryAgain.add(name);
    }
}
