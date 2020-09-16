package crossLink.listener;

import crossLink.BaseNode;

/**
 * @Description aoi监听器
 * @Author zhangfan
 * @Date 2020/9/15 18:08
 * @Version 1.0
 */
public interface AoiListener
{
    /**
     * 添加
     *
     * @param node
     */
    void onAddNode(BaseNode node);

    /**
     * 删除
     *
     * @param node
     */
    void onRemoveNode(BaseNode node);

    /**
     * 传送移动到指定的x,y
     *
     * @param node
     * @param x
     * @param y
     */
    void onTeleportMoveTo(BaseNode node, int x, int y);



}
