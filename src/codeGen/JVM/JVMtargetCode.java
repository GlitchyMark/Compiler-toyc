/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package codeGen.JVM;

import symTable.SymTable;

import java.util.ArrayList;
import java.util.List;

public class JVMtargetCode {
    public SymTable symtable = new SymTable();
    List<String> code = new ArrayList<>();
    List<CGInstruction> instructions = new ArrayList<>();
    LabelManager labelMan = new LabelManager();


    public JVMtargetCode()
    {
        //instructions.add(new CGVar(this));
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
        code.add(".limit stack 100");
        code.add(".limit locals 100");
    }
    void insert(CGInstruction cgi)
    {
        cgi.processinst(this);
    }

    public SymTable getSymtable()
    {
        return symtable;
    }

    public List<String> getCode()
    {
        return code;
    }

    public String addLabel()
    {
        return labelMan.addString();
    }
}
