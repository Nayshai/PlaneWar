package com.zhiqiang.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyCombatPlane {
	//坐标
	int ecp_x,ecp_y;
	//大小
	int ecp_width,ecp_height;
	//生命状态
	boolean isLife;
	//窗体
	GameStart gs;
	
	public EnemyCombatPlane(int ept_x, int ept_y, int ept_width, int ept_height,
			boolean isLife, GameStart gs) {
		super();
		this.ecp_x = ept_x;
		this.ecp_y = ept_y;
		this.ecp_width = ept_width;
		this.ecp_height = ept_height;
		this.isLife = isLife;
		this.gs = gs;
	}
	Random r=new Random();
	int index=r.nextInt(4);
	//绘制
	public void drawEnPlaneT(Graphics g){
		//判断生命状态
		if(isLife==true){
			g.drawImage(gs.encbtplane_image[index], ecp_x, ecp_y, ecp_width, ecp_height, gs);
			eptMove();
			//if(System.currentTimeMillis()-gs.enemybullet_time>=200){
			if(System.currentTimeMillis()%77==0){
				this.fire();
				//gs.enemybullet_time = System.currentTimeMillis();
			}
		}
	}
	
	//移动
	public void eptMove(){
		ecp_y += 4;
		if(ecp_y>600){
			this.isLife=false;
		}
	}
	
	//获取敌机面积
	public Rectangle getEnemyPlaneTSize(){
		Rectangle rtg = new Rectangle(ecp_x,ecp_y,ecp_width,ecp_height);
		return rtg;
	}
	
	//发射
	public void fire(){
		EnemyBullet ebl = new EnemyBullet(ecp_x+30, ecp_y+60, 15, 25, true, gs);
		gs.ebl_list.add(ebl);
	}
	
	//敌机与我军飞机碰撞
	public void eptEpIntersects(){
		if(this.isLife&&gs.mp.isLife&&this.getEnemyPlaneTSize().intersects(gs.mp.getMyPlaneSize())){
			this.isLife=false;
			gs.mp.mp_health-=10;
			Boom boom = new Boom(ecp_x-30, ecp_y-20, ecp_width, ecp_height, true, gs);
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
