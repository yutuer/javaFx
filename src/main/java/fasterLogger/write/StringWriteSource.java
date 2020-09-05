package fasterLogger.write;

/**
 * @Description 写数据的源
 * @Author zhangfan
 * @Date 2020/9/4 12:56
 * @Version 1.0
 */
public class StringWriteSource implements IWriteSource<String>
{
    private StringBuilder sb = new StringBuilder();

    @Override
    public String getSource()
    {
        sb.delete(0, sb.length());

        sb.append("当前时间为:");
        sb.append(System.currentTimeMillis());
        sb.append("\n");
        return sb.toString();
    }
}
