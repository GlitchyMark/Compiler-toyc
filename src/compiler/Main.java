package compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main
{
    public static void main(String[] args)
    {
        FileManipulator fileMan = new FileManipulator();
        fileMan.initFileScanner();
        List<String> words = fileMan.getListOfTokensWhitespaceDelimited();
        List<String> lmaoWords = new ArrayList<>();

        for(String word: words)
        {
            String lmao = word.concat("lmao");
            lmaoWords.add(lmao);
        }

        fileMan.writeListOfTokens(lmaoWords, Optional.empty());
    }
}