/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package symTable;

import java.util.HashMap;

public class SymTable{

    HashMap<String, Symbol> localSymTable = new HashMap<>();

    SymTable owner;
    boolean hasOwner;
    String labelName;
    Integer offsetCount = 1;
    Symbol lastaccessedsym;
    // Constructor
    public SymTable()
    {
        labelName = "main";
        hasOwner = false;
    }

    public SymTable(SymTable owner, String labelName)
    {
        this.owner = owner;
        this.labelName = labelName;
        hasOwner = true;
    }

    // Instance Methods
    public Symbol insert(String id, String type) throws SymbolAlreadyDeclared
    {

        if(!localSymTable.containsKey(id))
        {
            lastaccessedsym = new Symbol(id, offsetCount, type);
            localSymTable.put(lastaccessedsym.getName(), lastaccessedsym);
            offsetCount++;
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
            lastaccessedsym = new Symbol(id, offsetCount);
            localSymTable.put(lastaccessedsym.getName(), lastaccessedsym);
            offsetCount++;
            return lastaccessedsym;
        }
        else
        {
            String error = "SYMBOL ALREADY DECLARED";
            System.out.println(error);
            throw new SymbolAlreadyDeclared(error);
        }
    }

    public Symbol find(String id) throws SymbolNotFound
    {
        if(localSymTable.containsKey(id))
            lastaccessedsym = localSymTable.get(id);
        else if(hasOwner)
            lastaccessedsym = owner.find(id);
        else {
            System.out.println("Symbol with ID: " + id + " does not exist in symbol table");
            lastaccessedsym = new Symbol();
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
        return local;
    }

    public SymTable goDown()
    {
        return owner;
    }

    public String toString()
    {
        return "";
    }
}