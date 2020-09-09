package fasterLogger.write;

import fasterLogger.IDataProvider;

/**
 * @Description 写数据的源
 * @Author zhangfan
 * @Date 2020/9/4 12:56
 * @Version 1.0
 */
public class StringWriteSource implements IDataProvider
{
    private StringBuilder sb = new StringBuilder();

    private String msg;
    private long actorId;

    public StringWriteSource(String msg)
    {
        this.msg = msg;
    }

    public StringWriteSource(String msg, long actorId)
    {
        this(msg);
        this.actorId = actorId;
    }

    @Override
    public byte[] getData()
    {
        sb.delete(0, sb.length());
        sb.append(msg);
        sb.append(", 当前时间为:");
        sb.append(actorId);
        sb.append("\t");
        return sb.toString().getBytes(getCharset());
    }
}
