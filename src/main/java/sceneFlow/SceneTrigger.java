package sceneFlow;

import sceneFlow.action.Action;
import sceneFlow.action.Actions;
import sceneFlow.condition.Condition;
import sceneFlow.condition.Condtions;
import sceneFlow.condition.SceneConditionFactory;
import sceneFlow.dict.Dict;
import sceneFlow.dict.DictCondition;

/**
 * @Description 触发器
 * @Author zhangfan
 * @Date 2020/10/15 11:35
 * @Version 1.0
 */
public class SceneTrigger implements SceneFlowBase
{
    private IData data;

    private int triggerCount;
    private int triggerCountMax;

    private Condtions condtions;
    private Actions actions;

    protected Scene scene;

    @Override
    public Scene getScene()
    {
        return scene;
    }

    public SceneTrigger(IData data)
    {
        this.data = data;
        triggerCount = data.getIntParam(0);
        triggerCountMax = data.getIntParam(1);


    }

    public void init()
    {
        condtions = initCondtions();
        actions = initActions();
    }

    private Actions initActions()
    {
        int[] acts = data.getIntArrayParam(2);
        Actions actions = new Actions();

        int len = acts.length;

        for (int i = 0; i < len; i++)
        {

        }
        return actions;
    }

    private Condtions initCondtions()
    {
        boolean isAnd = data.getBoolParam(0);
        Condtions condtions = new Condtions(isAnd);
        int[] conds = data.getIntArrayParam(1);

        int len = conds.length;
        Condition[] conditions = new Condition[len];
        for (int i = 0; i < len; i++)
        {
            int condId = conds[i];
            DictCondition dictCondition = Dict.getDictCondition(condId);
            conditions[i] = SceneConditionFactory.createSceneCondition(scene, dictCondition);
        }
        condtions.init(conditions);
        return condtions;
    }

    public void destroy()
    {
        condtions.destroy();

        actions.destroy();
    }

    public void tick(int interval)
    {
        if (isAllOk())
        {

        }
    }


    public void addAction(Action action)
    {
        actions.addAction(action);
    }

    private boolean isAllOk()
    {
        return condtions.isAllOk();
    }

    public void triggerCount()
    {
        triggerCount++;
    }

    public boolean isFinish()
    {
        if (triggerCount < 0)
        {
            return false;
        }
        return triggerCount >= triggerCountMax;
    }
}

