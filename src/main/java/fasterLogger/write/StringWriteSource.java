package fasterLogger.write;

/**
 * @Description 写数据的源
 * @Author zhangfan
 * @Date 2020/9/4 12:56
 * @Version 1.0
 */
public class StringWriteSource implements IWriteDataSource
{
    private StringBuilder sb = new StringBuilder();

    private String msg;

    public StringWriteSource(String msg)
    {
        this.msg = msg;
    }

    @Override
    public byte[] getData()
    {
        sb.delete(0, sb.length());
        sb.append(msg);
        sb.append(", 当前时间为:");
        sb.append(System.currentTimeMillis());
        sb.append("\t");
        return sb.toString().getBytes(getCharset());
    }
}
