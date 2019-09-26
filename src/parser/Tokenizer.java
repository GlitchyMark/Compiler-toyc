package parser;

import compiler.Globals;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer
{
    List<String> preTokens;
    List<String> tokens = new ArrayList<>();
    Integer currentListLoc = 0;
    Integer currentLineLoc = 0;
    Integer prevPosition[] = {0, 0};
    char charBuff = ' ';

    public Tokenizer(List<String> preTokens)
    {
        this.preTokens = preTokens;
    }

    public char getChar()
    {
        prevPosition[0] = currentListLoc;
        prevPosition[1] = currentLineLoc;
        if(preTokens.get(currentListLoc).length() == currentLineLoc && ((preTokens.size() - 1) == currentListLoc))
        {
            return '\0';
        }
        if(preTokens.get(currentListLoc).length() == currentLineLoc)
        {
            currentListLoc++;
            currentLineLoc = 0;
            return '\n';
        }

        char c = preTokens.get(currentListLoc).charAt(currentLineLoc);
        currentLineLoc++;

        return c;
    }

    public boolean isInAlphabet(char ch)
    {
        return(Character.isLetterOrDigit(ch)
                ||(ch=='+')||(ch=='-')||(ch=='*')||(ch=='/')||
                (ch=='<')||(ch=='>')||(ch=='(')||(ch==')')||
                (ch=='=')||(ch==';')||(ch==':'));
    }

    public void throwError(String reason)
    {
        System.out.println("[SCANNER] Error: At Ln:" + (prevPosition[0] + 1) + " Col:" + prevPosition[1]);
        System.out.println("[SCANNER] Error: " + reason);
    }

    public Token getToken()
    {
        String lex = "";
        Token tok;
        while(Character.isWhitespace(charBuff) && (charBuff != '\0'))
        {
            if(charBuff == '\n')
            {
                break;
            }
            charBuff = getChar();
        }
        if(Character.isLetter(charBuff))
        {
            do
            {
                lex += charBuff;
                charBuff = getChar();
            } while(Character.isLetterOrDigit(charBuff));

            switch(lex)
            {
                case "int": return new Token(Token.Tokens.INT);
                case "char": tok = new Token(Token.Tokens.CHAR); break;
                case "return": tok = new Token(Token.Tokens.RETURN); break;
                case "if": tok = new Token(Token.Tokens.IF); break;
                case "else": tok = new Token(Token.Tokens.ELSE); break;
                case "for": tok = new Token(Token.Tokens.FOR); break;
                case "do": tok = new Token(Token.Tokens.DO); break;
                case "while": tok = new Token(Token.Tokens.WHILE); break;
                case "switch": tok = new Token(Token.Tokens.SWITCH); break;
                case "case": tok = new Token(Token.Tokens.CASE); break;
                case "default": tok = new Token(Token.Tokens.DEFAULT); break;
                case "write": tok = new Token(Token.Tokens.WRITE); break;
                case "read": tok = new Token(Token.Tokens.READ); break;
                case "continue": tok = new Token(Token.Tokens.CONTINUE); break;
                case "break": tok = new Token(Token.Tokens.BREAK); break;
                default: tok = new Token(Token.Tokens.ID, lex);
            }
        }

        else if(Character.isDigit(charBuff))
        {
            do
            {
                lex += charBuff;
                charBuff = getChar();
            } while(Character.isDigit(charBuff));

            if(charBuff == '.')
            {
                do
                {
                    lex += charBuff;
                    charBuff = getChar();
                } while(Character.isDigit(charBuff));
            }

            if(charBuff == 'E')
            {
                //this initial one is to account for the +/-
                lex += charBuff;
                charBuff = getChar();

                if(charBuff == '+')
                {
                    lex += charBuff;
                    charBuff = getChar();
                }
                else if(charBuff == '-')
                {
                    lex += charBuff;
                    charBuff = getChar();
                }
                while(Character.isDigit(charBuff))
                {
                    lex += charBuff;
                    charBuff = getChar();
                }
            }
            tok = new Token(Token.Tokens.NUMBER, lex);
        }

        else if(charBuff == '=')
        {
            lex += charBuff;
            charBuff = getChar();
            if(charBuff == '=')
            {
                lex += charBuff;
                charBuff = getChar();
                tok = new Token(Token.Tokens.RELOP, lex);
            }
            else
            {
                tok = new Token(Token.Tokens.ASSIGNOP);
            }
        }

        else if(charBuff == '/')
        {
            charBuff = getChar();
            if(charBuff == '/')
            {
                do
                {
                    charBuff = getChar();
                } while(charBuff != '\n');
                tok = new Token(Token.Tokens.COMMENT);
            }
            else if(charBuff == '*')
            {
                do
                {
                    charBuff = getChar();
                    if(charBuff == '*')
                    {
                        charBuff = getChar();
                        if(charBuff == '/')
                        {
                            charBuff = getChar();
                            break;
                        }
                    }
                } while(charBuff != '\0');
                if(charBuff == '\0')
                {
                    throwError("Unfinished comment.");
                    tok = new Token();
                }
                else
                {
                    tok = new Token(Token.Tokens.COMMENT);
                }
            }
            else
            {
                tok = new Token(Token.Tokens.MULOP, "/");
            }
        }

        else if(charBuff == '\'')
        {
            charBuff = getChar();
            if(charBuff == '\'')
            {
                tok = new Token(Token.Tokens.CHARLITERAL, "");
                charBuff = getChar();
            }
            else if(charBuff == '\n')
            {
                throwError("Newline in character literal");
                charBuff = getChar();
                tok = new Token();
            }
            else
            {
                lex += charBuff;
                charBuff = getChar();
                if(charBuff != '\'')
                {
                    throwError("Incorrect character literal");
                    charBuff = getChar();
                    tok = new Token();
                }
                else if(charBuff =='\n')
                {
                    throwError("Newline in character literal");
                    charBuff = getChar();
                    tok = new Token();
                }
                else
                {
                    tok = new Token(Token.Tokens.CHARLITERAL, lex);
                    charBuff = getChar();
                }
            }
        }

        else if(charBuff == '\"')
        {
            do
            {
                charBuff = getChar();
                if(charBuff != '\"')
                {
                    lex += charBuff;
                }
            } while(charBuff != '\"' && charBuff != '\n');
            if(charBuff == '\n')
            {
                throwError("Newline before string closed");
                charBuff = getChar();
                tok = new Token();
            }
            else
            {
                tok = new Token(Token.Tokens.STRING, lex);
                charBuff = getChar();
            }
        }

        else if(charBuff == '!')
        {
            lex += charBuff;
            charBuff = getChar();
            if(charBuff == '=')
            {
                lex += charBuff;
                charBuff = getChar();
                tok = new Token(Token.Tokens.RELOP, lex);
            }
            else
            {
                tok = new Token(Token.Tokens.NOT);
            }
        }

        else if(charBuff == '<')
        {
            lex += charBuff;
            charBuff = getChar();
            if(charBuff == '=')
            {
                lex+= charBuff;
            }
            charBuff = getChar();
            tok = new Token(Token.Tokens.RELOP, lex);
        }

        else if(charBuff == '>')
        {
            lex += charBuff;
            charBuff = getChar();
            if(charBuff == '=')
            {
                lex+= charBuff;
            }
            charBuff = getChar();
            tok = new Token(Token.Tokens.RELOP, lex);
        }

        else if(charBuff == '\n')
        {
            charBuff = getChar();
            tok = new Token(Token.Tokens.NEWLINE);
        }

        else if(charBuff == '\0')
        {
            tok = new Token(Token.Tokens.EOF);
        }

        else if(charBuff == '|')
        {
            lex += charBuff;
            charBuff = getChar();
            if(charBuff == '|')
            {
                lex += charBuff;
                charBuff = getChar();
                tok = new Token(Token.Tokens.ADDOP, lex);
            }
            else
            {
                throwError("Only one |");
                tok = new Token();
            }
        }

        else if(charBuff == '&')
        {
            lex += charBuff;
            charBuff = getChar();
            if(charBuff == '&')
            {
                lex += charBuff;
                charBuff = getChar();
                tok = new Token(Token.Tokens.MULOP, lex);
            }
            else
            {
                throwError("Only one &");
                tok = new Token();
            }
        }

        else
        {
            switch(charBuff)
            {
                case '+': tok = new Token(Token.Tokens.ADDOP, "+"); break;
                case '*': tok = new Token(Token.Tokens.MULOP, "*"); break;
                case '-': tok = new Token(Token.Tokens.ADDOP, "-"); break;
                case '%': tok = new Token(Token.Tokens.MULOP, "%"); break;
                case '(': tok = new Token(Token.Tokens.LPAREN); break;
                case ')': tok = new Token(Token.Tokens.RPAREN); break;
                case '{': tok = new Token(Token.Tokens.LCURLY); break;
                case '}': tok = new Token(Token.Tokens.RCURLY); break;
                case '[': tok = new Token(Token.Tokens.LBRACKET); break;
                case ']': tok = new Token(Token.Tokens.RBRACKET); break;
                case ',': tok = new Token(Token.Tokens.COMMA); break;
                case ';': tok = new Token(Token.Tokens.SEMICOLON); break;
                case ':': tok = new Token(Token.Tokens.COLON); break;
                default:
                    tok = new Token(Token.Tokens.ERROR);
                    throwError("Unexpected character " + charBuff);
            }
            charBuff = getChar();
        }



        return tok;
    }

    public List<String> getTokens()
    {
        List<String> tkns = new ArrayList<String>();
        //System.out.println("Changing input strings to tokens...");
        Token t;
        do
        {
            t = getToken();
            if(!t.toString().equals(""))
            {
                String formattedTkn = "(<"+t.tok.toString()+">,\""+t.lex+"\")";
                if(Globals.debugLevel <= 1)
                    System.out.println("[SCANNER] " + formattedTkn);
                tkns.add(formattedTkn);
            }

        }while(t.tok != Token.Tokens.EOF);
        if(Globals.debugLevel <= 1)
            System.out.println("[SCANNER] Total tokens: " + tkns.size());
        return tkns;
    }
}
