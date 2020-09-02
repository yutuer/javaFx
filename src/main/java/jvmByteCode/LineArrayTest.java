package jvmByteCode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/8/14 15:00
 * @Version 1.0
 */
public class LineArrayTest
{
    public static void main(String[] args)
    {


        LineArray[] las = new LineArray[]{
                new LineArray(new String[]{"素材123", "素材4"}),
                new LineArray(new String[]{"广告组1-5", "广告组6-8"}),
                new LineArray(new String[]{"国A组", "国B组"}),
                new LineArray(new String[]{"受C组", "受D组"}),
        };

        Queue<LineArray> queue = new ArrayDeque(Arrays.asList(las));

        LineArray lineArray = addAll(queue);

        System.out.println(lineArray);
    }

    public static LineArray addAll(Queue<LineArray> las)
    {
        LineArray rs = las.poll();

        LineArray param = null;
        while ((param = las.poll()) != null)
        {
            rs = rs.add(param);
        }
        return rs;
    }

    public static class LineArray
    {
        String[] contents;

        public LineArray(String[] contents)
        {
            this.contents = contents;
        }

        public LineArray add(LineArray la2)
        {
            String[] strs = new String[contents.length * la2.contents.length];

            int k = 0;

            for (int i = 0; i < contents.length; i++)
            {
                for (int j = 0; j < la2.contents.length; j++)
                {
                    strs[k++] = contents[i] + " " + la2.contents[j];
                }
            }
            return new LineArray(strs);
        }

        @Override
        public String toString()
        {
            return "LineArray{" +
                    "contents=" + Arrays.toString(contents) +
                    '}';
        }
    }

}
