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
    /*void println(String s)
    {
        builder += s;
        /*int index = fileprinting.size()-1;
        String curs = fileprinting.get(index);
        fileprinting.set(index, (curs.length() > 0 ? "" : spaces()) + curs + s);
        fileprinting.add("");*/
    //}

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
        builder = builder.replace( ",)", ")").replace( ", )", ")").replace( "(,", "(");

        List<Line> strbuilder = new ArrayList<Line>();
        strbuilder.add(new Line(builder, 0));

        while(strbuilder.get(strbuilder.size()-1).line.contains("("))
        {
            String split[] = strbuilder.get(strbuilder.size()-1).line.split("\\(", 2);
            strbuilder.get(strbuilder.size()-1).line = split[0] + "(";
            strbuilder.add(new Line(split[1], strbuilder.get(strbuilder.size()-1).spaces+1));
        }
        int iterator = 0;
        while(iterator < strbuilder.size())
        {
            if(strbuilder.get(iterator).line.contains(")"))
            {
                String split[] = strbuilder.get(iterator).line.split("\\)", 2);
                strbuilder.get(iterator).line = split[0];

                strbuilder.add(iterator + 1, new Line(")", strbuilder.get(iterator).spaces));
                strbuilder.add(iterator + 2, new Line(split[1], strbuilder.get(iterator).spaces));
                for (int i = iterator; i < strbuilder.size(); i++)
                    strbuilder.get(i).spaces--;
                iterator++;
            }
            iterator++;
        }
        /*iterator = 0;
        while(iterator < strbuilder.size())
        {
            if(strbuilder.get(iterator).line.contains(","))
            {
                String split[] = strbuilder.get(iterator).line.split(",", 2);
                strbuilder.get(iterator).line = split[0];

                strbuilder.add(iterator + 1, new Line(",", strbuilder.get(iterator).spaces));
                iterator++;
            }
            iterator++;
        }*/

        System.out.println(builder);//TODO REMOVE

        for(int i = strbuilder.size() - 1; i >= 0; i--) {//Remove empty lines
            if (strbuilder.get(i).line.trim().length() == 0)
                strbuilder.remove(i);
        }
        for(int i = 0; i < strbuilder.size(); i++)
        {
            if(strbuilder.get(i).line.startsWith(",")) {
                if(i > 0)
                {
                    strbuilder.get(i-1).line += ",";
                    strbuilder.get(i).line = strbuilder.get(i).line.substring(1);
                }
            }
            if(strbuilder.get(i).line.trim().startsWith(")")) {
                if(i > 0)
                {
                    //System.out.println("[DEBUG] " + strbuilder.get(i-1).line);
                    if(strbuilder.get(i-1).line.endsWith("("))
                    {
                        strbuilder.get(i-1).line += ")";
                        strbuilder.get(i).line = strbuilder.get(i).line.substring(1);
                    }
                }
            }
        }

        for(int i = strbuilder.size() - 1; i >= 0; i--) {//Remove empty lines
            if (strbuilder.get(i).line.trim().length() == 0)
                strbuilder.remove(i);
        }
        for(int i = 0; i < strbuilder.size(); i++) {
            if (strbuilder.get(i).line.startsWith(",")) {
                if (i > 0) {
                    strbuilder.get(i - 1).line += ",";
                    strbuilder.get(i).line = strbuilder.get(i).line.substring(1);
                }
            }
        }


        for(int i = 0; i < strbuilder.size(); i++)
            strbuilder.get(i).line = strbuilder.get(i).line.replace( "\u2001", ")").replace("\u2002","(");
        for(int i = 0; i < strbuilder.size(); i++) {
            if(strbuilder.get(i).line.trim().length() == 0)
                continue;
            System.out.print(i + ": ");
            for (int j = 0; j < strbuilder.get(i).spaces; j++)
                System.out.print("  ");
            System.out.println(strbuilder.get(i).line);
        }

        System.out.println("Leftover spaces: " + Integer.toString(strbuilder.get(strbuilder.size()-1).spaces));//TODO REMOVE


    }
}
