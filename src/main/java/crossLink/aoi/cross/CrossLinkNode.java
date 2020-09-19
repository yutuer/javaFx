package crossLink.aoi.cross;

import crossLink.aoi.BaseNode;
import crossLink.test.AoiTest;

/**
 * @Description 基类 , 为了逻辑能动态增减, 使用监听器
 * @Author zhangfan
 * @Date 2020/9/15 17:28
 * @Version 1.0
 */
public class CrossLinkNode extends BaseNode
{
    // x 轴
    public CrossLinkNode xPrev;
    public CrossLinkNode xNext;

    // y 轴
    public CrossLinkNode yPrev;
    public CrossLinkNode yNext;

    public CrossLinkNode(long label, int x, int y)
    {
        super(label, x, y);
    }

    protected String getTag()
    {
        return "CrossLink";
    }

    protected String getReason(BaseNode otherNode)
    {
        if (otherNode instanceof CrossLinkNode)
        {
            return String.format("[diffX:%d diffY:%d]  [diffTowerX:%d  diffTowerY:%d]",
                    Math.abs(x - otherNode.x), Math.abs(y - otherNode.y),
                    Math.abs(x / AoiTest.SCALE - otherNode.x / AoiTest.SCALE), Math.abs(y / AoiTest.SCALE - otherNode.y / AoiTest.SCALE)
            );
        }
        return "";
    }
}
