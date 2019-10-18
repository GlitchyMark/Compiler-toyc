package parser;

import java.util.List;

public class TokenListWrapper
{
    List<String> stringList;
    List<TCscanner> tokenList;

    public TokenListWrapper(List<String> stringList, List<TCscanner> tokenList)
    {
        this.stringList = stringList;
        this.tokenList = tokenList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public List<TCscanner> getTokenList()
    {
        return tokenList;
    }
}
