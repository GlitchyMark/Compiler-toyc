package abstractSyntax;

import parser.*;

public class ExpressionStatement extends GrammarDef
{
    public ExpressionStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        parser.printer.println("exprState(");
        parser.printer.indent();
        if(parser.tokPrimaryCheck())
        {
            new Expression(parser);
        }
        else
        {
            logError("expression expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolon
            parser.getNextToken();
            return;
        }
        else
        {
            logError("semicolon expected");
        }
        parser.printer.outdent();
        parser.printer.println(")");
    }

    @Override
    public String toString()
    {
        return "reducing ExpressionStatement";
    }
}
