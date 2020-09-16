package crossLink;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/15 17:46
 * @Version 1.0
 */
public class NodeFactory
{

    public static BaseNode createInstance(int x, int y)
    {
        return new BaseNode(x, y);
    }
}
