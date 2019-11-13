/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.TCparser;

public class Type extends GrammarDef {
    public Type(TCparser tcp) {
        super(tcp);
    }

    @Override
    void parseDefinition() {

        parser.getNextToken();
    }

    @Override
    public String toString()
    {
        return "reducing Type";
    }
}
