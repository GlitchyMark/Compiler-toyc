package symTable;

public class SymbolNotFound extends Exception {
    public SymbolNotFound(String errorMessage) {
        super(errorMessage);
    }
}