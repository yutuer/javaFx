package crossLink.listener;

import crossLink.IAoi;
import crossLink.aoi.BaseNode;
import crossLink.javaFx.Chessboard;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/16 12:16
 * @Version 1.0
 */
public class DrawRecListener implements AoiListener<BaseNode>
{
    private Chessboard chessboard;

    private int debugWidth;
    private int debugHeight;

    public DrawRecListener(Chessboard chessboard, int debugWidth, int debugHeight)
    {
        this.chessboard = chessboard;
        this.debugWidth = debugWidth;
        this.debugHeight = debugHeight;
    }

    @Override
    public void onAddNode(IAoi aoi, BaseNode node)
    {
        chessboard.addDebugRec(node, this.debugWidth, this.debugHeight);
    }

    @Override
    public void onRemoveNode(IAoi aoi, BaseNode node)
    {
        chessboard.addDebugRec(node, this.debugWidth, this.debugHeight);
    }

    @Override
    public void beforeMoveTo(IAoi aoi, BaseNode node, int x, int y)
    {
        chessboard.addDebugRec(node, this.debugWidth, this.debugHeight);
    }

    @Override
    public void afterMoveTo(IAoi aoi, BaseNode node, int x, int y)
    {
        chessboard.addDebugRec(node, this.debugWidth, this.debugHeight);
    }
}
