
/*
 * @author:加藤鹰
 * @date:2017年11月13日 上午9:50:59
 * @description:
 */

import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

import Bean.Enity;
import Show.Face;
import Show.MyPanel;
import Transfer.InterFace;

public class InterFaceImp implements InterFace {
	// 这个函数是负责画画的。画每个点当前的位置，目标的位置，通过jlabel来定位
	public JLabel[][] draw(List<Enity> now, List<Enity> aim, JLabel[][] jlabel) {
		// 这里你们可以把人物以图标的形式展现出来，也可以改善下棋盘框框的样子

		// 以下是画画的流程
		// 1.棋盘清空
		for (int i = 0; i < jlabel.length; i++) {
			for (int j = 0; j < jlabel[i].length; j++) {
				jlabel[i][j].setText("");
				jlabel[i][j].setBackground(Color.red);
			}
		}
		// 2.画目标位置(党徽)
		for (int i = 0; i < aim.size(); i++) {
			jlabel[aim.get(i).getX()][aim.get(i).getY()].setBackground(Color.GRAY);
		}
		if (now != null)
			// 3. 画上现在的每个点的位置，这里可以增加图标
			for (int i = 0; i < now.size(); i++) {
				jlabel[now.get(i).getX()][now.get(i).getY()].setText(
						jlabel[now.get(i).getX()][now.get(i).getY()].getText() + " " + now.get(i).getId() + "");
				jlabel[now.get(i).getX()][now.get(i).getY()].setBackground(Color.YELLOW);
			}
		return jlabel;
	}

	//以下这些事初始化时调用的设置
	// 设置棋盘和格子的框架样子，就是每个矩形的border
	public Border setBorder() {
		//这里可以设置棋盘的格子的border样式
		return BorderFactory.createLineBorder(Color.black);
	}

	// 我直接把框架类扔给你美化吧
	public Face setFaceUI(Face face) {
		//这里能对整个游戏的外部框架，菜单栏进行美化，还能添加你觉得需要的其他容器
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
		Common.Common.getAlltime();// 游戏的总时间
		Common.Common.getCount();// 游戏当前碰撞次数
		Common.Common.getUseTime();// 游戏已用时间
		bar.setBackground(Color.GRAY);
		bar.setForeground(Color.RED);
		return bar;
	}
}
