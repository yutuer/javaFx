package jvmByteCode;

import javassist.*;

import java.io.IOException;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/8/5 14:41
 * @Version 1.0
 */
public class TableSwitchTest
{
    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException
    {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass("jvmByteCode.TableSwitchVsLookup");

        //!!! 生成switch方法得不到 tableswitch的字节码
        StringBuilder sb = new StringBuilder("public void m(int i){\n");
        sb.append("switch (i){");
        addSwitchCode(1, 10, sb);
        addSwitchCode(11, 20, sb, false);
        sb.append("default:\nSystem.out.println(i);\nbreak;\n}\n}");

        ctClass.addMethod(CtMethod.make(sb.toString(), ctClass));


//        ctClass.writeFile();
    }

    private static void addSwitchCode(int from, int to, StringBuilder sb)
    {
        addSwitchCode(from, to, sb, true);
    }

    private static void addSwitchCode(int from, int to, StringBuilder sb, boolean isUpper)
    {
        if (isUpper)
        {
            for (int i = from; i < to; i++)
            {
                sb.append("case ").append(i).append(":\n").append("System.out.println(i);\nbreak;\n");
            }
        }
        else
        {
            for (int i = to - 1; i >= from; i--)
            {
                sb.append("case ").append(i).append(":\n").append("System.out.println(i);\nbreak;\n");
            }
        }
    }

    public void m(int i)
    {
        switch (i)
        {
            case 1:
                System.out.println(i);
                break;
            default:
                break;
        }
    }
}
