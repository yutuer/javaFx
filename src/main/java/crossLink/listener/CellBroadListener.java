package crossLink.listener;

import crossLink.IAoi;
import crossLink.aoi.cell.CellAoi;
import crossLink.aoi.cell.CellNode;
import crossLink.aoi.cell.Tower;
import util.Log;

import java.util.Set;

/**
 * @Description cell 广播策略
 * @Author zhangfan
 * @Date 2020/9/17 15:20
 * @Version 1.0
 */
public class CellBroadListener implements AoiListener<CellNode>
{
    @Override
    public void onAddNode(IAoi aoi, CellNode node)
    {
        // 获取所有广播的对象
        if (aoi instanceof CellAoi)
        {
            CellAoi cellAoi = CellAoi.class.cast(aoi);

            int xCell = node.x / cellAoi.cellSize;
            int yCell = node.y / cellAoi.cellSize;

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

            for (int i = left; i <= right; i++)
            {
                for (int j = upper; j <= down; j++)
                {
                    Tower tower = cellAoi.towers[i][j];
                    if (tower != null)
                    {
                        Set<Long> set = tower.set;
                        if (set.size() > 0)
                        {
                            for (Long aid : set)
                            {
                                if (aid != node.label)
                                {
                                    //互相广播 进入视野, 并建立联系
                                    node.addRelation(cellAoi.getCellNode(aid));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onRemoveNode(IAoi aoi, CellNode node)
    {
        if (aoi instanceof CellAoi)
        {
            CellAoi cellAoi = CellAoi.class.cast(aoi);

            int xCell = node.x / cellAoi.cellSize;
            int yCell = node.y / cellAoi.cellSize;

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

            for (int i = left; i <= right; i++)
            {
                for (int j = upper; j <= down; j++)
                {
                    Tower tower = cellAoi.towers[i][j];
                    if (tower == null)
                    {
                        continue;
                    }

                    Set<Long> set = tower.set;
                    if (!set.isEmpty())
                    {
                        for (Long aid : set)
                        {
                            // 告诉对方移除自己, 并删除双方联系
                            CellNode cellNode = cellAoi.getCellNode(aid);
                            if (cellNode != null)
                            {
                                cellNode.removeRelation(node);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void beforeMoveTo(IAoi aoi, CellNode node, int x, int y)
    {
        Log.CrossAOI_Logger.info("{} beforeMoveTo {} {}", node.label, x, y);
    }

    @Override
    public void afterMoveTo(IAoi aoi, CellNode node, int fromX, int fromY)
    {
        if (!(aoi instanceof CellAoi))
        {
            return;
        }

        CellAoi cellAoi = CellAoi.class.cast(aoi);

        int xCell = fromX / cellAoi.cellSize;
        int yCell = fromY / cellAoi.cellSize;
        Tower tower = cellAoi.towers[xCell][yCell];

        int newXCell = node.x / cellAoi.cellSize;
        int newYCell = node.y / cellAoi.cellSize;
        Tower _tower = cellAoi.towers[newXCell][newYCell];

        if (tower == _tower)
        {
            // 广播移动

        }
    }
}
