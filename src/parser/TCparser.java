package parser;

import java.util.List;
import abstractSyntax.Printer;
import abstractSyntax.Program;
import symTable.SymTable;

public class TCparser
{
    private Integer index = 1;
    public List<TCscanner> tokens;
    public TCtoken tct;
    public TCscanner tok;
    public TCscanner prevTok;
    public Printer printer = new Printer();

    public TCparser(TCtoken tct)
    {
         this.tct = tct;
         tok = tct.getToken();
         //just there for last getNextToken call, ensures no out of bounds shit
    }

    public  TCscanner getNextToken()
    {
        prevTok = tok;
        tok = tct.getToken();
        index++;
        return tok;
    }

    public Integer getCurrentListLoc()
    {
        return tct.getCurrentListLoc();
    }

    public Integer getCurrentLineLoc()
    {
        return tct.getCurrentLineLoc();
    }

    public void toyCProgram() {
        new Program(this);
    }


    public boolean tokPrimaryCheck()
    {
        return (tok.getTok().equals(TCscanner.Tokens.ID) || tok.getTok().equals(TCscanner.Tokens.NUMBER) || tok.getTok().equals(TCscanner.Tokens.STRING)
                || tok.getTok().equals(TCscanner.Tokens.CHARLITERAL) || tok.getTok().equals(TCscanner.Tokens.LPAREN) ||tok.getTok().equals(TCscanner.Tokens.ADDOP)
                || tok.getTok().equals(TCscanner.Tokens.NOT));
    }

    public boolean tokStatementCheck()
    {
        return (tok.getTok().equals(TCscanner.Tokens.BREAK) || tok.getTok().equals(TCscanner.Tokens.LCURLY) || tok.getTok().equals(TCscanner.Tokens.IF)
                || tok.getTok().equals(TCscanner.Tokens.SEMICOLON) || tok.getTok().equals(TCscanner.Tokens.RETURN) || tok.getTok().equals(TCscanner.Tokens.WHILE)
                || tok.getTok().equals(TCscanner.Tokens.READ) || tok.getTok().equals(TCscanner.Tokens.WRITE) || tok.getTok().equals(TCscanner.Tokens.NEWLINE)
                || tok.getTok().equals(TCscanner.Tokens.ID));
    }
}











