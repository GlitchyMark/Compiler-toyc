/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package parser;

import java.util.List;
import java.util.Optional;
import compiler.TCGlobals;

public class tc
{
    public static void main(String[] args)
    {

        handleArgs(args);
        TCFileManipulator fileMan = new TCFileManipulator();
        fileMan.initFileScanner(TCGlobals.loadFilename);
        List<String> words = fileMan.getListOfTokensWhitespaceDelimited();

        TCtoken testin = new TCtoken(words);
        TCparser parse = new TCparser(testin);
        parse.toyCProgram();
        parse.printer.printStack();

        //fileMan.writeListOfTokens(testin.getTokens().getStringList(), Optional.empty());
    }

    private static void handleArgs(String[] args)
    {
        int i = 1;
        if(args.length <= 0)
        {
            printUsage();
            System.exit(0);
        }
        TCGlobals.loadFilename = args[args.length - 1];
        if(args.length > 1)
        switch(args[0].toLowerCase())
        {
            case "-help":
                printUsage();
                return;
            case "-v":
            case "-verbose":
                TCGlobals.debugLevel = 0;
                return;
            case "-debug":
                try
                {
                    TCGlobals.debugLevel = Integer.parseInt(args[1]);
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
                "-verbose display all information\n" +
                "Note: path to toyc_source_file is already created in tests/");
    }


}