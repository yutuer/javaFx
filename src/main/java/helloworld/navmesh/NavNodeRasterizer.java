package helloworld.navmesh;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/7 20:02
 * @Version 1.0
 */
public class NavNodeRasterizer
{

    /**
     * 左上
     */
    private final VectorInt2 leftUpper;

    /**
     * 右下
     */
    private final VectorInt2 rightDown;
    /**
     * 所有的navNode
     */
    private NavNode[] allNodes;

    public NavNodeRasterizer(VectorInt2 leftUpper, VectorInt2 rightDown, NavNode[] allNodes)
    {
        this.leftUpper = leftUpper;
        this.rightDown = rightDown;
        this.allNodes = allNodes;
    }

    public NavNode[] getAllNodes()
    {
        return allNodes;
    }

    public void setAllNodes(NavNode[] allNodes)
    {
        this.allNodes = allNodes;
    }

    /**
     * 栅格化
     * 使用格子划分三角形, 为了快速找到点在哪个索引的三角形内
     */
    public void shangehua()
    {
        double width = rightDown.x - leftUpper.x;
        double height = rightDown.z - leftUpper.z;

        int xs = (int) Math.ceil(width / NavConst.CELLSIZE);
        int ys = (int) Math.ceil(height / NavConst.CELLSIZE);

        Cell[][] cells = new Cell[xs][ys];

    }

    /**
     * @param start
     * @param end
     * @return
     */
    public PathNode[] findPath(VectorInt2 start, VectorInt2 end)
    {


//        new PathNode(null, )

        while (NavUtil.isInSameTrigger(start, end))
        {

        }

        return null;
    }
}
