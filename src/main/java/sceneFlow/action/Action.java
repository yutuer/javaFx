package sceneFlow.action;

import sceneFlow.IData;

/**
 * @Description 无参数的Action
 * @Author zhangfan
 * @Date 2020/10/15 11:32
 * @Version 1.0
 */
public abstract class Action
{

    protected IData actionData;

    protected int actionID;

    public int getActionID()
    {
        return actionID;
    }

    public void init()
    {

    }

    public void destroy()
    {

    }

    public abstract void triggerActionEvent(int actionEventID, int triggerObjId, int param1, int param2, int param3);
}
