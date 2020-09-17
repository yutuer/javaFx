package crossLink.listener;

import crossLink.IAoi;
import crossLink.aoi.BaseNode;

/**
 * @Description aoi监听器
 * @Author zhangfan
 * @Date 2020/9/15 18:08
 * @Version 1.0
 */
public interface AoiListener<T extends BaseNode>
{
    /**
     * 添加
     *
     * @param aoi
     * @param node
     */
    void onAddNode(IAoi aoi, T node);

    /**
     * 删除
     *
     * @param aoi
     * @param node
     */
    void onRemoveNode(IAoi aoi, T node);

    /**
     * 移动到指定的x,y
     *
     * @param aoi
     * @param node
     * @param x
     * @param y
     */
    void beforeMoveTo(IAoi aoi, T node, int x, int y);

    /**
     * 从fromX,fromY移动到指定的位置
     *
     * @param aoi
     * @param node
     * @param fromX
     * @param fromY
     */
    void afterMoveTo(IAoi aoi, T node, int fromX, int fromY);


}
