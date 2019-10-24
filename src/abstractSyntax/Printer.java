package abstractSyntax;

import java.util.*;

public class Printer {
    //List<String> fileprinting = new ArrayList<String>();
    String builder = "";
    public Printer(){
        //fileprinting.add("");
    }

    void print(String s)
    {
        builder += s;
        /*int index = fileprinting.size()-1;
        String curs = fileprinting.get(index);
        fileprinting.set(index, (curs.length() > 0 ? "" : spaces()) + curs + s);*/
    }
    void println(String s)
    {
        builder += " " + s;
        /*int index = fileprinting.size()-1;
        String curs = fileprinting.get(index);
        fileprinting.set(index, (curs.length() > 0 ? "" : spaces()) + curs + s);
        fileprinting.add("");*/
    }

    /*int getStringLength()
    {
        int index = fileprinting.size()-1;
        return fileprinting.get(index).length();
    }*/
    /*void trim(int i)
    {
        int index = fileprinting.size()-1;
        String curs = fileprinting.get(index);
        fileprinting.set(index, curs.substring(0, curs.length()-(i+1)));
    }*/
    //Pretty printer
    private int pos = 0;
    final static int INDENTSIZE=2;
    public String spaces() {
        /*String s = "";
        for (int i = 1; i <= pos; i++)
            s += " ";
        return s;*/
        return "";
    } // method
    public void indent() {
        //pos += INDENTSIZE;
    }
    public void outdent() {
        //pos -= INDENTSIZE;
        //assert (pos >= 0);
    }

    public void printStack()
    {
        //for(int i = 0; i < fileprinting.size(); i++)
        //    System.out.println(fileprinting.get(i));
        String split[] = builder.split("\\(");

        for(int i = 0; i < split.length-1; i++)
        {
            for (int j = 0; j < i; j++)
                split[i] = "  " + split[i];

            split[i] = split[i] + ((i == split.length-2) ? "" : "(") + "\n";
        }
        String splitbuild = "";
        for(int i = 0; i < split.length; i++)
            splitbuild += split[i];

        String split2[] = splitbuild.split("\\)");
        for(int i = 0; i < split2.length; i++)
        {
            if(split2[i].length() < i*2)
                continue;
            split2[i] = split2[i].substring(i*2);
            split2[i] = split2[i] + ((i == split.length-2) ? "" : ")") + "\n";
        }
        for(int i = 0; i < split2.length; i++)
        System.out.print(split2[i]);
    }
}
