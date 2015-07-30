package com.zhiqiang.game;

import java.awt.Graphics;

//背景
public class Background {
	//坐标
	int bg_x,bg_y;
	int bg_y_b;
	//大小
	int bg_width,bg_height;
	//窗体
	GameStart gs;
	//构造函数
	public Background(int bg_x, int bg_y, int bg_width, int bg_height, GameStart gs) {
		super();
		this.bg_x = bg_x;
		this.bg_y = bg_y;
		this.bg_width = bg_width;
		this.bg_height = bg_height;
		this.gs = gs;
		this.bg_y_b = bg_y -bg_height;
	}
	//绘制
	public void drawBackground(Graphics g){
		g.drawImage(gs.background_image, bg_x, bg_y, bg_width, bg_height, gs);
		g.drawImage(gs.background_image, bg_x, bg_y_b, bg_width, bg_height, gs);
		//当背景绘制出来就让其进行移动
		bgMove();
	}
	//移动
	public void bgMove(){
		bg_y+=2;
		bg_y_b +=2;
		
		//背景的循环
		if(bg_y >= bg_height){ //说明第一张背景已经超出边界
			bg_y = bg_y_b - bg_height;
		}
		if(bg_y_b >= bg_height){ //第二张背景已经超出边界
			bg_y_b = bg_y - bg_height;
		}
	}
}
