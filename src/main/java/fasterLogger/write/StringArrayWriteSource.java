package fasterLogger.write;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/4 21:54
 * @Version 1.0
 */
public class StringArrayWriteSource
{

    private StringDataProvider[] stringWriteSources;

    public StringArrayWriteSource(int len)
    {
        this.stringWriteSources = new StringDataProvider[len];
        for (int i = 0; i < len; i++)
        {
            stringWriteSources[i] = new StringDataProvider("");
        }
    }

    public void writeString(WriterBuffer writerBuffer)
    {
        for (int i = 0, len = stringWriteSources.length; i < len; i++)
        {
            StringDataProvider stringWriteSource = stringWriteSources[i];
            writerBuffer.write(stringWriteSource.provideData());
        }
    }
}
