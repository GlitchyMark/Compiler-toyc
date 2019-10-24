/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;

public class FunctionDefinition extends GrammarDef {
    public FunctionDefinition(TCparser tcp) {
        super(tcp);
    }

    @Override
    void parseDefinition() {
        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            new FunctionHeader(parser);
        }
        else
        {
            logError("missing '('");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.LCURLY))
        {
            new FunctionBody(parser);
            return;
        }
        else
        {
            logError("missing '{'");
        }
    }

    @Override
    public String toString()
    {
        return "reducing FunctionDefinition";
    }
}
