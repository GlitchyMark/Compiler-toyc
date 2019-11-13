package codeGen.JVM;

import symTable.Symbol;
import symTable.SymbolNotFound;

public class CGVarGet extends CGInstruction
{
    String id;
    public CGVarGet(String id)
    {
        this.id = id;
    }

    void codeGen()
    {
        try
        {
            Symbol sym = target.symtable.find(id);
            target.code.add("iload " + sym.getOffset());
        }
        catch(SymbolNotFound e)
        {
            e.printStackTrace();
        }
    }
}
