package protobufCodeGen.out;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/2 20:27
 * @Version 1.0
 */
public class ConsoleOutput implements IOutPut
{

    @Override
    public void output(String content)
    {
        System.out.println(content);
    }
}
