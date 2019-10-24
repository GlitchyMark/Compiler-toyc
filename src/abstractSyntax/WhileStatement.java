/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;

public class WhileStatement extends GrammarDef
{
    public WhileStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        parser.printer.print("whileState(");
        parser.printer.indent();
        if(parser.tok.getTok().equals(TCscanner.Tokens.WHILE))
        {
            //consume while
            parser.getNextToken();
        }
        else
        {
            logError("while expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume lparen
            parser.getNextToken();
            parser.printer.print("expr(");
            new Expression(parser);
            parser.printer.print("),");
        }
        else
        {
            logError("( expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            parser.getNextToken();
            new Statement(parser);
        }
        else
        {
            logError(") expected");
        }
        parser.printer.print(")");
    }

    @Override
    public String toString()
    {
        return "reducing WhileStatement";
    }
}
