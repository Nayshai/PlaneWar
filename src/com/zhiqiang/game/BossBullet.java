package com.zhiqiang.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossBullet {
	//坐标
	int bossbullet_x,bossbullet_y;
	//大小
	int bossbullet_width,bossbullet_height;
	//生命状态
	boolean isLife;
	//窗体
	GameStart gs;
	double speed=3;
	double speed_x;
	double speed_y;
	
	public BossBullet(int bossbullet_x, int bossbullet_y, int bossbullet_width,
			int bossbullet_height, boolean isLife,  GameStart gs, int angle) {
		super();
		this.bossbullet_x = bossbullet_x;
		this.bossbullet_y = bossbullet_y;
		this.bossbullet_width = bossbullet_width;
		this.bossbullet_height = bossbullet_height;
		this.isLife = isLife;
		this.gs = gs;
		speed_x=speed*Math.sin(angle);
		speed_y=speed*Math.cos(angle);
	}
	
	//绘制
	Random r=new Random();
	int index=r.nextInt(10);
	public void drawBossBullet(Graphics g){
		//判断生命状态
		if(isLife==true){
			if(index<9){
				g.drawImage(gs.bossbullet_image[0], bossbullet_x, bossbullet_y, bossbullet_width, bossbullet_height, gs);
				bossBulletsMove();
			}else{
				g.drawImage(gs.bossbullet_image[1], bossbullet_x, bossbullet_y, bossbullet_width, bossbullet_height, gs);
				bossOneBulletMove();
			}
		}
	}
	
	//移动
	public void bossOneBulletMove(){
		bossbullet_y+=3;
		if(bossbullet_y>600||bossbullet_y<0||bossbullet_x>400||bossbullet_x<0){
			isLife=false;
		}
	}
	
	public void bossBulletsMove(){
		bossbullet_y+=speed_y;
		bossbullet_x+=speed_x;
		if(bossbullet_y>600||bossbullet_y<0||bossbullet_x>400||bossbullet_x<0){
			isLife=false;
		}
	}
	
	//获取敌机面积
	public Rectangle getBossBulletSize(){
		Rectangle rtg = new Rectangle(bossbullet_x,bossbullet_y,bossbullet_width,bossbullet_height);
		return rtg;
	}
	
	//BOSS子弹与我军飞机碰撞
	public void bossBuMpIntersects(){
		if(this.isLife==true&&gs.mp.isLife==true&&this.getBossBulletSize().intersects(gs.mp.getMyPlaneSize())){
			isLife=false;
			Boom boommp = new Boom(gs.mp.mp_x-15, gs.mp.mp_y-20,gs.mp.mp_width, gs.mp.mp_height, true, gs);
			gs.boom_list.add(boommp);
			gs.gso.playSound("./music/Enemy_Boom.mp3");
			gs.mp.mp_health-=10;
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
