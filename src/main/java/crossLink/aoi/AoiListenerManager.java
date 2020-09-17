package crossLink.aoi;

import crossLink.IAoi;
import crossLink.listener.AoiListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description aoi监听器管理
 * @Author zhangfan
 * @Date 2020/9/17 12:06
 * @Version 1.0
 */
public class AoiListenerManager<T extends BaseNode>
{
    protected List<AoiListener<T>> aoiListeners = new ArrayList<>();

    /**
     * 添加监听器到最后
     *
     * @param listener
     */
    public void addListenerToLast(AoiListener<T> listener)
    {
        if (aoiListeners.contains(listener))
        {
            removeListener(listener);
        }
        aoiListeners.add(listener);
    }

    /**
     * 添加监听器到最前面
     *
     * @param listener
     */
    public void addListenerToFirst(AoiListener<T> listener)
    {
        if (aoiListeners.contains(listener))
        {
            removeListener(listener);
        }
        aoiListeners.add(0, listener);
    }

    /**
     * 添加监听器
     *
     * @param listener
     */
    public void removeListener(AoiListener<T> listener)
    {
        for (int i = 0, size = aoiListeners.size(); i < size; i++)
        {
            AoiListener<T> aoiListener = aoiListeners.get(i);
            if (aoiListener == listener)
            {
                aoiListeners.remove(i);
                break;
            }
        }
    }

    protected void onTriggerAddListener(IAoi aoi, BaseNode node)
    {
        for (int i = 0, size = aoiListeners.size(); i < size; i++)
        {
            AoiListener aoiListener = aoiListeners.get(i);
            aoiListener.onAddNode(aoi, node);
        }
    }

    protected void onTriggerRemoveListener(IAoi aoi, BaseNode node)
    {
        for (int i = 0, size = aoiListeners.size(); i < size; i++)
        {
            AoiListener aoiListener = aoiListeners.get(i);
            aoiListener.onRemoveNode(aoi, node);
        }
    }

    protected void onTriggerBeforeMoveToListener(IAoi aoi, BaseNode node, int x, int y)
    {
        for (int i = 0, size = aoiListeners.size(); i < size; i++)
        {
            AoiListener aoiListener = aoiListeners.get(i);
            aoiListener.beforeMoveTo(aoi, node, x, y);
        }
    }

    protected void onTriggerAfterMoveToListener(IAoi aoi, BaseNode node, int x, int y)
    {
        for (int i = 0, size = aoiListeners.size(); i < size; i++)
        {
            AoiListener aoiListener = aoiListeners.get(i);
            aoiListener.afterMoveTo(aoi, node, x, y);
        }
    }
}
