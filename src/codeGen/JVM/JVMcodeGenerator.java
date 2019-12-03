/** EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package codeGen.JVM;

import symTable.SymTable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class JVMcodeGenerator
{
    public String buffer;
    JVMtargetCode target = new JVMtargetCode();
    public JVMcodeGenerator()
    {
    }
    public void insert(CGInstruction cgi)
    {
        target.insert(cgi);
    }

    public JVMtargetCode getTarget()
    {
        return target;
    }

}
