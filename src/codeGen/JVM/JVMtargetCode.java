/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package codeGen.JVM;

import java.util.List;

public class JVMtargetCode {
    List<String> code;
    List<CGInstruction> instructions;
    public JVMtargetCode()
    {
        instructions.add(new CGVar(this));
    }
    void insert(CGInstruction cgi, String s)
    {
        for(CGInstruction inst : instructions)
            if(inst.getClass().isInstance(cgi))
            {
                inst.codeGen(s);
            }
    }

    void insert(CGInstruction cgi)
    {
        for(CGInstruction inst : instructions)
            if(inst.getClass().isInstance(cgi))
            {
                inst.codeGen();
            }
    }
}
