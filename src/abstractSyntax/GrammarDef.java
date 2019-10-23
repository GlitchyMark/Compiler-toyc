package abstractSyntax;

import parser.TCparser;

public abstract class GrammarDef {
    TCparser parser;
    boolean error = false;
    int errorpos;
    String errormsg;
    public GrammarDef(TCparser tcp)
    {
        parser = tcp;
        parseDefinition();
        checkError();
    }

    abstract void parseDefinition();

    void checkError()
    {
        if(error)//print error here
        {
            for(int i = 0; errorpos > i; i++)
                System.out.print(" ");
            System.out.println("^ " + errormsg);
        }
    }

    public void logError(String msg)
    {
        error = true;
        errorpos = parser.printer.getStringLength();
        errormsg = errormsg;
    }
}
