/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import compiler.TCGlobals;
import parser.*;
import codeGen.JVM.*;

public class Definition extends GrammarDef {
    public Definition(TCparser tcp) {
        super(tcp);
    }
    String buffer;
    String name;
    @Override
    void parseDefinition() {
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
            buffer = "NULL";
            logError("");
        }
        TCGlobals.sorryMark = parser.tok.getLex();
        name = parser.tok.getLex();

        if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
        {
            buffer = parser.tok.getLex() + ", " + buffer;
            //consumes ID
            parser.getNextToken();
        }
        else
        {
            logError("missing id");
        }


        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            parser.printer.print("funcDef(" + buffer + ", ");
            new FunctionDefinition(parser);
            parser.printer.print(")");
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            parser.printer.print("varDef(" + buffer + ")");
            parser.codegenerator.insert(new CGGlobalVar(name));
            //Consumes semicolon
            parser.getNextToken();
            return;
        }
        else
        {
            logError("missing '(' or ';'");
        }
    }

    @Override
    public String toString()
    {
        return "reducing Definition";
    }
}
