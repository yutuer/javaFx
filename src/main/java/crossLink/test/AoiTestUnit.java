package crossLink.test;

import crossLink.IAoi;
import crossLink.aoi.AoiListenerManager;
import crossLink.aoi.cell.CellAoi;
import crossLink.aoi.cross.CrossAoi;
import crossLink.listener.CellBroadListener;
import crossLink.listener.CrossLinkBroadListener;
import util.Log;

import java.util.Random;

/**
 * @Description aoi测试单元
 * @Author zhangfan
 * @Date 2020/9/19 15:59
 * @Version 1.0
 */
public class AoiTestUnit
{

    public int defaultNodeNum;
    public int total;

    public int maxX;
    public int maxY;
    public int scale;

    int xRange;
    int yRange;

    Random random;

    private AddNodeTestUnit addNodeTestUnit;
    private RemoveNodeTestUnit removeNodeTestUnit;

    private int[] baseNodes;

    AoiListenerManager listenerManager;
    IAoi iaoi;

    public AoiTestUnit(int defaultNodeNum, int maxX, int maxY, int scale)
    {
        this.defaultNodeNum = defaultNodeNum;
        this.maxX = maxX;
        this.maxY = maxY;
        this.scale = scale;

        this.xRange = maxX * scale;
        this.yRange = maxY * scale;

        AoiTest.SCALE = scale;

        random = new Random();
    }

    public void initNodes()
    {
        this.baseNodes = new int[defaultNodeNum * 2];
        for (int i = 0; i < defaultNodeNum; i++)
        {
            baseNodes[i * 2] = (int) (random.nextDouble() * xRange);
            baseNodes[i * 2 + 1] = (int) (random.nextDouble() * yRange);
        }
        total = defaultNodeNum;
    }

    /**
     * 构造add测试数据, 可以重复调用
     *
     * @param addNum
     * @return
     */
    public AoiTestUnit makeTestAddNodeData(int addNum)
    {
        int[] addNodes = new int[addNum * 2];
        for (int i = 0; i < addNum; i++)
        {
            addNodes[i * 2] = (int) (random.nextDouble() * xRange);
            addNodes[i * 2 + 1] = (int) (random.nextDouble() * yRange);
        }
        total += addNum;

        this.addNodeTestUnit = new AddNodeTestUnit(addNodes);

        return this;
    }

    /**
     * 删除测试
     *
     * @param from
     * @param to
     * @return
     */
    public AoiTestUnit makeTestRemoveDataRange(int from, int to)
    {
        this.removeNodeTestUnit = new RemoveNodeTestUnit(from, to);
        return this;
    }

    /**
     * 删除测试
     *
     * @return
     */
    public AoiTestUnit makeTestRemoveDataAll()
    {
        this.removeNodeTestUnit = new RemoveNodeTestUnit(1, total);
        return this;
    }

    /**
     * 移动测试
     *
     * @param v 节点速度v
     * @return
     */
    public AoiTestUnit makeTestMoveData(int label, int v)
    {
        MoveNodeTestUnit moveNodeTestUnit = new MoveNodeTestUnit(label, v);
        return this;
    }

    /**
     * 使用init的数据 重新构造一个aoi对象
     *
     * @param type
     * @return
     */
    public AoiTestUnit loadAoi(String type)
    {
        if ("cell".equalsIgnoreCase(type))
        {
            //Cell
            listenerManager = new CellAoi(0, maxX, maxY, scale);
            iaoi = IAoi.class.cast(listenerManager);
            iaoi.acceptDatas(baseNodes);

            CellBroadListener cellBroadListener = new CellBroadListener();
            listenerManager.addListenerToFirst(cellBroadListener);
        }
        else
        {
            listenerManager = new CrossAoi(0, xRange, yRange);
            iaoi = IAoi.class.cast(listenerManager);
            iaoi.acceptDatas(baseNodes);

            CrossLinkBroadListener crossLinkBroadListener = new CrossLinkBroadListener(scale * 3 / 2, scale * 3 / 2);
            listenerManager.addListenerToLast(crossLinkBroadListener);
        }

        return this;
    }

    public void testAdd()
    {
        if (addNodeTestUnit == null)
        {
            return;
        }

        long now = System.currentTimeMillis();
        addNodeTestUnit.accpetInput(iaoi);
        Log.CrossAOI_Logger.warn("{} addTest costTime:{}", iaoi.getClass().getSimpleName(), System.currentTimeMillis() - now);
    }

    public void testRemove()
    {
        if (removeNodeTestUnit == null)
        {
            return;
        }

        long now = System.currentTimeMillis();
        removeNodeTestUnit.accpetInput(iaoi);
        Log.CrossAOI_Logger.warn("{} removeTest costTime:{}", iaoi.getClass().getSimpleName(), System.currentTimeMillis() - now);
    }
}
