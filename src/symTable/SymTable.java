/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package symTable;

import compiler.TCGlobals;

import javax.swing.plaf.synth.SynthButtonUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymTable{

    HashMap<String, Symbol> localSymTable = new HashMap<>();

    public static List<SymTable> symbolTables = new ArrayList<SymTable>();

    SymTable owner;
    String name = "";
    boolean hasOwner;
    String labelName;
    Integer offsetCount = 0;
    static SymTable previousTable;
    Integer localOffset;
    Integer loc;
    private Symbol lastaccessedsym;
    // Constructor
    public SymTable()
    {
        labelName = "main";
        hasOwner = false;
        localOffset = offsetCount;
        loc = 0;
    }

    public SymTable(SymTable owner, String labelName)
    {
        this.owner = owner;
        this.labelName = labelName;
        localOffset = offsetCount;
        loc = 0;
        hasOwner = true;
    }
    public SymTable getPreviousTable()
    {
        return previousTable;
    }
    // Instance Methods
    public Symbol insert(String id, String type) throws SymbolAlreadyDeclared
    {

        if(!localSymTable.containsKey(id))
        {
            lastaccessedsym = new Symbol(id, offsetCount, type, this);
            localSymTable.put(lastaccessedsym.getName(), lastaccessedsym);
            offsetCount++;
            loc++;
            return lastaccessedsym;
        }
        else
            {
            String error = "SYMBOL ALREADY DECLARED";
            System.out.println(error);
            throw new SymbolAlreadyDeclared(error);
        }
    }

    public Symbol insert(String id) throws SymbolAlreadyDeclared
    {
        if(!localSymTable.containsKey(id))
        {
            lastaccessedsym = new Symbol(id, offsetCount, this);
            localSymTable.put(lastaccessedsym.getName(), lastaccessedsym);
            offsetCount++;
            loc++;
            return lastaccessedsym;
        }
        else
        {
            String error = "SYMBOL ALREADY DECLARED";
            System.out.println(error);
            throw new SymbolAlreadyDeclared(error);
        }
    }

    public int getOffset()
    {
        return offsetCount;
    }

    public SymTable getSymTable(String lbl) throws SymbolNotFound
    {
        previousTable = this;
        for(SymTable symt : symbolTables)
        {
            if(symt.equals(lbl))
            {
                return symt;
            }
        }
        throw new SymbolNotFound("Table not found!");
    }

    public Symbol getFuncParam(int i) throws SymbolNotFound
    {
        for(Map.Entry<String, Symbol> sym : localSymTable.entrySet())
        {
            if(sym.getValue().getOffset() - localOffset == i)
                return sym.getValue();
        }
        throw new SymbolNotFound("SYMBOL NOT FOUND");
    }

    public Symbol find(String id) throws SymbolNotFound
    {
        if(TCGlobals.sorryAgain.contains(id)) {
            lastaccessedsym = new Symbol("GLOBAL" + id, this);
            return lastaccessedsym;
        }
        if(localSymTable.containsKey(id))
            lastaccessedsym = localSymTable.get(id);
        else if(hasOwner)
            lastaccessedsym = owner.find(id);
        else {
            System.out.println("Symbol with ID: " + id + " does not exist in symbol table");
            lastaccessedsym = new Symbol(this);
        }
        return lastaccessedsym;
    }

    public Symbol getLastAccessedSym()
    {
        return lastaccessedsym;
    }
    public SymTable addScope(String labelName)
    {
        SymTable local = new SymTable(this, labelName);
        symbolTables.add(local);
        return local;
    }

    public SymTable goUp()
    {
        return owner;
    }

    public int getLocalOffset()
    {
        return loc;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void mostRecentlyAccessed(String id)
    {
        try {
            find(id);
        }
        catch(SymbolNotFound e) {}
    }

    public String toString()
    {
        return "";
    }
}