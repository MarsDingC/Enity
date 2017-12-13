
/*
 * @author:加藤鹰
 * @date:2017年11月13日 上午9:50:59
 * @description:
 */

import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import Bean.Enity;
import Common.Common;
import Show.Face;
import Show.MyPanel;
import Transfer.InterFace;

public class InterFaceImp implements InterFace {
    private static ImageIcon[] walks = new ImageIcon[2];
    private static ImageIcon stand;
    private static int step = 0;
    private static boolean first = true;

    static {
        stand = new ImageIcon("images/player_stand.png");
        for (int i = 0; i < walks.length; ++i) {
            walks[i] = new ImageIcon("images/player_" + i + ".png");
        }
    }

    // 这个函数是负责画画的。画每个点当前的位置，目标的位置，通过jlabel来定位
    public JLabel[][] draw(List<Enity> now, List<Enity> aim, JLabel[][] jlabel) {
        // 这里你们可以把人物以图标的形式展现出来，也可以改善下棋盘框框的样子

        // 以下是画画的流程
        // 1.棋盘清空
        System.out.println("Width:" + jlabel[0][0].getWidth());
        System.out.println("Height:" + jlabel[0][0].getHeight());
        for (int i = 0; i < jlabel.length; i++) {
            for (int j = 0; j < jlabel[i].length; j++) {
                jlabel[i][j].setIcon(null);
                jlabel[i][j].setText("");
                jlabel[i][j].setBackground(Color.red);
            }
        }
        // 2.画目标位置(党徽)
        for (int i = 0; i < aim.size(); i++) {
            jlabel[aim.get(i).getX()][aim.get(i).getY()].setBackground(Color.GRAY);
        }
        if (now != null) {
            // 3. 画上现在的每个点的位置，这里可以增加图标

            ImageIcon icon = walks[step];
//            icon = getResizedImageIcon(icon, jlabel[0][0]);
            for (int i = 0; i < now.size(); i++) {
                int nx = now.get(i).getX();
                int ny = now.get(i).getY();
                if (nx == aim.get(i).getX() && ny == aim.get(i).getY()) {
                    System.out.println("nx=" + nx + " ny=" + ny
                            + " ax=" + aim.get(i).getX() + " ay=" + aim.get(i).getY());
                    jlabel[nx][ny].setIcon(stand);
                } else
                    jlabel[nx][ny].setIcon(icon);
//                jlabel[nx][ny].setText(
//                        jlabel[nx][ny].getText() + " " + now.get(i).getId() + "");
                jlabel[nx][ny].setBackground(Color.RED);
            }
            step = (step + 1) % walks.length;
        }
        return jlabel;
    }

    private ImageIcon getResizedImageIcon(ImageIcon icon, JLabel label) {
        int imgWidth = icon.getIconWidth();
        int imgHeight = icon.getIconHeight();
        int conWidth = label.getWidth();
        int conHeight = label.getHeight();
        int reImgWidth;
        int reImgHeight;
        if (imgWidth / imgHeight >= conWidth / conHeight) {
            if (imgWidth > conWidth) {
                reImgWidth = conWidth;
                reImgHeight = imgHeight * reImgWidth / imgWidth;
            } else {
                reImgWidth = imgWidth;
                reImgHeight = imgHeight;
            }
        } else {
            if (imgWidth > conWidth) {
                reImgHeight = conHeight;
                reImgWidth = imgWidth * reImgHeight / imgHeight;
            } else {
                reImgWidth = imgWidth;
                reImgHeight = imgHeight;
            }
        }
        //这个是强制缩放到与组件(Label)大小相同
//        icon = new ImageIcon(icon.getImage()
//                .getScaledInstance(
//                        label.getWidth(),
//                        label.getHeight(),
//                        Image.SCALE_DEFAULT));
        //这个是按等比缩放
        icon = new ImageIcon(icon.getImage().getScaledInstance(reImgWidth, reImgHeight, Image.SCALE_DEFAULT));
        return icon;
    }

    //以下这些事初始化时调用的设置
    // 设置棋盘和格子的框架样子，就是每个矩形的border
    public Border setBorder() {
        //这里可以设置棋盘的格子的border样式
        //createEtchedBorder(BorderUIResource.EtchedBorderUIResource.RAISED);
        //createLoweredSoftBevelBorder();
//        BorderFactory.createEtchedBorder(BorderUIResource.EtchedBorderUIResource.LOWERED,
//                new Color(Integer.parseInt("660000",16)),
//                new Color(Integer.parseInt("cc0000",16)));
        return BorderFactory.createLineBorder(Color.BLACK);
    }

    // 我直接把框架类扔给你美化吧
    public Face setFaceUI(Face face) {
        //这里能对整个游戏的外部框架，菜单栏进行美化，还能添加你觉得需要的其他容器
        JMenu menu = new JMenu("一般人你tm给我在这里加一个菜单试试！");
        JMenuItem item = new JMenuItem("撤销");
        JMenuBar bar = new JMenuBar();
        menu.add(item);
        bar.add(menu);
        try {
            Class<?> cls = Class.forName("Show.Face");
            Field menuBar = cls.getDeclaredField("menuBar");
            menuBar.setAccessible(true);
            menuBar.set(face, bar);
        } catch (Exception e) {
            e.printStackTrace();
        }

        face.getContentPane().add(bar);


        return face;
    }

    // 我直接把棋盘类扔给你。让你美化算了
    public MyPanel setJPanelUI(MyPanel myPanel) {
        //这里能对棋盘jpanel进行美化

        return myPanel;
    }

    //这是时时调用的。每次线程都会调用
    // 图形化美化进度条，这里是时时更新的。
    public JProgressBar beautifyBar(JProgressBar bar) {
        //这里可以美化进度条，你可能需要的参数。我也提供了
        int allTime = Common.getAlltime();// 游戏的总时间
        int count = Common.getCount();// 游戏当前碰撞次数
        int useTime = Common.getUseTime();// 游戏已用时间
        bar.setBackground(Color.GRAY);
        bar.setForeground(Color.RED);
        bar.setString(String.valueOf(allTime - count - useTime));
        bar.setStringPainted(true);
        return bar;
    }
}
