package com.zhiqiang.game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyBullet {
	/***属性***/
	//坐标
	int eb_x,eb_y;
	//大小
	int eb_width,eb_height;
	//生命状态
	boolean isLife;
	//窗体
	GameStart gs;
	
	//构造函数
	public EnemyBullet(int eb_x, int eb_y, int eb_width, int eb_height,
			boolean isLife, GameStart gs) {
		super();
		this.eb_x = eb_x;
		this.eb_y = eb_y;
		this.eb_width = eb_width;
		this.eb_height = eb_height;
		this.isLife = isLife;
		this.gs = gs;
	}
	
	//敌军子弹绘制
	public void drawEnemyBullet(Graphics g) {
		if(isLife==true){
			g.drawImage(gs.enemybullet_image, eb_x, eb_y, eb_width,eb_height, gs);
			enemyBulletMove();
		}
	}
	
	//敌军子弹移动
	public void enemyBulletMove(){
		eb_y += 5;
		if(eb_y>600){
			isLife=false;
		}
	}
	
	//获取子弹面积
	public Rectangle getEnBulletSize(){
		Rectangle rtg = new Rectangle(eb_x,eb_y,eb_width,eb_height);
		return rtg;
	}
	
	//敌机子弹与我军飞机碰撞(相交)
	public void eBuMpIntersects(){
		if(this.isLife&&gs.mp.isLife&&this.getEnBulletSize().intersects(gs.mp.getMyPlaneSize())){
			this.isLife=false;
			gs.mp.mp_health-=10;
			Boom boom = new Boom(gs.mp.mp_x-15, gs.mp.mp_y-20,gs.mp.mp_width, gs.mp.mp_height, true, gs);
			gs.boom_list.add(boom);
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
