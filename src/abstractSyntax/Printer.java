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

    class Line
    {
        Line(String ln, int spc)
        {
            spaces = spc;
            line = ln;
        }
        int spaces;
        String line;
    };

    public void printStack()
    {
        //for(int i = 0; i < fileprinting.size(); i++)
        //    System.out.println(fileprinting.get(i));
        /*String split[] = builder.split("\\(");

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
        System.out.print(split2[i]);*/

        List<Line> strbuilder = new ArrayList<Line>();
        strbuilder.add(new Line(builder, 0));

        while(strbuilder.get(strbuilder.size()-1).line.contains("("))
        {
            String split[] = strbuilder.get(strbuilder.size()-1).line.split("\\(", 1);
            strbuilder.get(strbuilder.size()-1).line = split[0] + "(";
            strbuilder.add(new Line(split[1], strbuilder.get(strbuilder.size()-1).spaces+1));
        }
        int iterator = 0;
        while(strbuilder.get(iterator).line.contains(")"))
        {
            String split[] = strbuilder.get(iterator).line.split("\\)", 1);
            strbuilder.get(iterator).line = split[0] + ")";

            strbuilder.add(iterator+1, new Line(split[1], strbuilder.get(iterator).spaces-1));
            for(int i = iterator; i < strbuilder.size(); i++)
                strbuilder.get(i).spaces--;
            iterator++;
        }
        for(int i = 0; i < strbuilder.size(); i++) {
            for (int j = 0; j < strbuilder.get(i).spaces; j++)
                System.out.print("  ");
            System.out.println(strbuilder.get(i).line);
        }
    }
}
