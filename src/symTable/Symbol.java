package symTable;
public class Symbol
{
    // Public Instance Variables
    private String name;
    private String type;
    private int offset;

    //enum TYPES{INT, FUNC };

    public Symbol(){}

    public Symbol(String name)
    {
        this.name = name;
    }

    public Symbol(String name,int offset)
    {
        this.name = name;
        this.offset = offset;
    }

    public Symbol(String name, String type)
    {
        this.name = name;
        this.type = type;
    }

    public Symbol(String name,int offset, String type)
    {
        this.name = name;
        this.offset = offset;
        this.type = type;
    }

    //get n set
    public String getName()
    {
        return name;
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