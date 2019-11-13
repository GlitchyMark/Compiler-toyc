package codeGen.JVM;

public class CGWriteEnter extends CGInstruction {


    void codeGen() {
        target.code.add("invokevirtual java/io / PrintStream / println(I) V)");
    }
}
