package crossLink.test;

import crossLink.IAoi;
import crossLink.aoi.AoiListenerManager;
import crossLink.aoi.cell.CellAoi;
import crossLink.aoi.cross.CrossAoi;
import crossLink.listener.CellBroadListener;
import crossLink.listener.CrossLinkBroadListener;
import util.Log;
import util.PropertiesUtils;

import java.util.Properties;
import java.util.Random;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/18 16:55
 * @Version 1.0
 */
public class AoiTest
{
    public static int SCALE = 1;

    public static void main(String[] args)
    {
        Random random = new Random();

        Properties properties = PropertiesUtils.getProperties("crossLink.properties");
        int defaultNodeNum = PropertiesUtils.getInt(properties, "defaultNodeNum", 200);
        int total = defaultNodeNum;

        int maxX = PropertiesUtils.getInt(properties, "maxX", 20);
        int maxY = PropertiesUtils.getInt(properties, "maxY", 20);
        int scale = PropertiesUtils.getInt(properties, "scale", 50);
        SCALE = scale;

        int xRange = maxX * scale;
        int yRange = maxY * scale;

        int[] baseNodes = new int[defaultNodeNum * 2];
        for (int i = 0; i < defaultNodeNum; i++)
        {
            baseNodes[i * 2] = (int) (random.nextDouble() * xRange);
            baseNodes[i * 2 + 1] = (int) (random.nextDouble() * yRange);
        }

        // 1. 添加的数据
        int addNum = PropertiesUtils.getInt(properties, "addNum", 1);
        int[] addNodes = new int[addNum * 2];
        for (int i = 0; i < addNum; i++)
        {
            addNodes[i * 2] = (int) (random.nextDouble() * xRange);
            addNodes[i * 2 + 1] = (int) (random.nextDouble() * yRange);
        }
        total += addNum;
        AddNodeTestUnit addNodeTestUnit = new AddNodeTestUnit(addNodes);

        // 2. 删除的数据 From - To
        RemoveNodeTestUnit removeNodeTestUnit = new RemoveNodeTestUnit(1, total);

        // 3. 移动的数据
        MoveNodeTestUnit moveNodeTestUnit = new MoveNodeTestUnit(total, 5);


        AoiListenerManager listenerManager;
        IAoi iaoi;

        //Cell
        listenerManager = new CellAoi(maxX, maxY, scale);
        iaoi = IAoi.class.cast(listenerManager);
        iaoi.acceptDatas(baseNodes);

        CellBroadListener cellBroadListener = new CellBroadListener();
        listenerManager.addListenerToFirst(cellBroadListener);

        long now = System.currentTimeMillis();

        //cell 添加
        addNodeTestUnit.accpetInput(iaoi);
//        // cell 删除
        removeNodeTestUnit.accpetInput(iaoi);
        Log.CrossAOI_Logger.warn("Cell addTest costTime:{}", System.currentTimeMillis() - now);
//        // cell 移动
//        moveNodeTestUnit.accpetInput(iaoi);

        //CrossLink
        listenerManager = new CrossAoi(xRange, yRange);
        iaoi = IAoi.class.cast(listenerManager);
        iaoi.acceptDatas(baseNodes);

        CrossLinkBroadListener crossLinkBroadListener = new CrossLinkBroadListener(scale * 3 / 2, scale * 3 / 2);
        listenerManager.addListenerToLast(crossLinkBroadListener);

        now = System.currentTimeMillis();

        // crossLink 添加
        addNodeTestUnit.accpetInput(iaoi);
//        // crossLink 删除 From - To
        removeNodeTestUnit.accpetInput(iaoi);
        Log.CrossAOI_Logger.warn("CrossLink addTest costTime:{}", System.currentTimeMillis() - now);
//        // crossLink 移动
//        moveNodeTestUnit.accpetInput(iaoi);

//        Log.CrossAOI_Logger.info("------------------------------------------------");
//
//        {
//            new RemoveNodeTestUnit(total / 2, total / 2 + 1).accpetInput(iaoi);
//        }

//        {
//            MoveNodeTestUnit moveNodeTestUnit = new MoveNodeTestUnit(total, 5);
//            moveNodeTestUnit.accpetInput(iaoi);
//
//            Executors.newScheduledThreadPool(1).scheduleAtFixedRate(moveNodeTestUnit, 1000, 2000, TimeUnit.MILLISECONDS);
//        }
    }
}
