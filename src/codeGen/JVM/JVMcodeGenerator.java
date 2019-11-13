/** EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package codeGen.JVM;

public class JVMcodeGenerator
{
    JVMtargetCode target;
    public JVMcodeGenerator()
    {

    }
    public void insert(CGInstruction cgi)
    {
        target.insert(cgi);
    }

}
