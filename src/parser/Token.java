package parser;

import compiler.Globals;

public class Token
{
    public static enum Tokens
    {
        INT, CHAR, RETURN, IF, ELSE, FOR, DO,
        WHILE, SWITCH, CASE, DEFAULT, WRITE, READ,
        CONTINUE, BREAK, NEWLINE, ID, NUMBER, CHARLITERAL,
        STRING, RELOP, ADDOP, MULOP, ASSIGNOP,
        LPAREN, RPAREN, LCURLY, RCURLY, LBRACKET,
        RBRACKET, COMMA, SEMICOLON, NOT, COLON, EOF, COMMENT, ERROR
    };
    Tokens tok;
    String lex;

    public Token()
    {
        tok = Tokens.ERROR;
    }

    public Token(Tokens tok)
    {
        this.tok = tok;
        lex = tok.toString();
    }

    public Token(Tokens tok, String lex)
    {
        this.tok = tok;
        this.lex = lex;
    }

    public Tokens getTok()
    {
        return tok;
    }

    public String getLex()
    {
        return lex;
    }

    @Override
    public String toString()
    {
        if(tok == Tokens.ID)//done
        {
            return ("ID("+lex+")");
        }
        else if(tok == Tokens.NUMBER)//done
        {
            return ("NUMBER("+lex+")");
        }
        else if(tok == Tokens.CHARLITERAL)
        {
            return ("CHARLITERAL("+lex+")");
        }
        else if(tok == Tokens.STRING)
        {
            return ("STRING("+lex+")");
        }
        else if(tok == Tokens.RELOP)
        {
            return ("RELOP("+lex+")");
        }
        else if(tok == Tokens.ADDOP)
        {
            return ("ADDOP("+lex+")");
        }
        else if(tok == Tokens.MULOP)
        {
            return ("MULOP("+lex+")");
        }
        else
        {
            switch(tok)
            {
                case INT: return "INT";//done
                case CHAR: return "CHAR";//done
                case RETURN: return "RETURN";//done
                case IF: return "IF";//done
                case ELSE: return "ELSE";//done
                case FOR: return "FOR";//done
                case DO: return "DO";//done
                case WHILE: return "WHILE";//done
                case SWITCH: return "SWITCH";//done
                case CASE: return "CASE";//done
                case DEFAULT: return "DEFAULT";//done
                case READ: return "READ";
                case WRITE: return "WRITE";//done
                case CONTINUE: return "CONTINUE";//done
                case BREAK: return "BREAK";//done
                case ASSIGNOP: return "ASSIGNOP";//DONE
                case LPAREN: return "LPAREN";
                case RPAREN: return "RPAREN";
                case LCURLY: return "LCURLY";
                case RCURLY: return "RCURLY";
                case LBRACKET: return "LBRACKET";
                case RBRACKET: return "RBRACKET";
                case COMMA: return "COMMA";
                case SEMICOLON: return "SEMICOLON";
                case NOT: return "NOT";//DONE
                case COLON: return "COLON";
                case EOF: return "EOF";//DONE
                case NEWLINE:
                case COMMENT:
                case ERROR:
                    return "";
                default: return "lexer error";
            }
        }
    }
}
