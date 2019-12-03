package codeGen.JVM;

import java.util.ArrayList;
import java.util.List;

public class LabelManager
{
    List<String> listOfLabels = new ArrayList<>();
    Integer labelNum = 1;

    public LabelManager()
    {

    }

    String addString()
    {
        String label = "Label" + labelNum;
        listOfLabels.add(label);
        labelNum++;

        return label;
    }

    boolean labelExists(String find)
    {
        return listOfLabels.contains(find);
    }

    void addNamed(String labelName)
    {
        listOfLabels.add(labelName);
    }

    String getMostRecent()
    {
        return listOfLabels.get(listOfLabels.size());
    }

    String getSecondMostRecent()
    {
        return listOfLabels.get(listOfLabels.size()-1);
    }
}
