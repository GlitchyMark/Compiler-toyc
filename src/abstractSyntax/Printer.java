package abstractSyntax;

import java.util.*;

public class Printer {
    List<String> fileprinting = new ArrayList<String>();

    public Printer(){
        fileprinting.add("");
    }

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
    void printlnspaces(String s)
    {
        int index = fileprinting.size()-1;
        fileprinting.set(index, fileprinting.get(index)+spaces()+s);
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

    public void printStack()
    {
        for(int i = 0; i < fileprinting.size(); i++)
            System.out.println(fileprinting.get(i));
    }
}
