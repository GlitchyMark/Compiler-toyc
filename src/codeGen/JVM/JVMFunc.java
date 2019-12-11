package codeGen.JVM;

public class JVMFunc
{
    String funcName;
    int paramCount;
    String formatted;

    public JVMFunc(String funcName, int paramCount)
    {
        this.funcName = funcName;
        this.paramCount = paramCount;
        formatted = funcName + "(";
        for(int i=paramCount; i>0; i--)
            formatted += "I";
        formatted += ")I";
    }

    public String getFormatted()
    {
        return formatted;
    }

    public String getFuncName()
    {
        return funcName;
    }
}
