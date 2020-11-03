package sceneFlow.condition;

import sceneFlow.Scene;
import sceneFlow.dict.DictCondition;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/10/16 10:23
 * @Version 1.0
 */
public class SceneCondition1 extends SceneCondition
{
    public SceneCondition1(Scene scene, DictCondition dictCondition)
    {
        super(scene, dictCondition);
    }

    @Override
    public boolean isOk()
    {
        return false;
    }

    @Override
    public void triggerCondtionEvent(int conditionEventId, int param1, int param2, int param3)
    {

    }
}
