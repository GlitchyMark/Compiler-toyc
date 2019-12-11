package codeGen.JVM;

import compiler.TCGlobals;
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
            if(target.symtable.isGlobal(id))
            {

                target.code.add("getstatic " + TCGlobals.className + "/" + id + " I");
                Symbol sym = target.symtable.getCurrentScope().find(id);
                //target.symtable.getCurrentScope().mostRecentlyAccessed(id);
            }
            else if(!target.funcMan.isFunc(id))
            {
                Symbol sym = target.symtable.getCurrentScope().find(id);
                //if(!TCGlobals.lazyCheck)
                target.code.add("iload " + sym.getOffset());
            }

        }
        catch(SymbolNotFound e)
        {
            e.printStackTrace();
        }
    }
}
