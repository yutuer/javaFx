package sceneFlow.condition;

import sceneFlow.Scene;
import sceneFlow.dict.DictCondition;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/10/16 10:38
 * @Version 1.0
 */
public class SceneConditionFactory
{
    public static SceneCondition createSceneCondition(Scene scene, DictCondition dictCondition)
    {
        switch (dictCondition.getCondType())
        {
            case 1:
                return new SceneCondition1(scene, dictCondition);
            default:
                break;
        }

        return null;
    }
}
