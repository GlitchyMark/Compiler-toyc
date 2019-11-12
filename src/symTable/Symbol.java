package symTable;
public class Symbol
{
    // Public Instance Variables
    public String name;
    public int type;
    public int offset;
    // Exceptions
    // Constructors
    public Symbol(){}
    public Symbol(String name){}
    public Symbol(String name,int offset){}
    public Symbol(String name,int offset, int type){}
    // Instance Methods
    public boolean equals(Symbol item){return false;};
    public String toString(){return "";}
}