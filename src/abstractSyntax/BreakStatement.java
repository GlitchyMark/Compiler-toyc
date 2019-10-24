package abstractSyntax;

import parser.*;

public class BreakStatement extends GrammarDef
{
    public BreakStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tok.getTok().equals(TCscanner.Tokens.BREAK))
        {
            //consume break
            parser.printer.println("breakState()");
            parser.getNextToken();
        }
        else
        {
            logError("break statement expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolon
            parser.getNextToken();
        }
        else
        {
            logError("semicolon expected");
        }
        return;
    }

    @Override
    public String toString()
    {
        return "reducing BreakStatement";
    }
}