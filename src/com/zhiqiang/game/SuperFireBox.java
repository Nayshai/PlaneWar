package com.zhiqiang.game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class SuperFireBox {
	/***属性***/
	//坐标
	int sfb_x,sfb_y;
	//大小
	int sfb_width,sfb_height;
	//生命状态
	boolean isLife;
	//窗体
	GameStart gs;
	public SuperFireBox(int sfa_x, int sfa_y, int sfa_width, int sfa_height,
			boolean isLife, GameStart gs) {
		super();
		this.sfb_x = sfa_x;
		this.sfb_y = sfa_y;
		this.sfb_width = sfa_width;
		this.sfb_height = sfa_height;
		this.isLife = isLife;
		this.gs = gs;
	}
	//超级武器包绘制
	public void drawSpFireBox(Graphics g) {
		if(isLife==true){
			g.drawImage(gs.superfirebox_image, sfb_x, sfb_y, sfb_width,sfb_height, gs);
			spFireBoxMove();
		}
	}
	
	//超级武器包移动
	public void spFireBoxMove(){
		sfb_y += 4;
		if(sfb_y>600){
			isLife=false;
		}
	}
	
	//超级武器包面积
	public Rectangle getSpFireBoxSize(){
		Rectangle rtg = new Rectangle(sfb_x,sfb_y,sfb_width,sfb_height);
		return rtg;
	}
	
	//超级武器包与我军飞机碰撞(相交)
	public void sfbMpIntersects(){
		if(this.isLife&&gs.mp.isLife&&this.getSpFireBoxSize().intersects(gs.mp.getMyPlaneSize())){
			this.isLife=false;
			gs.gso.playSound("./music/GetItem.mp3");
			this.gs.mp.superfire_num++;
		}
	}
	
}
