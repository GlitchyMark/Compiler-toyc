package parser;

import parser.TCscanner;

import java.util.List;

public class TCparser
{
    private Integer index = 1;
    List<TCscanner> tokens;
    TCscanner tok;


    public TCparser(List<TCscanner> tokens)
    {
        this.tokens = tokens;
         tok = tokens.get(0);
         //just there for last getNextToken call, ensures no out of bounds shit
         tokens.add(null);
    }

    public  TCscanner getNextToken()
    {
        tok = tokens.get(index);
        index++;
        return tok;
    }

    //TODO: Stretched to thin right now, but for all that is holy,
    //TODO: at some point soon, make a better boolean check than this
    public void toyCProgram()
    {
        while(tok.getTok().equals(TCscanner.Tokens.INT) || tok.getTok().equals(TCscanner.Tokens.CHAR))
        {
            if (tok.getTok().equals(TCscanner.Tokens.INT))
            {
                definition();
            }
            else if (tok.getTok().equals(TCscanner.Tokens.CHAR))
            {
                definition();
            }
        }
        if(tok.getTok().equals(TCscanner.Tokens.EOF))
        {
            getNextToken();
            return;
        }

        else
        {
            logError();
        }
    }

    public void definition()
    {
        if(tok.getTok().equals(TCscanner.Tokens.INT))
        {
            type();
        }
        else if(tok.getTok().equals(TCscanner.Tokens.CHAR))
        {
            type();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.ID))
        {
            //consumes ID
            getNextToken();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            functionDefinition();
        }
        else if(tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //Consumes semicolon
            getNextToken();
            return;
        }
        else
        {
            logError();
        }

    }

    public void type()
    {
        if(tok.getTok().equals(TCscanner.Tokens.INT))
        {
            //consumes int token
            getNextToken();
            return;
        }
        else if(tok.getTok().equals(TCscanner.Tokens.CHAR))
        {
            //consumes char token
            getNextToken();
            return;
        }
        else
        {
            logError();
        }
    }

    public void functionDefinition()
    {
        if(tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            functionHeader();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.LCURLY))
        {
            functionBody();
            return;
        }
        else
        {
            logError();
        }

    }

    public void functionHeader()
    {
        if(tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            getNextToken();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.INT))
        {
            formalParamList();
        }
        else if(tok.getTok().equals(TCscanner.Tokens.CHAR))
        {
            formalParamList();
        }
        else if(tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consumes r paren
            getNextToken();
            return;
        }

        if(tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            getNextToken();
            return;
        }
        else
        {
            logError();
        }
    }

    public void functionBody() {
        if (tok.getTok().equals(TCscanner.Tokens.LCURLY)) {
            compoundStatement();
            return;
        } else {
            logError();
        }
    }

    public void formalParamList() {
        if (tok.getTok().equals(TCscanner.Tokens.INT)) {
            type();
        } else if (tok.getTok().equals(TCscanner.Tokens.CHAR)) {
            type();
        } else {
            logError();
        }

        if (tok.getTok().equals(TCscanner.Tokens.ID)) {
            //consumes identifier
            getNextToken();
        } else {
            logError();
        }

        while (tok.getTok().equals(TCscanner.Tokens.COMMA)) {
            if (tok.getTok().equals(TCscanner.Tokens.COMMA)) {
                //consume comma
                getNextToken();
            } else {
                logError();
            }

            if (tok.getTok().equals(TCscanner.Tokens.INT)) {
                type();
            } else if (tok.getTok().equals(TCscanner.Tokens.CHAR)) {
                type();
            } else {
                logError();
            }

            if (tok.getTok().equals(TCscanner.Tokens.ID)) {
                //consume ID
                getNextToken();
            }
        }
    }

    public void compoundStatement()
    {
        if(tok.getTok().equals(TCscanner.Tokens.LCURLY))
        {
            //consume l curly
            getNextToken();
        }
        else
        {
            logError();
        }

        while(tok.getTok().equals(TCscanner.Tokens.INT) || tok.getTok().equals(TCscanner.Tokens.CHAR))
        {
            if(tok.getTok().equals(TCscanner.Tokens.INT))
            {
                type();
            }
            else if(tok.getTok().equals(TCscanner.Tokens.CHAR))
            {
                type();
            }
            else
            {
                logError();
            }

            if(tok.getTok().equals(TCscanner.Tokens.ID))
            {
                //consume ID
                getNextToken();
            }
            else
            {
                logError();
            }

            if(tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
            {
                //consume semicolon
                getNextToken();
            }
            else
            {
                logError();
            }
        }

        while(tokStatementCheck())
        {
            statement();
        }

        if(tok.getTok().equals(TCscanner.Tokens.RCURLY))
        {
            //consume r curly
            getNextToken();
        }
    }

    public void statement()
    {
        if(tok.getTok().equals(TCscanner.Tokens.BREAK))
        {
            breakStatement();
        }
        else if(tok.getTok().equals(TCscanner.Tokens.ID))
        {
            expressionStatement();
        }
        else if(tok.getTok().equals(TCscanner.Tokens.LCURLY) )
        {
            compoundStatement();
        }
        else if(tok.getTok().equals(TCscanner.Tokens.IF))
        {
            ifStatement();
        }
        else if(tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            nullStatement();
        }
        else if(tok.getTok().equals(TCscanner.Tokens.RETURN))
        {
            returnStatement();
        }
        else if(tok.getTok().equals(TCscanner.Tokens.WHILE))
        {
            whileStatement();
        }
        else if(tok.getTok().equals(TCscanner.Tokens.READ))
        {
            readStatement();
        }
        else if(tok.getTok().equals(TCscanner.Tokens.WRITE))
        {
            writeStatement();
        }
        else if(tok.getTok().equals(TCscanner.Tokens.NEWLINE))
        {
            newlineStatement();
        }
        else
        {
            logError();
        }
    }

    public void expressionStatement()
    {
        if(tokPrimaryCheck())
        {
            expression();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolon
            getNextToken();
            return;
        }
        else
        {
            logError();
        }
    }

    public void expression()
    {
        if(tokPrimaryCheck())
        {
            relopExpression();
        }
        else
        {
            logError();
        }

        while(tok.getTok().equals(TCscanner.Tokens.ASSIGNOP))
        {
            if(tok.getTok().equals(TCscanner.Tokens.ASSIGNOP))
            {
                //consume assignop
                getNextToken();
                relopExpression();
            }
            else
            {
                logError();
            }
        }
        return;
    }

    public void relopExpression()
    {
        if(tokPrimaryCheck())
        {
            simpleExpression();
        }
        else
        {
            logError();
        }

        while(tok.getTok().equals(TCscanner.Tokens.RELOP))
        {
            if(tok.getTok().equals(TCscanner.Tokens.RELOP))
            {
                //consume relop
                getNextToken();
                simpleExpression();
            }
            else
            {
                logError();
            }
        }
        return;
    }

    public void simpleExpression()
    {
        if(tokPrimaryCheck())
        {
            term();
        }
        else
        {
            logError();
        }

        while(tok.getTok().equals(TCscanner.Tokens.ADDOP))
        {
            if(tok.getTok().equals(TCscanner.Tokens.ADDOP))
            {
                //consume addop
                getNextToken();
                term();
            }
            else
            {
                logError();
            }
        }
        return;
    }

    public void term()
    {
        if(tokPrimaryCheck())
        {
            primary();
        }
        else
        {
            logError();
        }

        while(tok.getTok().equals(TCscanner.Tokens.MULOP))
        {
            if(tok.getTok().equals(TCscanner.Tokens.MULOP))
            {
                //consume mulop
                getNextToken();
                primary();
            }
            else
            {
                logError();
            }
        }
        return;
    }

    public void primary()
    {
        if(tok.getTok().equals(TCscanner.Tokens.ID))
        {
            //consumes identifier
            getNextToken();
            //TODO: finish this loose thread
            if(tok.getTok().equals(TCscanner.Tokens.LPAREN))
            {
                functionCall();
            }
        }
        else if(tok.getTok().equals(TCscanner.Tokens.NUMBER))
        {
            //consume number
            getNextToken();
            return;
        }
        else if(tok.getTok().equals(TCscanner.Tokens.STRING))
        {
            //consume string
            getNextToken();
            return;
        }
        else if(tok.getTok().equals(TCscanner.Tokens.CHARLITERAL))
        {
            //consume char
            getNextToken();
            return;
        }
        else if(tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume left paren
            getNextToken();
            expression();

            if (tok.getTok().equals(TCscanner.Tokens.RPAREN))
            {
                //consume right paren
                getNextToken();
                return;
            }
            else
            {
                logError();
            }
        }
        else if(tok.getTok().equals(TCscanner.Tokens.ADDOP))
        {
            //consume addop
            getNextToken();
            primary();
            return;
        }
        else if(tok.getTok().equals(TCscanner.Tokens.NOT))
        {
            //consume not
            getNextToken();
            primary();
            return;
        }
        else
        {
            logError();
        }
    }

    public void breakStatement()
    {
        if(tok.getTok().equals(TCscanner.Tokens.BREAK))
        {
            //consume break
            getNextToken();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolon
            getNextToken();
        }
        else
        {
            logError();
        }
        return;
    }

    public void ifStatement()
    {
        if(tok.getTok().equals(TCscanner.Tokens.IF))
        {
            //consume if
            getNextToken();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume lparen
            getNextToken();
            expression();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            getNextToken();
            statement();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.ELSE))
        {
            //consume else
            getNextToken();
            if(tokStatementCheck())
            {
                statement();
            }
        }
        return;
    }

    public void nullStatement()
    {
        if(tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolon
            getNextToken();
        }
        else
        {
            logError();
        }
    }

    public void returnStatement()
    {
        if(tok.getTok().equals(TCscanner.Tokens.RETURN))
        {
            //consume return
            getNextToken();
        }
        else
        {
            logError();
        }

        if(tokPrimaryCheck())
        {
            expression();
        }
        if (tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolon
            getNextToken();
            return;
        }
        else
        {
            logError();
        }
    }

    public void whileStatement()
    {
        if(tok.getTok().equals(TCscanner.Tokens.WHILE))
        {
            //consume while
            getNextToken();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume lparen
            getNextToken();
            expression();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            getNextToken();
            statement();
        }
        else
        {
            logError();
        }
    }

    public void readStatement()
    {
        if(tok.getTok().equals(TCscanner.Tokens.READ))
        {
            //consume read
            getNextToken();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume lparen
            getNextToken();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.ID))
        {
            //consume ID
            getNextToken();
            while(tok.getTok().equals(TCscanner.Tokens.COMMA))
            {
                //consume comma
                getNextToken();
                if(tok.getTok().equals(TCscanner.Tokens.ID))
                {
                    //consume ID
                    getNextToken();
                }
                else
                {
                    logError();
                }
            }
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume Rparen
            getNextToken();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolon
            getNextToken();
        }
        else
        {
            logError();
        }
    }

    public void writeStatement()
    {
        if(tok.getTok().equals(TCscanner.Tokens.WRITE))
        {
            //consume write
            getNextToken();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume lparen
            getNextToken();
            actualParameters();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            getNextToken();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolon
            getNextToken();
            return;
        }
        else
        {
            logError();
        }
    }

    public void newlineStatement()
    {
        if(tok.getTok().equals(TCscanner.Tokens.NEWLINE))
        {
            //consume new line
            getNextToken();
        }
        else
        {
            logError();
        }

        if(tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolion
            getNextToken();
            return;
        }
        else
        {
            logError();
        }
    }

    public void actualParameters()
    {
        if(tokPrimaryCheck())
        {
            expression();
            while(tok.getTok().equals(TCscanner.Tokens.COMMA))
            {
                //consume comma
                getNextToken();
                if(tokPrimaryCheck())
                {
                    expression();
                }
                else
                {
                    logError();
                }
            }
        }
        else
        {
            logError();
        }
    }

    public void functionCall()
    {
        if(tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume lparen
            getNextToken();
        }
        else
        {
            logError();
        }

        if(tokPrimaryCheck())
        {
            expression();
        }

        if(tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            getNextToken();
        }
        else
        {
            logError();
        }

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

    public void logError()
    {
        System.out.println("INDEX OF ERROR: " + index + " TOKEN: " + tok.getTok().toString());
        System.out.println("ERROR");
    }
}











