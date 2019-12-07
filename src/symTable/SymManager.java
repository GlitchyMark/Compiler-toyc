package symTable;

import compiler.TCGlobals;

import java.util.ArrayList;
import java.util.List;

public class SymManager
{
    List<SymTable> syms = new ArrayList<>();
    SymTable currentScope;

    public SymManager()
    {
        currentScope = new SymTable();
        syms.add(currentScope);
    }

    public SymTable getCurrentScope()
    {
        return currentScope;
    }

    public int getOffset()
    {
        return currentScope.getOffset();
    }

    public boolean isGlobal(String varName)
    {
        for(String s: TCGlobals.sorryAgain)
        {
            if(s.equals(varName))
                return true;
        }
        return false;
    }

    public SymTable getScopeName(String name)
    {
        for(SymTable s : syms)
        {
            if(s.name.equals(name))
                return s;
        }
        return new SymTable();
    }

    public void setNewCurrentScope(String name)
    {
        currentScope = new SymTable();
        syms.add(currentScope);
        currentScope.setName(name);
    }



}
