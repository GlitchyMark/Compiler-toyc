package codeGen.JVM;

import symTable.Symbol;
import symTable.SymbolNotFound;

public class CGFunctionArgument extends CGInstruction{

    int index;
    public CGFunctionArgument(int index)
    {
        this.index = index;
    }

    void codeGen() {
        try {
            Symbol s = target.symtable.getFuncParam(index);
            target.code.add("istore " + s.getOffset());
        } catch(SymbolNotFound snf)
        {
            snf.printStackTrace();
        }
    }
}
