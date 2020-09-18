package crossLink.test;

import crossLink.aoi.BaseNode;
import crossLink.aoi.cell.CellNode;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/18 11:34
 * @Version 1.0
 */
public class CellNodeFactory implements INodeFactory
{
    private long startLabel;

    public CellNodeFactory(long startLabel)
    {
        this.startLabel = startLabel;
    }

    @Override
    public BaseNode create(int x, int y)
    {
        return new CellNode(++startLabel, x, y);
    }
}
