package behaviorTree.ifs.single.action;

import behaviorTree.core.NodeStatusEnum;

/**
 * @Description 说话action
 * @Author zhangfan
 * @Date 2020/11/23 15:01
 * @Version 1.0
 */
public class SpeakAction<T> extends ActionNode<T>
{
    private int total;

    public SpeakAction(String tip)
    {
        super(tip, (tiContext, interval) ->
        {
            System.out.println("speak");
            return NodeStatusEnum.Successed;
        });
    }

    @Override
    protected void onTerminate(NodeStatusEnum status)
    {
        total = 0;
    }

}
