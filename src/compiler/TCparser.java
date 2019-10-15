package compiler;

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
            if(tok.getTok().equals(TCscanner.Tokens.BREAK))
            {
                //breakStatement call here
            }
            else if(tok.getTok().equals(TCscanner.Tokens.ID))
            {
                expressionStatement();
            }
            else if(tok.getTok().equals(TCscanner.Tokens.LCURLY) )
            {
                //compound statement call here
            }
            else if(tok.getTok().equals(TCscanner.Tokens.IF))
            {
                //if statement call here
            }
            else if(tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
            {
                //null statement call here
            }
            else if(tok.getTok().equals(TCscanner.Tokens.RETURN))
            {
                //returnStatement call here
            }
            else if(tok.getTok().equals(TCscanner.Tokens.WHILE))
            {
                //whileStatement call here
            }
            else if(tok.getTok().equals(TCscanner.Tokens.READ))
            {
                //read statemnet call here
            }
            else if(tok.getTok().equals(TCscanner.Tokens.WRITE))
            {
                //write statement call here
            }
            else if(tok.getTok().equals(TCscanner.Tokens.NEWLINE))
            {
                //newline statemnet call here
            }
            else
            {
                logError();
            }
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
            //functioncall call here
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
        System.out.println("ERROR");
    }
}











