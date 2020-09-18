package crossLink.aoi.cell;

import crossLink.Binder;
import crossLink.IAoi;
import crossLink.aoi.BaseNode;
import javafx.scene.shape.Shape;

import java.util.Set;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/17 11:45
 * @Version 1.0
 */
public class CellNode extends BaseNode
{
    public int towerX;
    public int towerY;

    public CellNode(long label, int x, int y)
    {
        super(label, x, y);
    }

    @Override
    public void onMoveTo(IAoi aoi, int x, int y, int newX, int newY)
    {
        super.onMoveTo(aoi, x, y, newX, newY);

        if (!(aoi instanceof CellAoi))
        {
            return;
        }

        CellAoi cellAoi = CellAoi.class.cast(aoi);
        int xCell = newX / cellAoi.cellSize;
        int yCell = newY / cellAoi.cellSize;

        int left = xCell - 1;
        if (left < 0)
        {
            left = 0;
        }

        int right = xCell + 1;
        if (right > cellAoi.xCellNum - 1)
        {
            right = cellAoi.xCellNum - 1;
        }

        int upper = yCell - 1;
        if (upper < 0)
        {
            upper = 0;
        }

        int down = yCell + 1;
        if (down > cellAoi.yCellNum - 1)
        {
            down = cellAoi.yCellNum - 1;
        }

        // 先都设置为true
        for (int i = left; i <= right; i++)
        {
            for (int j = upper; j <= down; j++)
            {
                cellAoi.marks[i][j] = true;
            }
        }

        int _xCell = towerX;
        int _yCell = towerY;

        int _left = _xCell - 1;
        if (_left < 0)
        {
            _left = 0;
        }

        int _right = _xCell + 1;
        if (_right > cellAoi.xCellNum - 1)
        {
            _right = cellAoi.xCellNum - 1;
        }

        int _upper = _yCell - 1;
        if (_upper < 0)
        {
            _upper = 0;
        }

        int _down = _yCell + 1;
        if (_down > cellAoi.yCellNum - 1)
        {
            _down = cellAoi.yCellNum - 1;
        }

        for (int i = _left; i <= _right; i++)
        {
            for (int j = _upper; j <= _down; j++)
            {
                if (cellAoi.marks[i][j])
                {
                    cellAoi.marks[i][j] = false;
                    // 广播移动
                    Tower tower = cellAoi.towers[i][j];
                    if (tower != null)
                    {
                        Set<Long> set = tower.set;
                        if (!set.isEmpty())
                        {
                            for (Long aid : set)
                            {
                                if (aid != label)
                                {
                                    this.onMoveBroad(cellAoi.getCellNode(aid));
                                }
                            }
                        }
                    }
                }
                else
                {
                    // 广播移除
                    Tower tower = cellAoi.towers[i][j];
                    if (tower != null)
                    {
                        Set<Long> set = tower.set;
                        if (!set.isEmpty())
                        {
                            for (Long aid : set)
                            {
                                if (aid != label)
                                {
                                    // 告诉对方移除自己, 并删除双方联系
                                    CellNode cellNode = cellAoi.getCellNode(aid);
                                    if (cellNode != null)
                                    {
                                        cellNode.removeRelation(this);
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }

        for (int i = left; i <= right; i++)
        {
            for (int j = upper; j <= down; j++)
            {
                if (cellAoi.marks[i][j])
                {
                    cellAoi.marks[i][j] = false;
                    // 广播添加
                    Tower tower = cellAoi.towers[i][j];
                    if (tower != null)
                    {
                        Set<Long> set = tower.set;
                        if (set.size() > 0)
                        {
                            for (Long aid : set)
                            {
                                if (aid != label)
                                {
                                    //互相广播 进入视野, 并建立联系
                                    this.addRelation(cellAoi.getCellNode(aid));
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public void moveTo(IAoi aoi, int newX, int newY)
    {
        super.moveTo(aoi, newX, newY);
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
    public void removeRelation(CellNode otherNode)
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
