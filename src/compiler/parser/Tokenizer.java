package compiler.parser;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer
{
    List<String> preTokens;
    List<String> tokens = new ArrayList<>();
    Integer currentListLoc = 0;
    Integer currentLineLoc = 0;
    char charBuff = ' ';

    public Tokenizer(List<String> preTokens)
    {
        this.preTokens = preTokens;
    }

    public char getChar()
    {
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

    public Token getToken()
    {
        String lex = "";
        Token tok = null;
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
                tok = new Token();
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
                    System.out.println("didn't finish your comment bro");
                    tok = new Token();
                }
                else
                {
                    tok = new Token();
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
                System.out.println("No newlines in character literals bro");
                charBuff = getChar();
                tok = new Token();
            }
            else
            {
                lex += charBuff;
                charBuff = getChar();
                if(charBuff == '\'')
                {
                    tok = new Token(Token.Tokens.CHARLITERAL, lex);
                    charBuff = getChar();
                }
                else
                {
                    System.out.println("Thats not a character literal bro");
                    charBuff = getChar();
                    tok = new Token();
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
                System.out.println("No newlines in strings bro");
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
                System.out.println("Can't use a single | bro");
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
                tok = new Token(Token.Tokens.ADDOP, lex);
            }
            else
            {
                System.out.println("Can't use a single & bro");
                tok = new Token();
            }
        }

        else
        {
            switch(charBuff)
            {
                case '+': tok = new Token(Token.Tokens.ADDOP, "+");
                case '*': tok = new Token(Token.Tokens.MULOP, "*");
                case '-': tok = new Token(Token.Tokens.ADDOP, "-");
                case '%': tok = new Token(Token.Tokens.MULOP, "%");
                case '(': tok = new Token(Token.Tokens.LPAREN);
                case ')': tok = new Token(Token.Tokens.RPAREN);
                case '{': tok = new Token(Token.Tokens.LCURLY);
                case '}': tok = new Token(Token.Tokens.RCURLY);
                case '[': tok = new Token(Token.Tokens.LBRACKET);
                case ']': tok = new Token(Token.Tokens.RBRACKET);
                case ',': tok = new Token(Token.Tokens.COMMA);
                case ';': tok = new Token(Token.Tokens.SEMICOLON);
                case ':': tok = new Token(Token.Tokens.COLON);
            }
        }



        return tok;
    }

    public List<String> getTokens()
    {
        while(charBuff != '\0')
        {
            String la = getToken().toString();
            System.out.println(la);
            tokens.add(la);
        }
        return tokens;
    }
}
