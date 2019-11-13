package codeGen.JVM;

public class CGWriteEnter extends CGInstruction {


    void codeGen() {
        target.code.add("getstatic java/lang/System/out Ljava/io/PrintStream;");
    }
}
