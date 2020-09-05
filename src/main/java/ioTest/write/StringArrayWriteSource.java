package ioTest.write;

import java.nio.charset.Charset;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/4 21:54
 * @Version 1.0
 */
public class StringArrayWriteSource
{

    private StringWriteSource[] stringWriteSources;

    public StringArrayWriteSource(int len)
    {
        this.stringWriteSources = new StringWriteSource[len];
        for (int i = 0; i < len; i++)
        {
            stringWriteSources[i] = new StringWriteSource();
        }
    }

    public void writeString(WriterBuffer writerBuffer)
    {
        for (int i = 0, len = stringWriteSources.length; i < len; i++)
        {
            StringWriteSource stringWriteSource = stringWriteSources[i];
            writerBuffer.write(stringWriteSource.getSource().getBytes(Charset.forName("UTF8")));
        }
    }
}
