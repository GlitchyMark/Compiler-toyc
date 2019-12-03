package codeGen.JVM;

import java.util.ArrayList;
import java.util.List;

public class JVMFuncManager
{
    List<JVMFunc> funcs = new ArrayList<>();
    public JVMFuncManager()
    {}

    public void addFunc(JVMFunc func)
    {
        funcs.add(func);
    }

    public boolean funcExists(JVMFunc f)
    {
        return funcs.contains(f);
    }

    public JVMFunc getFunc(String label)
    {
        JVMFunc f = new JVMFunc("",0);
        for(JVMFunc j : funcs)
        {
            if(j.funcName.equals(label))
            {
                f = j;
                break;
            }
        }
        return f;
    }
}
