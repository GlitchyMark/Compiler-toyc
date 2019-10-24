package abstractSyntax;

import parser.*;

public abstract class GrammarDef {
    TCparser parser;
    String errorSrc;
    String errormsg;
    Integer lineNum;
    public GrammarDef(TCparser tcp)
    {
        parser = tcp;
        parseDefinition();
        System.out.println("[PARSER] " + toString());
    }

    abstract void parseDefinition();

    void reportError()
    {
        //parser.printer.printStack();
        System.out.println("SYNTAX ERROR on(or near) line: " + lineNum);
        System.out.println(errorSrc.trim());
        for(int i = 0; parser.getCurrentLineLoc() > i; i++)
            System.out.print(" ");
        System.out.println("^ " + errormsg);
        System.exit(-1);
    }

    public void logError(String msg)
    {
        lineNum = parser.getCurrentListLoc();
        errorSrc = parser.getCurrentLine();
        errormsg = msg;
        reportError();
    }
}
