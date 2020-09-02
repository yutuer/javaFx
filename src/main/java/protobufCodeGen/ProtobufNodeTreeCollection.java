package protobufCodeGen;

import protobufCodeGen.out.IOutPut;
import protobufCodeGen.template.PrintEnum;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description 解析文件集合
 * @Author zhangfan
 * @Date 2020/8/31 15:14
 * @Version 1.0
 */
public class ProtobufNodeTreeCollection
{

    private ProtobufNodeTree[] protobufFiles;

    /**
     * 用于 打印输出模板
     */
    private Queue<String> queue = new LinkedList<>();

    /**
     * 输入一段名称, 打印一段代码
     */
    public void printCode(PrintEnum printEnum, IOutPut outPut)
    {
        while (!queue.isEmpty())
        {
            String className = pop();

            ProtobufFileNodeTxt nodeTxt = findNodeTxt(className);
            if (nodeTxt != null)
            {
                if (printEnum == PrintEnum.CopyFrom)
                {
                    outPut.output(nodeTxt.genCopyFromString());
                }
                else
                {
                    outPut.output(nodeTxt.genWriteToString());
                }
            }
        }
    }

    public void parse(String[] args)
    {
        protobufFiles = new ProtobufNodeTree[args.length];
        for (int i = 0; i < args.length; i++)
        {
            String arg = args[i];
            if (arg.endsWith(".proto"))
            {
                ProtobufNodeTree protobufFile = new ProtobufNodeTree(this, arg.substring(0, arg.indexOf(".proto")));
                protobufFile.start(arg);

                protobufFiles[i] = protobufFile;
            }
        }
    }

    public ProtobufFileNodeTxt findNodeTxt(String className)
    {
        for (int i = 0; i < protobufFiles.length; i++)
        {
            ProtobufNodeTree protobufNodeTree = protobufFiles[i];
            if (protobufNodeTree.contains(className))
            {
                return protobufNodeTree.getNodeTxtByName(className);
            }
        }
        return null;
    }

    public void addTemplateString(String name)
    {
        queue.add(name);
    }

    public String pop()
    {
        return queue.poll();
    }
}
