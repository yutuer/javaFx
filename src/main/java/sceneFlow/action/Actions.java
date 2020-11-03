package sceneFlow.action;

import java.util.List;

/**
 * @Description action集合
 * @Author zhangfan
 * @Date 2020/10/15 11:49
 * @Version 1.0
 */
public class Actions
{
    private List<Action> actions;

    public void addAction(Action action)
    {
        actions.add(action);
    }

    public void removeAction(int actionID)
    {
        //Todo
    }

    public void triggerActionEvent(int actionEventID, int triggerObjId, int param1, int param2, int param3)
    {
        for (int i = 0, size = actions.size(); i < size; i++)
        {
            actions.get(i).triggerActionEvent(actionEventID, triggerObjId, param1, param2, param3);
        }
    }

    public void destroy()
    {

    }
}
