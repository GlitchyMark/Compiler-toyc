package codeGen.JVM;

public class CGDiv extends CGInstruction
{
    void codeGen()
    {
        target.code.add("idiv");
        if(target.lastLiteral.equals("0"))
        {
            throw new ArithmeticException("Division by 0!");
        }
    }
}
