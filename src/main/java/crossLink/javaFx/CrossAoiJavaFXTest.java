package crossLink.javaFx;

import crossLink.IAoi;
import crossLink.aoi.AoiListenerManager;
import crossLink.aoi.BaseNode;
import crossLink.aoi.cross.CrossAoi;
import crossLink.listener.ChessBindListener;
import crossLink.listener.CrossLinkBroadListener;
import crossLink.listener.DrawRecListener;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.PropertiesUtils;

import java.util.Properties;
import java.util.Random;

/**
 * @Description aoi test
 * @Author zhangfan
 * @Date 2020/9/15 18:53
 * @Version 1.0
 */
public class CrossAoiJavaFXTest extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        /*
         广播范围会使用方框框起来

         被广播的节点标记为红色
         添加形式的节点标记为金色

         删除的节点不会移除, 被标记为褐色

         move 是先删除, 然后添加
         */
        Properties properties = PropertiesUtils.getProperties("crossLink.properties");
        int isDebug = PropertiesUtils.getInt(properties, "isDebug", 1);
        int isX = PropertiesUtils.getInt(properties, "isX", 1);

        int num = PropertiesUtils.getInt(properties, "defaultNodeNum", 200);

        int maxX = PropertiesUtils.getInt(properties, "maxX", 20);
        int maxY = PropertiesUtils.getInt(properties, "maxY", 20);
        int scale = PropertiesUtils.getInt(properties, "scale", 50);

        final int debugWidth = PropertiesUtils.getInt(properties, "debugWidth", 100);
        final int debugHeight = PropertiesUtils.getInt(properties, "debugHeight", 100);

        int xRange = maxX * scale;
        int yRange = maxY * scale;

        AoiListenerManager listenerManager = null;
        listenerManager = new CrossAoi(0, xRange, yRange, scale);
//        listenerManager = new CellAoi(0, maxX, maxY, scale);
        IAoi iaoi = IAoi.class.cast(listenerManager);

        Pane root = new Pane();

        Chessboard chessboard = new Chessboard(root, scale, maxX, maxY, false);
        chessboard.draw();

        ChessBindListener chessListener = new ChessBindListener(chessboard);
        listenerManager.addListenerToLast(chessListener);

        int[] baseNodes = new int[num * 2];

        Random random = new Random();
        for (int i = 0; i < num; i++)
        {
            // for debug
            if (isDebug == 1)
            {
                if (isX == 1)
                {
                    baseNodes[i * 2] = (int) (40);
                    baseNodes[i * 2 + 1] = (int) (random.nextDouble() * yRange);
                }
                else
                {
                    baseNodes[i * 2] = (int) (random.nextDouble() * xRange);
                    baseNodes[i * 2 + 1] = (int) (80);
                }
            }
            else
            {
                baseNodes[i * 2] = (int) (random.nextDouble() * xRange);
                baseNodes[i * 2 + 1] = (int) (random.nextDouble() * yRange);
            }
        }
        iaoi.acceptDatas(baseNodes);

        CrossLinkBroadListener broadListener = new CrossLinkBroadListener(debugWidth, debugHeight);
        listenerManager.addListenerToLast(broadListener);

        DrawRecListener debugListener = new DrawRecListener(chessboard, debugWidth, debugHeight);
        listenerManager.addListenerToLast(debugListener);

//        CellBroadListener cellBroadListener = new CellBroadListener();
//        listenerManager.addListenerToFirst(cellBroadListener);

        // region 操作

        //for debug
//        if (isDebug == 1)
//        {
//            iaoi.addNode(new CrossLinkNode(num + 1, (int) (40), (int) (random.nextDouble() * yRange)));
//        }
//        else
//        {
//            //for Test
//            iaoi.addNode(new CrossLinkNode(num + 1, (int) (random.nextDouble() * xRange), (int) (random.nextDouble() * yRange)));
//        }

//        iaoi.removeNode(1);

        BaseNode first = iaoi.getNode(1);
        boolean isLeft = first.x > (xRange / 2);
        boolean isUpper = first.y > (yRange / 2);

        final int dis = 75;

        final BaseNode f = first;
        if (isDebug == 1)
        {
            if (isX == 1)
            {
                iaoi.moveNode(f, f.x, f.y + (isUpper ? -1 * dis : dis));
            }
            else
            {
                iaoi.moveNode(f, f.x + (isLeft ? -1 * dis : dis), f.y);
            }
        }
        else
        {
            iaoi.moveNode(f, f.x + (isLeft ? -1 * dis : dis), f.y + (isUpper ? -1 * dis : dis));
        }

        // endRegion

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("CrossAoi Test");
        stage.show();
    }
}
