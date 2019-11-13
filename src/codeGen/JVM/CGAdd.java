package codeGen.JVM;

import symTable.Symbol;
import symTable.SymbolAlreadyDeclared;

public class CGAdd extends CGInstruction
{

    void codeGen()
    {
        target.code.add("iadd");
    }
}
