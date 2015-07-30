package com.zhiqiang.game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class BulletUpdate {
	//坐标
	int buup_x,buup_y;
	//大小
	int buup_width,buup_height;
	//生命状态
	boolean isLife;
	//窗体
	GameStart gs;
	
	public BulletUpdate(int buup_x, int buup_y, int buup_width,
			int buup_height, boolean isLife, GameStart gs) {
		super();
		this.buup_x = buup_x;
		this.buup_y = buup_y;
		this.buup_width = buup_width;
		this.buup_height = buup_height;
		this.isLife = isLife;
		this.gs = gs;
	}
	
	//武器升级包绘制
	public void drawBulletUpdate(Graphics g) {
		if(isLife==true){
			g.drawImage(gs.bulletupdate_image, buup_x, buup_y, buup_width,buup_height, gs);
			bulletUpdateMove();
		}
	}
	
	//武器升级包移动
	public void bulletUpdateMove(){
		buup_y += 4;
		if(buup_y>600){
			isLife=false;
		}
	}
	
	//获取武器升级包面积
	public Rectangle getBulletUpdateSize(){
		Rectangle rtg = new Rectangle(buup_x,buup_y,buup_width,buup_height);
		return rtg;
	}
	
	//武器升级包与我军飞机碰撞(相交)
	public void bulletUpMpIntersects(){
		if(this.isLife&&gs.mp.isLife&&this.getBulletUpdateSize().intersects(gs.mp.getMyPlaneSize())){
			this.isLife=false;
			this.gs.mp.doublefire_num+=100;
			this.gs.mp.dartsfire_num=0;
			gs.gso.playSound("./music/GetItem.mp3");
		}
	}
}
