package codeGen.JVM;

public class CGWriteOut extends CGInstruction {


    void codeGen() {
        target.code.add("invokevirtual java/io / PrintStream / println(I) V)");
    }
}
