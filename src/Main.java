
import java.util.ArrayList;
import java.util.List;

import Bean.Enity;
import Common.Common;
import Utile.Utile;
import Service.Logic;

import javax.swing.*;

public class Main {

	public static void main(String[] args) 
	{
		
		Enity temp=Utile.copyEnity(new Enity());//这个是深度拷贝节点函数，可以帮助用户深度拷贝这个节点
		List<Enity> list=Utile.copyList(new ArrayList<Enity>());//这个是深度拷贝列表，包括里面的每个节点都是深度拷贝了
		
		Common.getHEIGHT();//获取棋盘高度
		Common.getHEIGHT();//获取棋盘宽度
		Common.getSTEP();//获取步长
		Common.getSpeed();//获得游戏刷新速度
		Common.setSpeed(1000);//设置游戏刷新速度一秒，为了便于调速，但是比赛官方实数是1秒
		Common.getAlltime();// 游戏的总时间
		Common.getCount();// 游戏当前碰撞次数
		Common.getUseTime();// 游戏已用时间
		
		//逻辑部分不能是主线程。否则没法让它暂停
		//所以整个项目有两个线程，一个是逻辑部分，一个是图形界面部分
		//逻辑部分算法的好坏会影响游戏时间的，图形界面进程定时刷新的(不影响比赛时间)
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		new Thread(new Logic(new AlgorithmImp(), new InterFaceImp())).start();
	}

}
