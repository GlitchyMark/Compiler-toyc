package codeGen.JVM;

public class CGWriteOut extends CGInstruction {


    void codeGen()
    {
        target.code.add("invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;");
        target.code.add("invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V");
    }
}
