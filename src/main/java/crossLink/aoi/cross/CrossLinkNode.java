package crossLink.aoi.cross;

import crossLink.Binder;
import crossLink.IAoi;
import crossLink.aoi.BaseNode;
import crossLink.test.AoiTest;
import javafx.scene.shape.Shape;

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

    @Override
    public void onMoveTo(IAoi aoi, int x, int y, int newX, int newY)
    {
        super.onMoveTo(aoi, x, y, newX, newY);

        //移动连接

        // 移除
        if (aoi instanceof CrossAoi)
        {
            CrossAoi crossAoi = CrossAoi.class.cast(aoi);
            crossAoi.makeDoubleLink(xPrev, xNext, true);
            crossAoi.makeDoubleLink(yPrev, yNext, false);

            crossAoi.addNode(this, newX - x, newY - y);
        }
    }

    @Override
    public void addRelation(BaseNode otherNode)
    {
        super.addRelation(otherNode);

        if (otherNode != null)
        {
            Shape shape = Binder.get(otherNode.label);
            if (shape != null)
            {
                shape.setFill(addColor);
            }
        }
    }

    @Override
    public void removeRelation(BaseNode otherNode)
    {
        super.removeRelation(otherNode);

        // 移除的时候, 是还存留在图上的做主语. 所以这里应该用label
        Shape shape = Binder.get(label);
        if (shape != null)
        {
            shape.setFill(removeColor);
        }
    }
}
