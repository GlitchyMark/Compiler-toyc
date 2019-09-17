package compiler;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        FileManipulator fileMan = new FileManipulator();
        fileMan.initFileScanner();
        List<String> words = fileMan.getListOfTokensWhitespaceDelimited();

        for(String word: words)
        {
            System.out.println(word);
        }
    //some test stuff
        
    }
}