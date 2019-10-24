package abstractSyntax;

import parser.*;

public abstract class GrammarDef {
    TCparser parser;
    boolean error = false;
    int errorpos;
    String errormsg;
    public GrammarDef(TCparser tcp)
    {
        parser = tcp;
        parseDefinition();
        System.out.println("[PARSER] " + toString());
        checkError();
    }

    abstract void parseDefinition();

    void checkError()
    {
        if(error)//print error here
        {
            parser.printer.printStack();
            for(int i = 0; errorpos > i; i++)
                System.out.print(" ");
            System.out.println("^ " + errormsg);
            System.exit(-1);
        }
    }

    public void logError(String msg)
    {
        error = true;
        errorpos = parser.printer.getStringLength();
        errormsg = msg;
    }
}
