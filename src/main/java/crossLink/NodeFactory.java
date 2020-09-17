package crossLink;

import crossLink.aoi.cross.CrossLinkNode;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/15 17:46
 * @Version 1.0
 */
public class NodeFactory
{

    public static CrossLinkNode createInstance(long label, int x, int y)
    {
        return new CrossLinkNode(label, x, y);
    }
}
