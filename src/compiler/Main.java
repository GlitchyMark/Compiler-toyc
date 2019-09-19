package compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main
{
    public static void main(String[] args)
    {
        /**
         * I have forgotten that coding in school and coding
         * in the industry are two very different things.
         *
         * I have been coding for this project as if I was still
         * in the industry, which is not wise. I apologise for this
         * and shall try to write more practical, less future oriented
         * code moving forward.
         *
         * I apologise mark.
         */
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