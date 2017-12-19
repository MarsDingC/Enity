/*
 * @author:伊藤诚，加藤鹰
 * @date:2017年11月10日 下午6:08:33
 * @description:
 */

import Bean.Enity;
import Transfer.Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Utile.Utile.copyEnity;

public class AlgorithmImp implements Algorithm {
    //供你们参考的党徽里每个点具体的位置
    private List<Enity> aim = new ArrayList<>();
    private boolean first = true;//第一次加载时创建目标列表
    // 算法规则
    /*
     * 功能：计算每一个人物到达的下一个的位置，建议玩家使用更优算法修改，特别是带碰撞检测
	 * 目的：玩家自己编写，控制人物移动 
	 * 参数： init:按照人物编号人物当前位置 
	 *       Enity代表一个人
	 *            内置函数 getX(),getY()获取当前位置，
	 *                    movingRight(),movingLeft(),movingUp(),movingDown()以规定的步伐上下左右移动
	 *                    每个点每次只能移动一次，步长可以通过getStep()来查看。如果多次移动，我们会检查并给你报错的
	 *                    
	 */
    private int time = 0;
    private static int waitTime = 0;
    private static int allTime = 0;
    private static int playTime = 0;


    public List<Enity> rule(List<Enity> init) {
        List<Enity> ans = new ArrayList<>();
        //为了便于大家遍历我们的目标结果序列。我们这里创建了一份目标序列
        if (first) {
            initAim();
            first = false;
        }

        List<MyEnity> myInit = new ArrayList<>();
        for (int i = 0; i < init.size(); i++) {//初始化myinit并计算路径长度
            myInit.add(new MyEnity(init.get(i), i));
            myInit.get(i).setLength(aim.get(i));
        }
        findRightAim(myInit);
        Collections.sort(myInit);//按照路径长度排序
        setMyEnityInfo(myInit);//设置每个点的可移动信息
        boolean canMove = true;
        while (canMove) {
            canMove = false;
            for (int i = 0; i < myInit.size(); i++) {
                MyEnity e1 = myInit.get(i);
                if (!e1.isMoved() && e1.getLength() != 0) {
                    Enity e2 = aim.get(e1.getAim());//找到与e1对应的目标点
                    canMove = tryMove(e1, e2);
                    if (canMove) {
                        e1.setMoved(true);
                        setMyEnityInfo(myInit);//设置每个点的可移动信息
                    }
                }
            }
        }

        test(myInit);

        for (int i = 0; i < myInit.size(); i++) {
            for (MyEnity enity : myInit) {
                if (enity.getNum() == i) ans.add(enity);
            }
        }
        return ans;
    }

    /**
     * 测试移动次数和等待次数，在逻辑结束时输出
     *
     * @param myInit
     */
    private void test(List<MyEnity> myInit) {
        time++;
        boolean isOver = true;
        for (MyEnity aMyinit1 : myInit) {
            if (aMyinit1.getLength() != 0) {
                isOver = false;
                break;
            }
            if (!aMyinit1.isMoved()) {
                waitTime++;
            }
        }
        if (isOver) {
            System.out.println("本次移动次数：" + time);
            System.out.println("本次等待次数：" + waitTime);
            allTime += time;
            playTime++;
            System.out.println("平均移动次数：" + allTime / (double) playTime);
            System.out.println("总测试次数：" + playTime);
            waitTime = 0;
            time = 0;
            first = true;
        }
    }

    /**
     * 对每个点尝试移动
     *
     * @param e1 将要移动的点
     * @param e2 目标点
     * @return
     */
    private boolean tryMove(MyEnity e1, Enity e2) {
        if (e1.getX() < e2.getX()) {
            if (e1.isCanRight()) {
                e1.movingRight();
                return true;
            }
        } else if (e1.getX() > e2.getX()) {
            if (e1.isCanLeft()) {
                e1.movingLeft();
                return true;
            }
        }
        if (e1.getY() < e2.getY()) {
            if (e1.isCanDown()) {
                e1.movingDown();
                return true;
            }
        } else if (e1.getY() > e2.getY()) {
            if (e1.isCanUp()) {
                e1.movingUp();
                return true;
            }
        }
        return false;
    }

