package sceneFlow;

import java.util.HashMap;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/10/15 11:53
 * @Version 1.0
 */
public class Scene
{
    private HashMap<Integer, BPObject> sceneObjs = new HashMap<>();

    public BPObject getObjectByObjId(int objectId)
    {
        return sceneObjs.get(objectId);
    }

    public void addSceneTrigger(SceneTrigger sceneTrigger)
    {

    }
}
