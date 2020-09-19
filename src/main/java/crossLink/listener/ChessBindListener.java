package crossLink.listener;

import crossLink.Binder;
import crossLink.IAoi;
import crossLink.aoi.BaseNode;
import crossLink.javaFx.Chessboard;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import util.Log;

/**
 * @Description 将节点和棋盘形状绑定起来的监听器
 * @Author zhangfan
 * @Date 2020/9/15 20:26
 * @Version 1.0
 */
public class ChessBindListener implements AoiListener<BaseNode>
{

    private Chessboard chessboard;

    private boolean isRandomColor;

    public ChessBindListener(Chessboard chessboard)
    {
        this(chessboard, false);
    }

    public ChessBindListener(Chessboard chessboard, boolean isRandomColor)
    {
        this.chessboard = chessboard;
        this.isRandomColor = isRandomColor;
    }

    @Override
    public void onAddNode(IAoi aoi, BaseNode node)
    {
        Shape shape = chessboard.addChess(node.x, node.y, isRandomColor);
        if (!isRandomColor)
        {
            // 自己变色
            Binder.changeColor(node.label, Color.GOLD);
        }

        Binder.bind(shape, node.label);
    }

    @Override
    public void onRemoveNode(IAoi aoi, BaseNode node)
    {
        Log.CrossAOI_Logger.info("removeNode: [{} {}]", node.x, node.y);

        Shape shape = Binder.removeBind(node.label);
        if (shape != null)
        {
            // 调试, 所以先注释掉了
//            chessboard.removeChess(shape);

            if (!isRandomColor)
            {
                // 自己变色
                Binder.changeColor(node.label, Color.GOLD);
            }
        }
    }

    @Override
    public void beforeMoveTo(IAoi aoi, BaseNode node, int x, int y)
    {

    }

    @Override
    public void afterMoveTo(IAoi aoi, BaseNode node, int x, int y)
    {

    }
}
