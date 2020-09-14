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

    @Override
    public byte[] provideData()
    {
        sb.append(msg);
        sb.append(", hello:");
        sb.append(actorId);
        sb.append("\t");

        byte[] data = sb.toString().getBytes(getCharset());
        sb.delete(0, sb.length());
        return data;
    }

    @Override
    public IDataProvider wrap(String msg, long actorId, String content)
    {
        this.msg = msg;
        this.actorId = actorId;
        return this;
    }
}
