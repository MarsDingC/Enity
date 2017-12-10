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

    void setCanLeft() {
        isCanLeft = false;
    }

    boolean isCanRight() {
        return isCanRight;
    }

    void setCanRight() {
        isCanRight = false;
    }

    boolean isCanUp() {
        return isCanUp;
    }

    void setCanUp() {
        isCanUp = false;
    }

    boolean isCanDown() {
        return isCanDown;
    }

    void setCanDown() {
        isCanDown = false;
    }

    public int getAim() {
        return aim;
    }

    public void setAim(int aim) {
        this.aim = aim;
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

    void culLength(Enity enity) {
        this.length = abs(this.getX() - enity.getX()) + abs(this.getY() - enity.getY());
    }


    int findLength(Enity enity) {
        int length1 = abs(this.getX() - enity.getX()) + abs(this.getY() - enity.getY());
        return length1;
    }

    @Override
    public int compareTo(MyEnity o) {
        return o.length - this.length;
    }
}
