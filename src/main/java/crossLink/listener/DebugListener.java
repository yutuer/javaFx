package crossLink.listener;

import crossLink.IAoi;
import crossLink.aoi.cross.CrossLinkNode;
import crossLink.javaFx.Chessboard;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/16 12:16
 * @Version 1.0
 */
public class DebugListener implements AoiListener<CrossLinkNode>
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
    public void onAddNode(IAoi aoi, CrossLinkNode node)
    {
        chessboard.addDebugRec(node, this.debugWidth, this.debugHeight);
    }

    @Override
    public void onRemoveNode(IAoi aoi, CrossLinkNode node)
    {
        chessboard.addDebugRec(node, this.debugWidth, this.debugHeight);
    }

    @Override
    public void beforeMoveTo(IAoi aoi, CrossLinkNode node, int x, int y)
    {
        chessboard.addDebugRec(node, this.debugWidth, this.debugHeight);
    }

    @Override
    public void afterMoveTo(IAoi aoi, CrossLinkNode node, int x, int y)
    {
        chessboard.addDebugRec(node, this.debugWidth, this.debugHeight);
    }
}
