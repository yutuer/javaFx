package protobufCodeGen.out;

/**
 * @Description 输出工厂类
 * @Author zhangfan
 * @Date 2020/9/5 12:53
 * @Version 1.0
 */
public class OutPutFactory
{
    public static IOutPut getInstance(int output)
    {
        if (output == 0)
        {
            return new ConsoleOutput();
        }
        return null;
    }
}
