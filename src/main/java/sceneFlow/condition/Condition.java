package sceneFlow.condition;

import sceneFlow.dict.DictCondition;

/**
 * @Description 条件
 * @Author zhangfan
 * @Date 2020/10/15 11:35
 * @Version 1.0
 */
public abstract class Condition
{

    protected DictCondition dictCondition;

    public Condition(DictCondition dictCondition)
    {
        this.dictCondition = dictCondition;
    }

    public void init()
    {

    }

    public void destroy()
    {

    }

    /**
     * 条件是否完成
     *
     * @return
     */
    public abstract boolean isOk();

    /**
     * 触发监听, 修改condtion 类内部变量值
     *
     * @param conditionEventId
     * @param param1
     * @param param2
     * @param param3
     */
    public abstract void triggerCondtionEvent(int conditionEventId, int param1, int param2, int param3);
}
