package leetcode;

/**
 * @Description 147
 * @Author zhangfan
 * @Date 2020/11/20 16:58
 * @Version 1.0
 */
public class Solution147
{
    public ListNode insertionSortList(ListNode head)
    {
        if (head == null || head.next == null)
        {
            return head;
        }

        ListNode dummy = new ListNode(Integer.MIN_VALUE);

        ListNode cur = head;
        while (cur != null)
        {
            ListNode next = cur.next;
            cur.next = null;

            insertToDummy(dummy, cur);

            cur = next;
        }
        return dummy.next;
    }

    private void insertToDummy(ListNode dummy, ListNode node)
    {
        if (dummy.next == null)
        {
            dummy.next = node;
        }
        else
        {
            ListNode cur = dummy;
            ListNode next = cur.next;
            while (cur != null && next != null)
            {
                if (node.val < next.val)
                {
                    // 插入cur和next中间
                    node.next = next;
                    cur.next = node;
                    return;
                }

                cur = next;
                next = cur.next;
            }

            cur.next = node;
        }
    }

    private static class ListNode
    {
        int val;
        ListNode next;

        ListNode(int x)
        {
            val = x;
        }
    }

    public static void main(String[] args)
    {
    }
}


