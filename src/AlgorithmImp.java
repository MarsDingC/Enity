/*
 * @author:伊藤诚，加藤鹰
 * @date:2017年11月10日 下午6:08:33
 * @description:
 */

import java.util.*;

import Bean.Enity;
import Transfer.Algorithm;

import static Utile.Utile.copyEnity;
import static java.lang.Math.abs;

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

    public List<Enity> rule(List<Enity> init) {
        List<Enity> ans = new ArrayList<>();
        //为了便于大家遍历我们的目标结果序列。我们这里创建了一份目标序列
        if (first) {
            initAim();
            first = false;
        }
        findRightAim(init);
        List<MyEnity> myInit = new ArrayList<>();
        for (int i = 0; i < init.size(); i++) {//初始化myinit并计算路径长度
            myInit.add(new MyEnity(init.get(i), i));
            myInit.get(i).calLength(aim.get(i));
        }
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
                        setMyEnityInfo(myInit);
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

    private void test(List<MyEnity> myInit) {
        time++;
        boolean is = true;
        for (MyEnity aMyinit1 : myInit) {
            if (aMyinit1.getLength() != 0) {
                is = false;
                break;
            }
            if (!aMyinit1.isMoved()) {
                waitTime++;
            }
        }
        if (is) {
            System.out.println(time);
            System.out.println(waitTime);
            waitTime = 0;
            time = 0;
        }
    }

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

    private void setMyEnityInfo(List<MyEnity> myEnityList) {
        for (MyEnity aMyEnityList : myEnityList) {
            aMyEnityList.calLength(aim.get(aMyEnityList.getAim()));
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

    private void findRightAim(List<Enity> enityList) {
        int[] length = new int[enityList.size()];
        for (int i = 0; i < enityList.size(); i++) {
            length[i] = calLength(enityList.get(i), aim.get(i));
        }
        Enity ie, je, ia, ja;
        for (int i = 0; i < enityList.size(); i++) {
            for (int j = 0; j < enityList.size(); j++) {
                ie = enityList.get(i);
                je = enityList.get(j);
                ia = aim.get(i);
                ja = aim.get(j);
                if (Math.max(calLength(ie, ja), calLength(je, ia)) < Math.max(length[i], length[j])) {
                    swapAim(length, ia, ja, i, j);
                }
            }
        }
    }

    private void swapAim(int[] length, Enity ia, Enity ja, int i, int j) {
        Enity temp = copyEnity(ia);
        aim.set(i, ja);
        aim.set(j, temp);
        int tempInt = length[i];
        length[i] = length[j];
        length[j] = tempInt;
    }

    private int calLength(Enity e1, Enity e2) {
        return abs(e1.getX() - e2.getX()) + abs(e1.getY() - e2.getY());
    }

}