    //友情提供，各个点的位置
    private void initAim() {
        aim.add(new Enity(14, 4));
        aim.add(new Enity(15, 4));
        aim.add(new Enity(16, 4));
        aim.add(new Enity(17, 4));
        aim.add(new Enity(18, 4));

        aim.add(new Enity(14, 5));
        aim.add(new Enity(17, 5));
        aim.add(new Enity(18, 5));
        aim.add(new Enity(19, 5));
        aim.add(new Enity(20, 5));
        aim.add(new Enity(21, 5));

        aim.add(new Enity(19, 6));
        aim.add(new Enity(22, 6));
        aim.add(new Enity(23, 6));

        aim.add(new Enity(20, 7));
        aim.add(new Enity(24, 7));

        aim.add(new Enity(21, 8));
        aim.add(new Enity(25, 8));

        aim.add(new Enity(10, 9));
        aim.add(new Enity(11, 9));
        aim.add(new Enity(12, 9));
        aim.add(new Enity(13, 9));
        aim.add(new Enity(14, 9));
        aim.add(new Enity(21, 9));
        aim.add(new Enity(25, 9));

        aim.add(new Enity(9, 10));
        aim.add(new Enity(14, 10));
        aim.add(new Enity(22, 10));
        aim.add(new Enity(26, 10));

        aim.add(new Enity(8, 11));
        aim.add(new Enity(13, 11));
        aim.add(new Enity(23, 11));
        aim.add(new Enity(26, 11));

        aim.add(new Enity(7, 12));
        aim.add(new Enity(14, 12));
        aim.add(new Enity(23, 12));
        aim.add(new Enity(27, 12));

        aim.add(new Enity(6, 13));
        aim.add(new Enity(15, 13));
        aim.add(new Enity(24, 13));
        aim.add(new Enity(27, 13));

        aim.add(new Enity(7, 14));
        aim.add(new Enity(10, 14));
        aim.add(new Enity(16, 14));
        aim.add(new Enity(24, 14));
        aim.add(new Enity(27, 14));

        aim.add(new Enity(8, 15));
        aim.add(new Enity(9, 15));
        aim.add(new Enity(11, 15));
        aim.add(new Enity(17, 15));
        aim.add(new Enity(23, 15));
        aim.add(new Enity(26, 15));

        aim.add(new Enity(12, 16));
        aim.add(new Enity(18, 16));
        aim.add(new Enity(22, 16));
        aim.add(new Enity(25, 16));

        aim.add(new Enity(13, 17));
        aim.add(new Enity(19, 17));
        aim.add(new Enity(21, 17));
        aim.add(new Enity(24, 17));

        aim.add(new Enity(14, 18));
        aim.add(new Enity(20, 18));
        aim.add(new Enity(23, 18));

        aim.add(new Enity(15, 19));
        aim.add(new Enity(22, 19));

        aim.add(new Enity(8, 20));
        aim.add(new Enity(16, 20));
        aim.add(new Enity(22, 20));

        aim.add(new Enity(7, 21));
        aim.add(new Enity(9, 21));
        aim.add(new Enity(17, 21));
        aim.add(new Enity(23, 21));

        aim.add(new Enity(8, 22));
        aim.add(new Enity(10, 22));
        aim.add(new Enity(11, 22));
        aim.add(new Enity(16, 22));
        aim.add(new Enity(24, 22));

        aim.add(new Enity(8, 23));
        aim.add(new Enity(9, 23));
        aim.add(new Enity(12, 23));
        aim.add(new Enity(15, 23));
        aim.add(new Enity(18, 23));
        aim.add(new Enity(19, 23));
        aim.add(new Enity(25, 23));

        aim.add(new Enity(7, 24));
        aim.add(new Enity(9, 24));
        aim.add(new Enity(10, 24));
        aim.add(new Enity(11, 24));
        aim.add(new Enity(13, 24));
        aim.add(new Enity(14, 24));
        aim.add(new Enity(17, 24));
        aim.add(new Enity(20, 24));
        aim.add(new Enity(24, 24));

        aim.add(new Enity(6, 25));
        aim.add(new Enity(9, 25));
        aim.add(new Enity(12, 25));
        aim.add(new Enity(15, 25));
        aim.add(new Enity(16, 25));
        aim.add(new Enity(21, 25));
        aim.add(new Enity(23, 25));

        aim.add(new Enity(7, 26));
        aim.add(new Enity(8, 26));
        aim.add(new Enity(13, 26));
        aim.add(new Enity(14, 26));
        aim.add(new Enity(22, 26));
    }

