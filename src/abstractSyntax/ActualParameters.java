package abstractSyntax;

import parser.*;

public class ActualParameters extends GrammarDef
{
    public ActualParameters(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tokPrimaryCheck())
        {
            parser.printer.println(",");
            parser.printer.println("expr(");
            new Expression(parser);
            parser.printer.print(")");
            while(parser.tok.getTok().equals(TCscanner.Tokens.COMMA))
            {
                //consume comma
                parser.getNextToken();
                if(parser.tokPrimaryCheck())
                {
                    parser.printer.println(",");
                    parser.printer.println("expr(");
                    new Expression(parser);
                    parser.printer.print(")");
                }
                else
                {
                    logError(", expected");
                }
            }
        }
    }

    @Override
    public String toString()
    {
        return "reducing ActualParameters";
    }
}
