package abstractSyntax;

import parser.*;

public class Program extends GrammarDef{
    Program(TCparser tcp) {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        parser.printer.println("prog(");
        parser.printer.indent();
        while(parser.tok.getTok().equals(TCscanner.Tokens.INT) || parser.tok.getTok().equals(TCscanner.Tokens.CHAR))
        {
            if (parser.tok.getTok().equals(TCscanner.Tokens.INT))
            {
                //Definition();
            }
            else if (parser.tok.getTok().equals(TCscanner.Tokens.CHAR))
            {
                //Definition();
            }
        }
        parser.printer.println(")");
        if(parser.tok.getTok().equals(TCscanner.Tokens.EOF))
        {
            parser.getNextToken();
            return;
        }

        else
        {
            logError("EOF character missing");
        }
    }
}
