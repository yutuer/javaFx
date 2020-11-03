package sceneFlow.action;

import sceneFlow.Scene;
import sceneFlow.SceneFlowBase;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/10/15 17:58
 * @Version 1.0
 */
public abstract class SceneAction extends Action implements SceneFlowBase
{
    protected Scene scene;

    @Override
    public Scene getScene()
    {
        return scene;
    }
}
