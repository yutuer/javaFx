package crossLink.listener;

/**
 * @Description 节点监听器
 * @Author zhangfan
 * @Date 2020/9/15 18:09
 * @Version 1.0
 */
public interface IBaseNodeListener
{

    /**
     * 移动到新的点
     *
     * @param x
     * @param y
     * @param newX
     * @param newY
     */
    void onMoveTo(int x, int y, int newX, int newY);
}
