package crossLink.listener;

import crossLink.IAoi;
import crossLink.aoi.cross.CrossLinkNode;
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
public class ChessListener implements AoiListener<CrossLinkNode>
{

    private Chessboard chessboard;

    public ChessListener(Chessboard chessboard)
    {
        this.chessboard = chessboard;
    }

    @Override
    public void onAddNode(IAoi aoi, CrossLinkNode node)
    {
        Shape shape = chessboard.addChess(node.x, node.y, false);
        Binder.bind(shape, node);
    }

    @Override
    public void onRemoveNode(IAoi aoi, CrossLinkNode node)
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
    public void beforeMoveTo(IAoi aoi, CrossLinkNode node, int x, int y)
    {

    }

    @Override
    public void afterMoveTo(IAoi aoi, CrossLinkNode node, int x, int y)
    {

    }
}
