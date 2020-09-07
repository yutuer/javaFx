package fasterLogger.process;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Description process池管理
 * @Author zhangfan
 * @Date 2020/9/7 17:17
 * @Version 1.0
 */
public class ProcessPool
{
    private Queue<Process> processQueue = new ArrayDeque<>();

    // 使用什么数据结构? 堆,链表,还是数组,序列表,队列
    // 因为需要遍历. 使用队列
    public void addProcess(Process process)
    {

    }

}
