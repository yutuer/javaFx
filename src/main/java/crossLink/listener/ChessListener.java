package crossLink.listener;

import crossLink.BaseNode;
import crossLink.Binder;
import crossLink.javaFx.Chessboard;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import util.Log;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/15 20:26
 * @Version 1.0
 */
public class ChessListener implements AoiListener
{

    private Chessboard chessboard;

    public ChessListener(Chessboard chessboard)
    {
        this.chessboard = chessboard;
    }

    @Override
    public void onAddNode(BaseNode node)
    {
        Shape shape = chessboard.addChess(node.x, node.y, false);
        Binder.bind(shape, node);
    }

    @Override
    public void onRemoveNode(BaseNode node)
    {
        Log.CrossAOI_Logger.info("removeNode: [{} {}]", node.x, node.y);

        Shape shape = Binder.removeBind(node);
        if (shape != null)
        {
//            chessboard.removeChess(shape);

            // 调试代码
            shape.setFill(Color.BROWN);
        }
    }

    @Override
    public void onTeleportMoveTo(BaseNode node, int x, int y)
    {

    }
}
