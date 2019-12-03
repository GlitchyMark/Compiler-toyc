package symTable;

import codeGenerator.SymbolTable;

public class Symbol
{
    // Public Instance Variables
    private String name;
    private String type;
    private int offset;
    SymTable symT;

    //enum TYPES{INT, FUNC };

    public Symbol(SymTable symT){
        this.symT = symT;
    }

    public Symbol(String name, SymTable symT)
    {
        this.name = name;
        this.symT = symT;
    }

    public Symbol(String name,int offset,SymTable symT)
    {
        this.name = name;
        this.offset = offset;
        this.symT = symT;
    }

    public Symbol(String name, String type, SymTable symT)
    {
        this.name = name;
        this.type = type;
        this.symT = symT;
    }

    public Symbol(String name,int offset, String type, SymTable symT)
    {
        this.name = name;
        this.offset = offset;
        this.type = type;
        this.symT = symT;
    }

    //get n set
    public String getName()
    {
        return name;
    }

    public SymTable getSymT()
    {
        return symT;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getOffset()
    {
        return  offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Instance Methods
    public boolean equals(Symbol item)
    {
        return false;
    }

    public String toString(){return "";}
}