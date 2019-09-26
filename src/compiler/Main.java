package compiler;

import compiler.parser.FileManipulator;
import compiler.parser.Tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main
{
    public static void main(String[] args)
    {

        handleArgs(args);
        FileManipulator fileMan = new FileManipulator();
        fileMan.initFileScanner(Globals.loadFilename);
        List<String> words = fileMan.getListOfTokensWhitespaceDelimited();
        List<String> lmaoWords = new ArrayList<>();

        Tokenizer testin = new Tokenizer(words);

        fileMan.writeListOfTokens(testin.getTokens(), Optional.empty());
    }

    private static void handleArgs(String[] args)
    {
        int i = 1;
        if(args.length <= 0)
        {
            printUsage();
            System.exit(0);
        }
        Globals.loadFilename = args[args.length - 1];
        if(args.length > 1)
        switch(args[0].toLowerCase())
        {
            case "-help":
                printUsage();
                return;
            case "-verbose":
                Globals.debugLevel = 0;
                return;
            case "-debug":
                try
                {
                    Globals.debugLevel = Integer.parseInt(args[1]);
                } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException ex) {
                    System.out.println("Debug level number invalid.");
                    printUsage();
                    System.exit(1);
                }
                return;
            default:
                System.out.println(args[0] + "is not a valid command.");
                printUsage();
                System.exit(1);
        }
    }

    private static void printUsage()
    {
        System.out.print("Usage: java [classpath] parser.tc [options] toyc_source_file\n" +
                "where options include:\n" +
                "-help display this usage message\n" +
                "-debug <level> display messages that aid in tracing the\n" +
                "compilation process. If level is:\n" +
                "0 - all messages\n" +
                "1 - scanner messages only\n" +
                "-verbose display all information\n");
    }


}