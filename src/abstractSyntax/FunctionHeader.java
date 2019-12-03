/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import compiler.TCGlobals;
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
            parser.codegenerator.insert(new CGnothin(TCGlobals.sorryMark));
        }
        else
        {
            logError("missing '('");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.INT))
        {
            new FormalParamList(parser);
            CGFunction cgfun = new CGFunction(TCGlobals.sorryMark);
            parser.codegenerator.insert(cgfun);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.CHAR))
        {
            new FormalParamList(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consumes r paren
            CGFunction cgfun = new CGFunction(TCGlobals.sorryMark);
            parser.codegenerator.insert(cgfun);
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