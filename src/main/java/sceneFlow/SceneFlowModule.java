package sceneFlow;

import java.util.List;

/**
 * @Description
 * @Author zhangfan
 * @Date 2020/10/15 11:32
 * @Version 1.0
 */
public class SceneFlowModule
{
    private Scene scene;

    public SceneFlowModule(Scene scene)
    {
        this.scene = scene;
    }

    private List<SceneTrigger> waitDestroyTriggers;

    private List<SceneTrigger> waitAddTriggers;

    private List<SceneTrigger> sceneTriggers;

    public void tick(int interval)
    {
        tickDestroyTrigger();

        tickWaitAddTrigger();

        tickTriggers(interval);
    }

    private void tickTriggers(int interval)
    {
        for (int i = 0, size = sceneTriggers.size(); i < size; i++)
        {
            SceneTrigger sceneTrigger = sceneTriggers.get(i);

            if (sceneTrigger.isFinish())
            {
                destroySceneTrigger(sceneTrigger);
                return;
            }

            sceneTrigger.tick(interval);

            if (sceneTrigger.isFinish())
            {
                destroySceneTrigger(sceneTrigger);
                return;
            }
        }
    }

    private void tickDestroyTrigger()
    {
        for (int i = 0, size = waitDestroyTriggers.size(); i < size; i++)
        {
            SceneTrigger sceneTrigger = waitDestroyTriggers.get(i);
            sceneTrigger.destroy();
        }
    }

    private void tickWaitAddTrigger()
    {
        for (int i = 0, size = waitAddTriggers.size(); i < size; i++)
        {
            SceneTrigger sceneTrigger = waitAddTriggers.get(i);
            sceneTrigger.init();

            sceneTriggers.add(sceneTrigger);
        }
    }

    public void addSceneTrigger(SceneTrigger sceneTrigger)
    {
        waitAddTriggers.add(sceneTrigger);
    }

    public void destroySceneTrigger(SceneTrigger sceneTrigger)
    {
        waitDestroyTriggers.add(sceneTrigger);
    }

    public Scene getScene()
    {
        return scene;
    }
}
