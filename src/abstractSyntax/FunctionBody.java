/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

public class FunctionBody extends GrammarDef{
    public FunctionBody(TCparser tcp) {
        super(tcp);
    }

    @Override
    void parseDefinition() {
        if (parser.tok.getTok().equals(TCscanner.Tokens.LCURLY)) {
            new CompoundStatement(parser);
            return;
        } else {
            logError("missing '{'");
        }
    }

    @Override
    public String toString()
    {
        return "reducing FunctionBody";
    }
}
