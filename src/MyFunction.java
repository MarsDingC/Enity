import Bean.Enity;
import Utile.Utile;

import java.util.List;

import static java.lang.Math.abs;

/**
 * Created by MarsDingC on 2017/12/13.
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

    static int countSuccess(List<Enity> now, List<Enity> aim) {
        List<Enity> tempNow = Utile.copyList(now);
        List<Enity> tempAim = Utile.copyList(aim);
        int num = 0;
        for (int i = 0; i < now.size(); ++i) {
            for(int j=0;j<now.size();++j)
            if (pong(tempNow.get(i), tempAim.get(j))) {
                num++;
            }
        }
        return num;
    }


}
