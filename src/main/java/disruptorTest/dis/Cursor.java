package disruptorTest.dis;

/**
 * @Description 游标
 * @Author zhangfan
 * @Date 2020/9/28 8:23
 * @Version 1.0
 */
public interface Cursor
{
    /**
     * 得到当前值
     *
     * @return
     */
    long getCursor();
}
