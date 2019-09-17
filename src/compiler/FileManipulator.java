package compiler;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManipulator
{
    Scanner scanner = new Scanner(System.in);
    Scanner fileScan = null;
    List<String> stringList = new ArrayList<>();
    Path path;
    String filePath;
    boolean invalidPath;

    public FileManipulator()
    {

    }

    //ok what do here
    public void initFileScanner()
    {
        do
        {
            invalidPath = false;
            //System.out.println("Enter in file path of file to be compiled:");
            //filePath = scanner.next();
            try
            {
                path = Paths.get("g:\\compile\\test.txt"); //just for tests I swear
                //path = Paths.get(filePath);
                fileScan = new Scanner(path);
            }
            catch (IOException e)
            {
                System.out.println("ERROR: File not found");
                System.out.println(e.getMessage());
                invalidPath = true;
            }
        } while(invalidPath);
    }

    public List<String> getListOfTokensWhitespaceDelimited()
    {
        while(fileScan.hasNext())
        {
            String toBeStored = fileScan.next();
            stringList.add(toBeStored);
        }

        return stringList;
    }

}