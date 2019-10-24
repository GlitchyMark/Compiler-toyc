/*EGRE 591 -- Compiler Construction
*By Mark Johnston and George Constantine
 */

package parser;

import compiler.TCGlobals;

import java.util.ArrayList;
import java.util.List;

public class TCtoken
{
    List<String> preTokens;
    Integer currentListLoc = 0;
    Integer currentLineLoc = 0;
    Integer prevPosition[] = {0, 0};
    Integer errorPos[] = {0, 0};
    char charBuff = ' ';
    TCscanner tok;
    boolean endFile = true;

    public TCtoken(List<String> preTokens)
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

    public Integer getCurrentLineLoc()
    {
        return errorPos[1];
    }

    public List<String> getPreTokens()
    {
        return preTokens;
    }

    public Integer getCurrentListLoc()
    {
        return errorPos[0];
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

    public TCscanner getToken()
    {
        String lex = "";
        errorPos[0] = prevPosition[0];
        errorPos[1] = prevPosition[1];
        while(Character.isWhitespace(charBuff) && (charBuff != '\0'))
        {
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
                case "int": tok = new TCscanner(TCscanner.Tokens.INT); break;
                case "char": tok = new TCscanner(TCscanner.Tokens.CHAR); break;
                case "return": tok = new TCscanner(TCscanner.Tokens.RETURN); break;
                case "if": tok = new TCscanner(TCscanner.Tokens.IF); break;
                case "else": tok = new TCscanner(TCscanner.Tokens.ELSE); break;
                case "for": tok = new TCscanner(TCscanner.Tokens.FOR); break;
                case "do": tok = new TCscanner(TCscanner.Tokens.DO); break;
                case "while": tok = new TCscanner(TCscanner.Tokens.WHILE); break;
                case "switch": tok = new TCscanner(TCscanner.Tokens.SWITCH); break;
                case "case": tok = new TCscanner(TCscanner.Tokens.CASE); break;
                case "default": tok = new TCscanner(TCscanner.Tokens.DEFAULT); break;
                case "write": tok = new TCscanner(TCscanner.Tokens.WRITE); break;
                case "read": tok = new TCscanner(TCscanner.Tokens.READ); break;
                case "continue": tok = new TCscanner(TCscanner.Tokens.CONTINUE); break;
                case "break": tok = new TCscanner(TCscanner.Tokens.BREAK); break;
                case "newline": tok = new TCscanner(TCscanner.Tokens.NEWLINE); break;
                default: tok = new TCscanner(TCscanner.Tokens.ID, lex);
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
            tok = new TCscanner(TCscanner.Tokens.NUMBER, lex);
        }

        else if(charBuff == '=')
        {
            lex += charBuff;
            charBuff = getChar();
            if(charBuff == '=')
            {
                lex += charBuff;
                charBuff = getChar();
                tok = new TCscanner(TCscanner.Tokens.RELOP, lex);
            }
            else
            {
                tok = new TCscanner(TCscanner.Tokens.ASSIGNOP);
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
                } while(charBuff != '\n' && charBuff != '\0');
                tok = new TCscanner(TCscanner.Tokens.COMMENT);
            }
            else if(charBuff == '*')
            {
                int commentlevel = 0;
                do
                {
                    charBuff = getChar();

                    if(charBuff == '/') {
                        charBuff = getChar();
                        if (charBuff == '*') {
                            commentlevel++;
                            charBuff = getChar();
                        }
                    }
                    if(charBuff == '*')
                    {
                        charBuff = getChar();
                        if(charBuff == '/')
                        {
                            charBuff = getChar();
                            if(commentlevel-- <= 0)
                            break;
                        }
                    }
                } while(charBuff != '\0');
                if(charBuff == '\0')
                {
                    throwError("Unfinished comment.");
                    tok = new TCscanner();
                }
                else
                {
                    tok = new TCscanner(TCscanner.Tokens.COMMENT);
                }
            }
            else
            {
                tok = new TCscanner(TCscanner.Tokens.MULOP, "/");
            }
        }

        else if(charBuff == '\'')
        {
            charBuff = getChar();
            if(charBuff == '\'')
            {
                tok = new TCscanner(TCscanner.Tokens.CHARLITERAL, "");
                charBuff = getChar();
            }
            else if(charBuff == '\n')
            {
                throwError("Newline in character literal");
                charBuff = getChar();
                tok = new TCscanner();
            }
            else
            {
                lex += charBuff;
                charBuff = getChar();
                if(charBuff != '\'')
                {
                    throwError("Incorrect character literal");
                    charBuff = getChar();
                    tok = new TCscanner();
                }
                else if(charBuff =='\n')
                {
                    throwError("Newline in character literal");
                    charBuff = getChar();
                    tok = new TCscanner();
                }
                else
                {
                    tok = new TCscanner(TCscanner.Tokens.CHARLITERAL, lex);
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
                tok = new TCscanner();
            }
            else
            {
                tok = new TCscanner(TCscanner.Tokens.STRING, lex);
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
                tok = new TCscanner(TCscanner.Tokens.RELOP, lex);
            }
            else
            {
                tok = new TCscanner(TCscanner.Tokens.NOT);
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
            tok = new TCscanner(TCscanner.Tokens.RELOP, lex);
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
            tok = new TCscanner(TCscanner.Tokens.RELOP, lex);
        }



        else if(charBuff == '\0')
        {
            tok = new TCscanner(TCscanner.Tokens.EOF);
        }

        else if(charBuff == '|')
        {
            lex += charBuff;
            charBuff = getChar();
            if(charBuff == '|')
            {
                lex += charBuff;
                charBuff = getChar();
                tok = new TCscanner(TCscanner.Tokens.ADDOP, lex);
            }
            else
            {
                throwError("Only one |");
                tok = new TCscanner();
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
                tok = new TCscanner(TCscanner.Tokens.MULOP, lex);
            }
            else
            {
                throwError("Only one &");
                tok = new TCscanner();
            }
        }

        else
        {
            switch(charBuff)
            {
                case '+': tok = new TCscanner(TCscanner.Tokens.ADDOP, "+"); break;
                case '*': tok = new TCscanner(TCscanner.Tokens.MULOP, "*"); break;
                case '-': tok = new TCscanner(TCscanner.Tokens.ADDOP, "-"); break;
                case '%': tok = new TCscanner(TCscanner.Tokens.MULOP, "%"); break;
                case '(': tok = new TCscanner(TCscanner.Tokens.LPAREN); break;
                case ')': tok = new TCscanner(TCscanner.Tokens.RPAREN); break;
                case '{': tok = new TCscanner(TCscanner.Tokens.LCURLY); break;
                case '}': tok = new TCscanner(TCscanner.Tokens.RCURLY); break;
                case '[': tok = new TCscanner(TCscanner.Tokens.LBRACKET); break;
                case ']': tok = new TCscanner(TCscanner.Tokens.RBRACKET); break;
                case ',': tok = new TCscanner(TCscanner.Tokens.COMMA); break;
                case ';': tok = new TCscanner(TCscanner.Tokens.SEMICOLON); break;
                case ':': tok = new TCscanner(TCscanner.Tokens.COLON); break;
                default:
                    tok = new TCscanner(TCscanner.Tokens.ERROR);
                    throwError("Unexpected character " + charBuff);
            }
            charBuff = getChar();
        }


        if(!tok.toString().equals("") && endFile)
        {
            String formattedTkn = "(<"+tok.toString()+">,\""+tok.lex+"\")";
            if(tok.getTok().equals(TCscanner.Tokens.EOF))
                endFile = false;
            if(TCGlobals.debugLevel <= 1)
                System.out.println("[SCANNER] " + formattedTkn);
        }
        return tok;
    }

    public TokenListWrapper getTokens()
    {
        List<String> tkns = new ArrayList<String>();
        List<TCscanner> tokens = new ArrayList<>();
        //System.out.println("Changing input strings to tokens...");
        TCscanner t;
        do
        {
            t = getToken();
            if(!t.toString().equals(""))
            {
                String formattedTkn = "(<"+t.tok.toString()+">,\""+t.lex+"\")";
                if(TCGlobals.debugLevel <= 1)
                    System.out.println("[SCANNER] " + formattedTkn);
                tkns.add(formattedTkn);
                tokens.add(t);
            }

        }while(t.tok != TCscanner.Tokens.EOF);
        if(TCGlobals.debugLevel <= 1)
            System.out.println("[SCANNER] Total tokens: " + tkns.size());
        TokenListWrapper tk = new TokenListWrapper(tkns, tokens);
        return tk;
    }
}
