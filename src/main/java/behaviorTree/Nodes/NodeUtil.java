package behaviorTree.Nodes;

import behaviorTree.Nodes.behavior.FuryAttack;
import behaviorTree.Nodes.behavior.NormalAttack;
import behaviorTree.Nodes.condition.IsInFury;
import behaviorTree.Nodes.condition.RangeFindPlayer;
import behaviorTree.Nodes.condition.TargetIsPlayer;
import behaviorTree.Nodes.decorate.InvertNode;
import behaviorTree.impl.ActionNode;
import behaviorTree.impl.ConditionNode;
import behaviorTree.impl.DecoratorNode;
import behaviorTree.impl.SelectorNode;
import behaviorTree.impl.SequenceNode;

public class NodeUtil
{

    /**
     * 攻击玩家
     */
    public static final SelectorNode AttackPlayer = new SelectorNode()
    {
    };

    /**
     * 常规攻击
     */
    public static final SequenceNode NormalAttackPlayer = new SequenceNode()
    {
    };

    /**
     * 视野内发现玩家
     */
    public static final ConditionNode RangeFindPlayer = new RangeFindPlayer();

    /**
     * 目标是玩家
     */
    public static final ConditionNode TargetIsPlayer = new TargetIsPlayer();

    /**
     * 普通攻击
     */
    public static final ActionNode NormalAttack = new NormalAttack();

    /**
     * 报复攻击
     */
    public static final SequenceNode BAOFUAttack = new SequenceNode()
    {
    };

    /**
     * 发狂状态
     */
    public static final ConditionNode IsInFury = new IsInFury();

    /**
     * 疯狂攻击
     */
    public static final ActionNode FuryAttack = new FuryAttack();

    /**
     * 反转结果的修饰节点
     */
    public static final DecoratorNode InvertNode = new InvertNode();

    static
    {
//		AttackPlayer.setTip("攻击玩家行为树判断");

        NormalAttackPlayer.setTip("普通攻击判断!!!");
        BAOFUAttack.setTip("报复攻击判断!!!");

        {
            // 普通攻击
            AttackPlayer.addNode(NormalAttackPlayer);

            NormalAttackPlayer.addNode(RangeFindPlayer);
            NormalAttackPlayer.addNode(TargetIsPlayer);

            InvertNode.setDecoratorNode(IsInFury);
            NormalAttackPlayer.addNode(InvertNode);
            NormalAttackPlayer.addNode(NormalAttack);
        }

        {
            // 狂暴攻击
            AttackPlayer.addNode(BAOFUAttack);

            BAOFUAttack.addNode(RangeFindPlayer);
            BAOFUAttack.addNode(TargetIsPlayer);
            BAOFUAttack.addNode(IsInFury);
            BAOFUAttack.addNode(FuryAttack);
        }

    }
}
