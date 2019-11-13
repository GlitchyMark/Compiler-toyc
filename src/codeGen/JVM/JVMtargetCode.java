/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package codeGen.JVM;

import symTable.SymTable;

import java.util.List;

public class JVMtargetCode {
    public SymTable symtable;
    List<String> code;
    List<CGInstruction> instructions;
    public JVMtargetCode()
    {
        instructions.add(new CGVar(this));
        initCodeLines();
    }
    void initCodeLines()
    {
        code.add(".source test.java");
        code.add(".class test");
        code.add("; ACC_SUPER bit is set");
        code.add(".super java/lang/Object");
        code.add("; >> METHOD 1 <<");
        code.add(".method <init>()V");
        code.add(".limit stack 1");
        code.add(".limit locals 1");
        code.add(".line 1");
        code.add("aload_0");
        code.add("invokespecial java/lang/Object/<init>()V");
        code.add("return");
        code.add(".end method");
        code.add("; >> METHOD 2 <<");
        code.add(".method public static main([Ljava/lang/String;)V");
        code.add(".limit stack 2");
        code.add(".limit locals 2");
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
