package codeGen.JVM;

import compiler.TCGlobals;

public class CGFunction extends CGInstruction{

    String label;
    public CGFunction(String lex)
    {
        label = lex;
    }

    void codeGen()
    {
        JVMFunc f = new JVMFunc(label, TCGlobals.funcCount);
        target.funcMan.addFunc(f);
        TCGlobals.functions.add(label);
        String thing = "";
        while(TCGlobals.funcCount > 0)
        {
            thing += "I";
            TCGlobals.funcCount--;
        }
        target.code.add(".method public static " + label + "(" + thing + ")I");
        target.code.add(".limit stack 100");
        target.code.add(".limit locals 100");

    }

}
