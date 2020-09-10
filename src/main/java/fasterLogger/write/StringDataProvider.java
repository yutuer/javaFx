package fasterLogger.write;

import fasterLogger.IDataProvider;

/**
 * @Description 写数据的源
 * @Author zhangfan
 * @Date 2020/9/4 12:56
 * @Version 1.0
 */
public class StringDataProvider implements IDataProvider
{
    private StringBuilder sb = new StringBuilder();

    private long actorId;

    private String msg;

    public StringDataProvider(String msg)
    {
        this.msg = msg;
    }

    public StringDataProvider(String msg, long actorId)
    {
        this(msg);
        this.actorId = actorId;
    }

    @Override
    public byte[] provideData()
    {
        sb.delete(0, sb.length());
        sb.append(msg);
        sb.append(", 当前时间为:");
        sb.append(actorId);
        sb.append("\t");
        return sb.toString().getBytes(getCharset());
    }
}
