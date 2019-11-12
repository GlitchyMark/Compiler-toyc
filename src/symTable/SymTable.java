/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package symTable;

public class SymTable{
    // Constructor
    public SymTable() {};
    // Instance Methods
    public Symbol insert(String id,int type) throws SymbolAlreadyDeclared { return null;};
    public Symbol insert(String id) throws SymbolAlreadyDeclared { return null;};
    public Symbol find(String id) throws SymbolNotFound { return null;};
    public String toString() { return "";};
}