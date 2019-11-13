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
        Symbol sym = new Symbol(id, type);
        localSymTable.put(sym.getName(), sym);
        return sym;
    }

    public Symbol insert(String id) throws SymbolAlreadyDeclared
    {
        Symbol sym = new Symbol(id);
        localSymTable.put(sym.getName(), sym);
        return sym;
    }

    public Symbol find(String id) throws SymbolNotFound
    {
        Symbol sym;
        if(localSymTable.containsKey(id))
            sym = localSymTable.get(id);
        else if(hasOwner)
            sym = owner.find(id);
        else {
            System.out.println("Symbol with ID: " + id + " does not exist in symbol table");
            sym = new Symbol();
        }
        return sym;
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