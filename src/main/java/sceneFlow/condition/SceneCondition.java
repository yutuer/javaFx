package sceneFlow.condition;

import sceneFlow.Scene;
import sceneFlow.SceneFlowBase;
import sceneFlow.dict.DictCondition;

/**
 * @Description 场景condition
 * @Author zhangfan
 * @Date 2020/10/15 17:54
 * @Version 1.0
 */
public abstract class SceneCondition extends Condition implements SceneFlowBase
{

    protected Scene scene;

    public SceneCondition(Scene scene, DictCondition dictCondition)
    {
        super(dictCondition);
        this.scene = scene;
    }

    @Override
    public Scene getScene()
    {
        return scene;
    }
}
