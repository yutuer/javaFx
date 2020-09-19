package crossLink.javaFx;

import crossLink.IAoi;
import crossLink.aoi.AoiListenerManager;
import crossLink.aoi.BaseNode;
import crossLink.listener.CellBroadListener;
import crossLink.listener.ChessBindListener;
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
        int num = PropertiesUtils.getInt(properties, "num", 200);

        int maxX = PropertiesUtils.getInt(properties, "maxX", 20);
        int maxY = PropertiesUtils.getInt(properties, "maxY", 20);
        int scale = PropertiesUtils.getInt(properties, "scale", 50);

        final int debugWidth = PropertiesUtils.getInt(properties, "xRange", 100);
        final int debugHeight = PropertiesUtils.getInt(properties, "yRange", 100);

        int xRange = maxX * scale;
        int yRange = maxY * scale;

        AoiListenerManager listenerManager = null;
//      listenerManager = new CrossAoi(xRange, yRange);
//        listenerManager = new CellAoi(maxX, maxY, scale);

        IAoi iaoi = IAoi.class.cast(listenerManager);

        Pane root = new Pane();

        Chessboard chessboard = new Chessboard(root, scale, maxX, maxY, false);
        chessboard.draw();

        ChessBindListener chessListener = new ChessBindListener(chessboard);
        listenerManager.addListenerToLast(chessListener);

        int[] baseNodes = new int[num * 2];

        Random random = new Random();
        for (int i = 1; i <= num; i++)
        {
//            BaseNode baseNode = new CrossLinkNode(i, (int) (random.nextDouble() * xRange), (int) (random.nextDouble() * yRange));

            baseNodes[i * 2] = (int) (random.nextDouble() * xRange);
            baseNodes[i * 2 + 1] = (int) (random.nextDouble() * yRange);
        }
        iaoi.acceptDatas(baseNodes);

//        CrossLinkBroadListener broadListener = new CrossLinkBroadListener(debugWidth, debugHeight);
//        listenerManager.addListener(broadListener);

//        DrawRecListener debugListener = new DrawRecListener(chessboard, debugWidth, debugHeight);
//        listenerManager.addListener(debugListener);

        CellBroadListener cellBroadListener = new CellBroadListener();
        listenerManager.addListenerToFirst(cellBroadListener);

        // region 操作

//        iaoi.addNode(new CellNode(num + 1, (int) (random.nextDouble() * xRange), (int) (random.nextDouble() * yRange)));

//        iaoi.removeNode(first);

        BaseNode first = iaoi.getNode(1);
        boolean isLeft = first.x > (xRange / 2);
        boolean isUpper = first.y > (yRange / 2);

        final int dis = 100;

        final BaseNode f = first;
        iaoi.moveNode(f, f.x + (isLeft ? -1 * dis : dis), f.y + (isUpper ? -1 * dis : dis));

        // endRegion

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("CrossAoi Test");
        stage.show();
    }
}
