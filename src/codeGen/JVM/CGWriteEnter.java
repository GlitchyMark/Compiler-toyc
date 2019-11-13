package codeGen.JVM;

public class CGWriteEnter extends CGInstruction {


    void codeGen() {
        target.code.add("invokevirtual java/lang/System/out Ljava/io/PrintStream;");
    }
}
