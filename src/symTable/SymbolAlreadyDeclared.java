package symTable;

public class SymbolAlreadyDeclared extends Exception {
    public SymbolAlreadyDeclared(String errorMessage) {
        super(errorMessage);
    }
}