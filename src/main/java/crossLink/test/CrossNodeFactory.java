package crossLink.test;

import crossLink.aoi.BaseNode;
import crossLink.aoi.cross.CrossLinkNode;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/18 11:33
 * @Version 1.0
 */
public class CrossNodeFactory implements INodeFactory
{
    private long startLabel;

    public CrossNodeFactory(long startLabel)
    {
        this.startLabel = startLabel;
    }

    @Override
    public BaseNode create(int x, int y)
    {
        return new CrossLinkNode(++startLabel, x, y);
    }
}
