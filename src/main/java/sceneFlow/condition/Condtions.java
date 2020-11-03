package sceneFlow.condition;

import java.util.List;

/**
 * @Description 条件集合
 * @Author zhangfan
 * @Date 2020/10/15 11:45
 * @Version 1.0
 */
public class Condtions
{
    /**
     * 所有条件是否是与关系
     */
    private boolean isAnd;

    /**
     * 条件集合
     */
    private List<Condition> conditions;

    public Condtions(boolean isAnd)
    {
        this.isAnd = isAnd;
    }

    public boolean isAllOk()
    {
        if (isAnd)
        {
            for (int i = 0; i < conditions.size(); i++)
            {
                if (!conditions.get(i).isOk())
                {
                    return false;
                }
            }
            return true;
        }
        else
        {
            for (int i = 0; i < conditions.size(); i++)
            {
                if (conditions.get(i).isOk())
                {
                    return true;
                }
            }
            return false;
        }
    }

    public void init(Condition[] conditions)
    {
        for (int i = 0, len = conditions.length; i < len; i++)
        {
            addCondition(conditions[i]);
        }
    }

    /**
     * 所有的condition 注册监听
     */
    public void addCondition(Condition condition)
    {
        condition.init();

        conditions.add(condition);
    }

    public void destroy()
    {
        for (int i = conditions.size() - 1; i >= 0; i--)
        {
            removeCondtion(conditions.get(i));
        }
    }

    public void removeCondtion(Condition condition)
    {
        if (conditions.remove(condition))
        {
            condition.destroy();
        }
    }
}
