package compiler;

public interface AbstractSyntax
{
    public String toString();
    class PrettyPrint {
        static int pos = 0;
        final static int INDENTSIZE=2;
        public String spaces() {
            String s = "";
            for (int i = 1; i <= pos; i++)
                s += " ";
            return s;
        } // method
        public void indent() {
            pos += INDENTSIZE;
        }
        public void outdent() {
            pos -= INDENTSIZE;
        }
    }
}
