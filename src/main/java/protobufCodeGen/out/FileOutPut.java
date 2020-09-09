package protobufCodeGen.out;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

/**
 * @Description
 * @Author zhangfan
 * @Date 2020/9/9 17:04
 * @Version 1.0
 */
public class FileOutPut implements IOutPut
{

    /**
     * 路径
     */
    private String path;

    public FileOutPut(String path)
    {
        this.path = path;
    }

    @Override
    public void output(String content)
    {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path)))
        {
            bos.write(content.getBytes(Charset.forName("UTF8")));
            bos.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
