package abstractSyntax;

import parser.*;

public class ReadStatement extends GrammarDef
{
    public ReadStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        parser.printer.println("readState(");
        parser.printer.indent();
        if(parser.tok.getTok().equals(TCscanner.Tokens.READ))
        {
            //consume read
            parser.getNextToken();
        }
        else
        {
            logError("");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume lparen
            parser.getNextToken();
        }
        else
        {
            logError("( expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
        {
            parser.printer.print(parser.tok.getLex());
            //consume ID
            parser.getNextToken();
            while(parser.tok.getTok().equals(TCscanner.Tokens.COMMA))
            {
                parser.printer.println(",");
                parser.printer.print(parser.tok.getLex());
                //consume comma
                parser.getNextToken();
                if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
                {
                    //consume ID
                    parser.getNextToken();
                }
                else
                {
                    logError("ID expected");
                }
            }
            parser.printer.println("");
        }
        else
        {
            logError("ID expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume Rparen
            parser.getNextToken();
        }
        else
        {
            logError(") expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolon
            parser.getNextToken();
        }
        else
        {
            logError("; expected");
        }
        parser.printer.outdent();
        parser.printer.print(")");
    }

    @Override
    public String toString()
    {
        return "reducing ReadStatement";
    }
}
