/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;

public class Program extends GrammarDef{
    public Program(TCparser tcp) {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        parser.printer.print("prog(");
        while(parser.tok.getTok().equals(TCscanner.Tokens.INT) || parser.tok.getTok().equals(TCscanner.Tokens.CHAR))
        {
            if (parser.tok.getTok().equals(TCscanner.Tokens.INT))
            {
                new Definition(parser);
            }
            else if (parser.tok.getTok().equals(TCscanner.Tokens.CHAR))
            {
                new Definition(parser);
            }
        }
        parser.printer.print(")");
        if(parser.tok.getTok().equals(TCscanner.Tokens.EOF))
        {
            parser.getNextToken();
            return;
        }

        else
        {
            logError("EOF token missing");
        }
    }

    @Override
    public String toString()
    {
        return "reducing ToyCProgram";
    }
}
