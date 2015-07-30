package com.zhiqiang.game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class DartsFireUpdate {
	//坐标
	int dbup_x,dbup_y;
	//大小
	int dbup_width,dbup_height;
	//生命状态
	boolean isLife;
	//窗体
	GameStart gs;
	public DartsFireUpdate(int dbup_x, int dbup_y, int dbup_width,
			int dbup_height, boolean isLife, GameStart gs) {
		super();
		this.dbup_x = dbup_x;
		this.dbup_y = dbup_y;
		this.dbup_width = dbup_width;
		this.dbup_height = dbup_height;
		this.isLife = isLife;
		this.gs = gs;
	}
	//飞镖武器升级包绘制
	public void drawDartsFireUpdate(Graphics g) {
		if(isLife==true){
			g.drawImage(gs.dartsfireupdate_image, dbup_x, dbup_y, dbup_width,dbup_height, gs);
			dartsFireUpdateMove();
		}
	}
	
	//飞镖武器升级包移动
	public void dartsFireUpdateMove(){
		dbup_y += 5;
		if(dbup_y>600){
			isLife=false;
		}
	}
	
	//获取飞镖武器升级包面积
	public Rectangle getDartsFireUpdateSize(){
		Rectangle rtg = new Rectangle(dbup_x,dbup_y,dbup_width,dbup_height);
		return rtg;
	}
	
	//飞镖武器升级包与我军飞机碰撞(相交)
	public void dbUpMpIntersects(){
		if(this.isLife&&gs.mp.isLife&&this.getDartsFireUpdateSize().intersects(gs.mp.getMyPlaneSize())){
			this.isLife=false;
			this.gs.mp.dartsfire_num+=50;
			this.gs.mp.doublefire_num=0;
			gs.gso.playSound("./music/GetItem.mp3");
		}
	}
}
