import Bean.Enity;

import static java.lang.Math.abs;

/**
 * Created by 92377 on 2017/12/13.
 */
public class MyFunction {
    static boolean pong(Enity e1, Enity e2) {
        return e1.getX() == e2.getX() && e1.getY() == e2.getY();
    }

    static void printEnityXY(Enity e1){
        System.out.println("("+e1.getX()+","+e1.getY()+")");
    }

    static int calLength(Enity e1, Enity e2) {
        return abs(e1.getX() - e2.getX()) + abs(e1.getY() - e2.getY());
    }
}
