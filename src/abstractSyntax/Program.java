package abstractSyntax;

import parser.*;

public class Program extends GrammarDef{
    Program(TCparser tcp) {
        super(tcp);
    }

    void parseDefinition()
    {
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
