/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

public class FunctionHeader extends GrammarDef{
    public FunctionHeader(TCparser tcp) {
        super(tcp);
    }

    @Override
    void parseDefinition() {
        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            parser.getNextToken();
        }
        else
        {
            logError("missing '('");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.INT))
        {
            new FormalParamList(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.CHAR))
        {
            new FormalParamList(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consumes r paren
            parser.getNextToken();
            return;
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            parser.getNextToken();
            return;
        }
        else
        {
            logError("missing ')'");
        }
    }

    @Override
    public String toString()
    {
        return "reducing FunctionHeader";
    }
}