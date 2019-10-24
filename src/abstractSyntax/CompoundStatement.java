package abstractSyntax;

import parser.*;

public class CompoundStatement extends GrammarDef
{
    public CompoundStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        String buffer = "";

        parser.printer.println("blockState(");
        parser.printer.indent();
        if(parser.tok.getTok().equals(TCscanner.Tokens.LCURLY))
        {
            //consume l curly
            parser.getNextToken();
        }
        else
        {
            logError("{ expected");
        }

        while(parser.tok.getTok().equals(TCscanner.Tokens.INT) || parser.tok.getTok().equals(TCscanner.Tokens.CHAR))
        {
            if(parser.tok.getTok().equals(TCscanner.Tokens.INT))
            {
                buffer = "int";
                new Type(parser);
            }
            else if(parser.tok.getTok().equals(TCscanner.Tokens.CHAR))
            {
                buffer = "char";
                new Type(parser);
            }
            else
            {
                logError("");
            }

            if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
            {
                buffer = parser.tok.getLex() + ", " + buffer;
                parser.printer.print("varDef(" + buffer + ")");
                //consume ID
                parser.getNextToken();
            }
            else
            {
                logError("");
            }

            if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
            {
                //consume semicolon
                parser.getNextToken();
            }
            else
            {
                logError("';' expected");
            }
            parser.printer.outdent();
            parser.printer.println(")");
        }

        if(parser.tokStatementCheck())
            parser.printer.println(",");

        while(parser.tokStatementCheck())
        {
            new Statement(parser);

            if(parser.tokStatementCheck())
                parser.printer.println(",");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RCURLY))
        {
            //consume r curly
            parser.getNextToken();
        }
        else
        {
            logError("} expected");
        }
    }

    @Override
    public String toString()
    {
        return "reducing CompoundStatement";
    }
}