    /**
     * 设置每个点的可移动信息
     *
     * @param myEnityList
     */
    private void setMyEnityInfo(List<MyEnity> myEnityList) {
        for (MyEnity aMyEnityList : myEnityList) {
            aMyEnityList.setLength(aim.get(aMyEnityList.getAim()));
        }
        for (int i = 0; i < myEnityList.size(); i++) {
            MyEnity myEnity = myEnityList.get(i);
            myEnity.resetCanMove();
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};
            for (MyEnity myEnity1 : myEnityList) {
                for (int j = 0; j < 4; ++j) {
                    if (myEnity1.getX() == myEnity.getX() + dx[j]
                            && myEnity1.getY() == myEnity.getY() + dy[j]) {
                        myEnity.setCanMove(dx[j], dy[j]);
                    }
                }
            }
        }
    }

    private MyEnity getXWant(MyEnity myEnity) {
        MyEnity wantEnity = new MyEnity(myEnity);
        if (myEnity.getX() < aim.get(myEnity.getAim()).getX())
            wantEnity.movingRight();
        else if (myEnity.getX() > aim.get(myEnity.getAim()).getX())
            wantEnity.movingLeft();
        else if (myEnity.getY() < aim.get(myEnity.getAim()).getY())
            wantEnity.movingDown();
        else if (myEnity.getY() > aim.get(myEnity.getAim()).getY())
            wantEnity.movingUp();
        return wantEnity;
    }

    private MyEnity getYWant(MyEnity myEnity) {
        MyEnity wantEnity = new MyEnity(myEnity);
        if (myEnity.getY() < aim.get(myEnity.getAim()).getY())
            wantEnity.movingDown();
        else if (myEnity.getY() > aim.get(myEnity.getAim()).getY())
            wantEnity.movingUp();
        else if (myEnity.getX() < aim.get(myEnity.getAim()).getX())
            wantEnity.movingRight();
        else if (myEnity.getX() > aim.get(myEnity.getAim()).getX())
            wantEnity.movingLeft();
        return wantEnity;
    }


    /**
     * 进行路径规划，使局部每两点的路径长度中的最大值最小，达成全局最大路径最小
     *
     * @param enityList
     */
    private void findRightAim(List<MyEnity> enityList) {
        int[] length = new int[enityList.size()];
        for (int i = 0; i < enityList.size(); i++) {
            length[i] = MyFunction.calLength(enityList.get(i), aim.get(i));
        }
        MyEnity enityi, enityj;
        Enity aimi, aimj;
        for (int i = 0; i < enityList.size(); i++) {
            for (int j = 0; j < enityList.size(); j++) {
                enityi = enityList.get(i);
                enityj = enityList.get(j);
                aimi = aim.get(enityi.getAim());
                aimj = aim.get(enityj.getAim());
                int pong = 0;
                if (MyFunction.pong(getXWant(enityi), getXWant(enityj))) {
                    pong = 1;
                } else if (MyFunction.pong(getYWant(enityi), getYWant(enityj))) {
                    pong = 1;
                }
                if (Math.max(MyFunction.calLength(enityi, aimj), MyFunction.calLength(enityj, aimi)) < Math.max(length[i], length[j]) + pong) {
                    swapAim(length, aimi, aimj, i, j);
                    length[i] = MyFunction.calLength(enityi, aimj);
                    length[j] = MyFunction.calLength(enityj, aimi);
                }
            }
        }
    }

    private void swapAim(int[] length, Enity ia, Enity ja, int i, int j) {
        Enity temp = copyEnity(ia);
        aim.set(i, ja);
        aim.set(j, temp);
    }



}
