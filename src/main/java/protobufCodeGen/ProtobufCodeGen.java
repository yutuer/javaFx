package protobufCodeGen;

import protobufCodeGen.out.ConsoleOutput;
import protobufCodeGen.out.IOutPut;
import protobufCodeGen.template.PrintEnum;
import util.Log;
import util.PropertiesUtils;

import java.util.Properties;

/**
 * @Description 门面类
 * @Author zhangfan
 * @Date 2020/8/31 14:27
 * @Version 1.0
 */
public class ProtobufCodeGen
{

    public static void main(String[] args)
    {
        if (args == null || args.length < 2)
        {
            Log.logger.error("输入文件参数!!!");
            System.exit(1);
        }

        if ("-c".equals(args[0]))
        {
            String propertiesName = args[1];

            Properties properties = PropertiesUtils.getProperties(propertiesName);

            ProtobufNodeTreeCollection protobufNodeTreeCollection = new ProtobufNodeTreeCollection();
            protobufNodeTreeCollection.parse(PropertiesUtils.getString(properties, ProtobufCodeKey.protoFiles, "").split(";"));

            protobufNodeTreeCollection.addTemplateString(PropertiesUtils.getString(properties, ProtobufCodeKey.protoName, ""));

            IOutPut outPut = getInstance(PropertiesUtils.getInt(properties, ProtobufCodeKey.output, 0));
            protobufNodeTreeCollection.printCode(PrintEnum.values()[PropertiesUtils.getInt(properties, ProtobufCodeKey.printEnum, 0)], outPut);
        }
    }

    public static IOutPut getInstance(int output)
    {
        if (output == 0)
        {
            return new ConsoleOutput();
        }
        return null;
    }
}
