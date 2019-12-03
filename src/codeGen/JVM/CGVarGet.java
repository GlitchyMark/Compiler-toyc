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
        System.out.println("asdf");
        try
        {
            if(target.symtable.isGlobal(id))
            {
                target.code.add("getstatic test/" + id + " I");
            }
            else
            {
                Symbol sym = target.symtable.getCurrentScope().find(id);
                target.code.add("iload " + sym.getOffset());
            }

        }
        catch(SymbolNotFound e)
        {
            e.printStackTrace();
        }
    }
}
