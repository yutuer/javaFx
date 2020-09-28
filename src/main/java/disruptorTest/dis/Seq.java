package disruptorTest.dis;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/27 23:26
 * @Version 1.0
 */
public interface Seq extends Cursor
{
    /**
     * @return
     */
    long next();
}
