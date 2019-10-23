package abstractSyntax;

import parser.*;

public class Statement extends GrammarDef
{
    public Statement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        parser.printer.println("Definition(");
        parser.printer.indent();
        if(parser.tok.getTok().equals(TCscanner.Tokens.BREAK))
        {
            //breakStatement();
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
        {
            //expressionStatement();
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.LCURLY) )
        {
            //compoundStatement();
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.IF))
        {
            //ifStatement();
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //nullStatement();
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.RETURN))
        {
            //returnStatement();
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.WHILE))
        {
            //whileStatement();
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.READ))
        {
            //readStatement();
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.WRITE))
        {
            //writeStatement();
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.NEWLINE))
        {
            //newlineStatement();
        }
        else
        {
            logError("No statement");
        }
        parser.printer.println(")");
    }
}
