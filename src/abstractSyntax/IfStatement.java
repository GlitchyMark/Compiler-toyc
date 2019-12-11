/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import compiler.TCGlobals;
import parser.*;
import codeGen.JVM.*;

public class IfStatement extends GrammarDef
{
    public IfStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        parser.printer.print("ifState(");
        if(parser.tok.getTok().equals(TCscanner.Tokens.IF))
        {
            //consume if
            parser.getNextToken();
        }
        else
        {
            logError("");
        }

        CGIfCmp ifCmp = new CGIfCmp();
        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume lparen
            parser.getNextToken();
            parser.printer.print("expr(");
            TCGlobals.lazyCheck = true;
            new Expression(parser);
            parser.printer.print("),");
            parser.codegenerator.insert(ifCmp);
        }
        else
        {
            logError("'(' expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            parser.getNextToken();
            TCGlobals.lazyCheck = false;
            new Statement(parser);
            parser.codegenerator.insert(new CGIfGoto(ifCmp.getLabelY()));
            //goto LabelY here

            parser.codegenerator.insert(new CGIfLabelX(ifCmp.getLabelX()));
            //LabelX goes here
        }
        else
        {
            logError("')' expected");
        }
        //or here, doesn't super matter

        if(parser.tok.getTok().equals(TCscanner.Tokens.ELSE))
            parser.printer.print(",");
        else
            parser.printer.print("");

        if(parser.tok.getTok().equals(TCscanner.Tokens.ELSE))
        {
            //consume else
            parser.getNextToken();
            if(parser.tokStatementCheck())
            {
                new Statement(parser);
            }
        }
        parser.codegenerator.insert(new CGIfLabelY(ifCmp.getLabelY()));
        parser.printer.print(")");
        //LabelY goes here

        return;
    }

    @Override
    public String toString()
    {
        return "reducing IfStatement";
    }
}
