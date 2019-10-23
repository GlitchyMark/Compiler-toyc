package abstractSyntax;

import java.util.List;

public class Printer {
    List<String> fileprinting;

    void printspaces(String s)
    {
        int index = fileprinting.size()-1;
        fileprinting.set(index, fileprinting.get(index)+spaces()+s);
    }
    void print(String s)
    {
        int index = fileprinting.size()-1;
        fileprinting.set(index, fileprinting.get(index)+s);
    }
    void println(String s)
    {
        int index = fileprinting.size()-1;
        fileprinting.set(index, fileprinting.get(index)+s);
        fileprinting.add("");
    }

    int getStringLength()
    {
        int index = fileprinting.size()-1;
        return fileprinting.get(index).length();
    }
    //Pretty printer
    private int pos = 0;
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
