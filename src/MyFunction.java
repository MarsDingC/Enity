import Bean.Enity;

import static java.lang.Math.abs;

/**
 * Created by 92377 on 2017/12/13.
 */
public class MyFunction {

    /**
     * 通过分别比较两点X、Y轴坐标判断两点是否碰撞
     *
     * @param e1
     * @param e2
     * @return
     */
    static boolean pong(Enity e1, Enity e2) {
        return e1.getX() == e2.getX() && e1.getY() == e2.getY();
    }

    /**
     * 输出该Enity的坐标
     *
     * @param e1
     */
    static void printEnityXY(Enity e1) {
        System.out.println("(" + e1.getX() + "," + e1.getY() + ")");
    }


    /**
     * 计算两点之间的路径长度
     *
     * @param e1
     * @param e2
     * @return
     */
    static int calLength(Enity e1, Enity e2) {
        return abs(e1.getX() - e2.getX()) + abs(e1.getY() - e2.getY());
    }
}
