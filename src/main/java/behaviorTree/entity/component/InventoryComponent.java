package behaviorTree.entity.component;

import behaviorTree.entity.BehaviourEntity;
import behaviorTree.entity.ItemType;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 背包组件
 * @Author zhangfan
 * @Date 2020/11/23 20:39
 * @Version 1.0
 */
public class InventoryComponent extends BaseComponent
{

    private Map<ItemType, Integer> items = new HashMap<>();

    public InventoryComponent(BehaviourEntity entity)
    {
        super(entity);
    }

    /**
     * 是否有某种物品
     *
     * @param itemType
     * @return
     */
    public boolean hasItem(ItemType itemType)
    {
        return itemNum(itemType) > 0 ? true : false;
    }

    /**
     * 物品数量
     *
     * @param itemType
     * @return
     */
    public int itemNum(ItemType itemType)
    {
        Integer value = items.get(itemType);
        return value == null ? 0 : value;
    }

    /**
     * 删除某物品x个
     *
     * @param itemType
     * @param num
     * @return
     */
    public boolean deleteItemNum(ItemType itemType, int num)
    {
        int leftNum = itemNum(itemType);
        if (leftNum < num)
        {
            return false;
        }

        items.put(itemType, leftNum - num);
        return true;
    }
}
