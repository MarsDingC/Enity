import Bean.Enity;

import static java.lang.Math.abs;

/**
 * Created by 92377 on 2017/12/8.
 */
public class MyEnity extends Enity implements Comparable<MyEnity> {
    private int length;
    private int num;
    private int aim;
    private boolean isMoved;
    private boolean isCanLeft;
    private boolean isCanRight;
    private boolean isCanUp;
    private boolean isCanDown;


    int getNum() {
        return num;
    }

    boolean isMoved() {
        return isMoved;
    }

    void setMoved(boolean isMoved) {
        this.isMoved = isMoved;
    }

    boolean isCanLeft() {
        return isCanLeft;
    }



    boolean isCanRight() {
        return isCanRight;
    }



    boolean isCanUp() {
        return isCanUp;
    }


    boolean isCanDown() {
        return isCanDown;
    }

    void resetCanMove() {
        isCanUp = true;
        isCanRight = true;
        isCanLeft = true;
        isCanDown = true;
    }

    void setCanMove(int dx, int dy) {
        if (dx < 0) isCanLeft = false;
        else if (dx > 0) isCanRight = false;
        else if (dy < 0) isCanUp = false;
        else if (dy > 0) isCanDown = false;
    }

    int getAim() {
        return aim;
    }

    MyEnity(Enity enity, int n) {
        super(enity.getX(), enity.getY(), enity.getId(), enity.getStep(), 32, 30);
        length = 0;
        num = n;
        aim = n;

        isMoved = false;
        isCanDown = true;
        isCanLeft = true;
        isCanRight = true;
        isCanUp = true;

    }

    int getLength() {
        return length;
    }

    void calLength(Enity enity) {
        this.length = abs(this.getX() - enity.getX()) + abs(this.getY() - enity.getY());
    }


    @Override
    public int compareTo(MyEnity o) {
        return o.length - this.length;
    }
}
