package helloworld.navmesh;

import util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/7 18:48
 * @Version 1.0
 */
public class NavMeshBuilder
{
    public String parseFileInString(String file)
    {
        String content = null;

        try
        {
            InputStream is = getClass().getClassLoader().getResourceAsStream(file);

            InputStreamReader isr = new InputStreamReader(is);

            int length = is.available();

            char[] data = new char[length];

            isr.read(data, 0, length);

            content = String.valueOf(data);

            isr.close();
            is.close();

        }
        catch (Exception ex)
        {
            Log.Nav_Logger.error("【地图格子文件加载错误】 加载错误 [文件名] {} ", file, ex);
            return null;
        }

        return content;
    }

    public NavNodeRasterizer build(String nav_info)
    {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(nav_info.getBytes("utf-8"));
             InputStreamReader isr = new InputStreamReader(bais, "utf-8");
             BufferedReader reader = new BufferedReader(isr))
        {

            // 1.加载name
            String name = reader.readLine();

            reader.readLine();

            // 2.加载MD5(本身MD5和主场景MD5)
            String md5 = reader.readLine();
            String mainSceneMd5 = reader.readLine();

            // 3.加载bounds
            String line = reader.readLine();
            if (!line.startsWith(NavConst.BOUNDS_PRIFIX))
            {
                Log.Nav_Logger.error(" 【导航网格加载错误】 Bounds is not start with bound, line={}", line);
                return null;
            }

            String substring = line.substring(NavConst.BOUNDS_PRIFIX.length());
            String[] split = substring.split(NavConst.SEMI);
            String[] minStr = split[0].split(NavConst.COMMA);
            String[] maxStr = split[1].split(NavConst.COMMA);

            VectorInt2 min = new VectorInt2(Integer.parseInt(minStr[0]), Integer.parseInt(minStr[1]));
            VectorInt2 max = new VectorInt2(Integer.parseInt(maxStr[0]), Integer.parseInt(maxStr[1]));

            // 4.加载ndoes
            line = reader.readLine();
            if (!line.startsWith(NavConst.NODE_PRIFIX))
            {
                Log.Nav_Logger.error(" 【导航网格加载错误】 Nodes is not start with nodes, line={}", line);
                return null;
            }
            substring = line.substring(NavConst.NODE_PRIFIX.length());

            int count = Integer.parseInt(substring);

            NavNode[] allNodes = new NavNode[count];

            for (int i = 0; i < count; i++)
            {

                //  node deserial
                line = reader.readLine();
                NavNode node = deserialNavNode(line);

                if (node == null)
                {
                    Log.Nav_Logger.error(" 【导航网格加载错误】 node deserial error, line={}", line);
                    return null;
                }

                allNodes[i] = node;
            }

            NavNodeRasterizer navNodeRasterizer = new NavNodeRasterizer();
            navNodeRasterizer.setAllNodes(allNodes);

            return navNodeRasterizer;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析NavNode
     *
     * @param readLine
     * @return
     */
    private NavNode deserialNavNode(String readLine)
    {
        String[] split = readLine.split(NavConst.SEMI);

        int index = Integer.parseInt(split[0]);
        byte area = Byte.parseByte(split[1]);
        VectorInt3 position = VectorInt3.parse(split[2]);
        VectorInt3 vertex0 = VectorInt3.parse(split[3]);
        VectorInt3 vertex1 = VectorInt3.parse(split[4]);
        VectorInt3 vertex2 = VectorInt3.parse(split[5]);

        NavNode node = new NavNode(index, position, area, vertex0, vertex1, vertex2);

        return node;
    }

}
