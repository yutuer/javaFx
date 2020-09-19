package crossLink.aoi.cell;

import crossLink.IAoi;
import crossLink.aoi.AoiListenerManager;
import crossLink.test.CellNodeFactory;
import crossLink.test.INodeFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description T使用网格的aoi
 * @Author zhangfan
 * @Date 2020/9/17 11:44
 * @Version 1.0
 */
public class CellAoi extends AoiListenerManager<CellNode> implements IAoi<CellNode>
{
    private Map<Long, CellNode> nodes = new HashMap<>();

    public int xCellNum;
    public int yCellNum;
    public int cellSize;

    public Tower[][] towers;

    public boolean[][] marks;

    private INodeFactory<CellNode> nodeFactory;

    public CellAoi(int labelBegin, int xCellNum, int yCellNum, int cellSize)
    {
        this.xCellNum = xCellNum;
        this.yCellNum = yCellNum;
        this.cellSize = cellSize;

        nodeFactory = new CellNodeFactory(labelBegin);

        towers = new Tower[xCellNum][yCellNum];
        marks = new boolean[xCellNum][yCellNum];
    }

    @Override
    public void addNode(CellNode node)
    {
        int xCell = node.x / cellSize;
        int yCell = node.y / cellSize;

        Tower tower = towers[xCell][yCell];
        if (tower == null)
        {
            tower = new Tower();
            towers[xCell][yCell] = tower;
        }

        tower.set.add(node.label);
        node.towerX = xCell;
        node.towerY = yCell;

        nodes.put(node.label, node);

        onTriggerAddListener(this, node);
    }

    @Override
    public void addNode(int x, int y)
    {
        addNode(nodeFactory.create(x, y));
    }

    @Override
    public void removeNode(long label)
    {
        CellNode node = nodes.get(label);
        if (node == null)
        {
            return;
        }

        int xCell = node.x / cellSize;
        int yCell = node.y / cellSize;

        Tower tower = towers[xCell][yCell];
        if (tower != null)
        {
            tower.set.remove(node.label);
        }

        node.towerX = 0;
        node.towerY = 0;

        nodes.remove(node.label);

        onTriggerRemoveListener(this, node);
    }

    @Override
    public void moveNode(CellNode node, int x, int y)
    {
        final int _x = node.x;
        final int _y = node.y;

        onTriggerBeforeMoveToListener(this, node, x, y);

        node.moveTo(this, x, y);

        onTriggerAfterMoveToListener(this, node, _x, _y);
    }

    public void acceptDatas(int[] nodePos)
    {
        for (int i = 0, size = nodePos.length; i < size; i += 2)
        {
            addNode(nodeFactory.create(nodePos[i], nodePos[i + 1]));
        }
    }

    @Override
    public CellNode getNode(long label)
    {
        return nodes.get(label);
    }

    @Override
    public int getXRange()
    {
        return xCellNum * cellSize;
    }

    @Override
    public int getYRange()
    {
        return yCellNum * cellSize;
    }

    public CellNode getCellNode(long label)
    {
        return nodes.get(label);
    }
}
