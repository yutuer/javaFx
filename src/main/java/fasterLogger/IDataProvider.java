package fasterLogger;

import java.nio.charset.Charset;

/**
 * @Description 写数据来源 接口
 * @Author zhangfan
 * @Date 2020/9/4 12:56
 * @Version 1.0
 */
public interface IDataProvider
{
    /**
     * 获取数据
     *
     * @return
     */
    byte[] getData();

    default Charset getCharset()
    {
        return Charset.forName("UTF8");
    }
}
