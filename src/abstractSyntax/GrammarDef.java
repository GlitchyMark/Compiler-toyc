package abstractSyntax;

import compiler.TCGlobals;
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
        if(TCGlobals.debugLevel >= 2)
            System.out.println("[PARSER] " + toString());
    }

    abstract void parseDefinition();

    void reportError()
    {
        //parser.printer.printStack();
        System.out.println("SYNTAX ERROR on(or near) line: " + lineNum);
        String nwErrorSrc = errorSrc.trim();
        System.out.println(nwErrorSrc);
        for(int i = 0; parser.getCurrentLineLoc() - (errorSrc.length() - nwErrorSrc.length()) > i; i++)
            System.out.print(" ");
        System.out.println("^ " + errormsg);
        System.exit(-1);
    }

    public void logError(String msg)
    {
        lineNum = parser.getCurrentListLoc();
        errorSrc = parser.tct.getPreTokens().get(lineNum);
        errormsg = msg;
        reportError();
    }
}
