package abstractSyntax;

import parser.*;

public class ReturnStatement extends GrammarDef
{
    public ReturnStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        parser.printer.print("returnState(");
        if(parser.tok.getTok().equals(TCscanner.Tokens.RETURN))
        {
            //consume return
            parser.getNextToken();
        }
        else
        {
            logError("return expected");
        }

        if(parser.tokPrimaryCheck())
        {
            parser.printer.println("expr(");
            new Expression(parser);
            parser.printer.print(")");
        }
        if (parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolon
            parser.getNextToken();
            return;
        }
        else
        {
            logError("; expected");
        }
        parser.printer.print(")");
    }

    @Override
    public String toString()
    {
        return "reducing ReturnStatement";
    }
}
