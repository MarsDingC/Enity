import Bean.Enity;

import static java.lang.Math.abs;

/**
 * Created by MarsDingC on 2017/12/8.
 */
public class MyEnity extends Enity implements Comparable<MyEnity> {
    private int depth;
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




    MyEnity(Enity enity) {
        super(enity.getX(), enity.getY(), enity.getId(), enity.getStep(), 32, 30);
        depth = 0;
        length = 0;
        num = 0;
        aim = 0;
        isMoved = true;
    }

    MyEnity(int x, int y, int depth) {
        super(y, x);
        this.depth = depth;
        length = 0;
        num = 0;
        aim = 0;
        isMoved = true;
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


    public int getDepth() {
        return depth;
    }

    
    void setLength(Enity enity) {
        this.length=MyFunction.calLength(this,enity);
    }


    @Override
    public int compareTo(MyEnity o) {
        return o.length - this.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyEnity myEnity = (MyEnity) o;

        return this.getX() == myEnity.getX() && this.getY() == myEnity.getY();
    }

    @Override
    public int hashCode() {
        int result = depth;
        result = 31 * result + length;
        result = 31 * result + num;
        result = 31 * result + aim;
        result = 31 * result + (isMoved ? 1 : 0);
        result = 31 * result + (isCanLeft ? 1 : 0);
        result = 31 * result + (isCanRight ? 1 : 0);
        result = 31 * result + (isCanUp ? 1 : 0);
        result = 31 * result + (isCanDown ? 1 : 0);
        return result;
    }
}
