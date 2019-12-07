package codeGen.JVM;

import symTable.Symbol;
import symTable.SymbolAlreadyDeclared;
import symTable.SymbolNotFound;

public class CGReadIn extends CGInstruction
{
    String id;
    public CGReadIn(String id)
    {
        this.id = id;
    }

    void codeGen()
    {
        try
        {
            Symbol sym = target.symtable.getCurrentScope().insert("STRING " + target.symtable.getOffset());
            target.code.add("new java/util/Scanner");
            target.code.add("dup");
            target.code.add("getstatic java/lang/System/in Ljava/io/InputStream;");
            target.code.add("invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V");
            target.code.add("astore " + sym.getOffset());
            target.code.add("aload " + sym.getOffset());

        }
        catch(SymbolAlreadyDeclared e)
        {
            e.printStackTrace();
        }

        target.code.add("invokevirtual java/util/Scanner/next()Ljava/lang/String;");
        target.code.add("invokestatic java/lang/Integer/parseInt(Ljava/lang/String;)I");

        try
        {
            if(target.symtable.isGlobal(id))
            {

                target.code.add("putstatic test/" + id + " I");
                Symbol sym = target.symtable.getCurrentScope().find(id);
                //target.symtable.getCurrentScope().mostRecentlyAccessed(id);
            }
            else
            {
                Symbol sym = target.symtable.getCurrentScope().find(id);
                target.code.add("istore " + sym.getOffset());
            }

        }
        catch(SymbolNotFound e)
        {
            e.printStackTrace();
        }
    }
}
