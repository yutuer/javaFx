package crossLink.listener;

import crossLink.BaseNode;
import crossLink.javaFx.Chessboard;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/16 12:16
 * @Version 1.0
 */
public class DebugListener implements AoiListener
{
    private Chessboard chessboard;

    private int debugWidth;
    private int debugHeight;

    public DebugListener(Chessboard chessboard, int debugWidth, int debugHeight)
    {
        this.chessboard = chessboard;
        this.debugWidth = debugWidth;
        this.debugHeight = debugHeight;
    }

    @Override
    public void onAddNode(BaseNode node)
    {
        chessboard.addDebugRec(node, this.debugWidth, this.debugHeight);
    }

    @Override
    public void onRemoveNode(BaseNode node)
    {
        chessboard.addDebugRec(node, this.debugWidth, this.debugHeight);
    }

    @Override
    public void onTeleportMoveTo(BaseNode node, int x, int y)
    {

    }
}
