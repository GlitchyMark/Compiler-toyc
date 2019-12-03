/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package compiler;

import codeGen.JVM.JVMFunc;

import java.util.ArrayList;
import java.util.List;

public class TCGlobals
{
    public static int debugLevel = 5;
    public static String loadFilename;
    public static boolean lazyCheck = false;
    public static boolean isWrite = false;
    public static int funcCount = 0;
    public static String sorryMark = "";
    public static List<String> sorryAgain = new ArrayList<>();
    public static List<String> functions = new ArrayList<>();
}
