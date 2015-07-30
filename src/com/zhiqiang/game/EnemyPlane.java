package com.zhiqiang.game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyPlane {
	
	//坐标
	int ep_x,ep_y;
	//大小
	int ep_width,ep_height;
	//生命状态
	boolean isLife;
	//窗体
	GameStart gs;
	
	//构造函数
	public EnemyPlane(int ep_x, int ep_y, int ep_width, int ep_height,
			boolean isLife, GameStart gs) {
		super();
		this.ep_x = ep_x;
		this.ep_y = ep_y;
		this.ep_width = ep_width;
		this.ep_height = ep_height;
		this.isLife = isLife;
		this.gs = gs;
	}
	
	//绘制
	public void drawEnPlane(Graphics g){
		//判断生命状态
		if(isLife){
			g.drawImage(gs.enplane_image, ep_x, ep_y, ep_width, ep_height, gs);
			epMove();
		}
	}
	
	//移动
	public void epMove(){
		ep_y += 3;
		if(ep_y>600){
			this.isLife=false;
		}
	}
	
	//获取敌机面积
	public Rectangle getEnemyPlaneSize(){
		Rectangle rtg = new Rectangle(ep_x,ep_y,ep_width,ep_height);
		return rtg;
	}
	
	//敌机与我军飞机碰撞
	public void epEpIntersects(){
		if(this.isLife&&gs.mp.isLife&&this.getEnemyPlaneSize().intersects(gs.mp.getMyPlaneSize())){
			this.isLife=false;
			gs.mp.mp_health-=10;
			Boom boom = new Boom(ep_x-30, ep_y-20, ep_width, ep_height, true, gs);
			gs.boom_list.add(boom);
			Boom boommp = new Boom(gs.mp.mp_x-15, gs.mp.mp_y-20,gs.mp.mp_width, gs.mp.mp_height, true, gs);
			gs.boom_list.add(boommp);
			gs.gso.playSound("./music/Enemy_Boom.mp3");
			if(gs.mp.mp_health<=0){
				gs.gso.playSound("./music/gameover.mp3");
				gs.mp.isLife=false;
				gs.flag++;
			}
			if(gs.mp.mp_health>10&&gs.mp.mp_health<=30){
				gs.gso.playSound("./music/Health_Low.mp3");
			}
		}
	}
	
}
