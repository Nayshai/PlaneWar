package com.zhiqiang.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class DartsBullet {
	/***属性***/
	//坐标
	int dbl_x,dbl_y;
	//大小
	int dbl_width,dbl_height;
	//生命状态
	boolean isLife;
	//窗体
	GameStart gs;
	public DartsBullet(int dbl_x, int dbl_y, int dbl_width, int dbl_height,
			boolean isLife, GameStart gs) {
		super();
		this.dbl_x = dbl_x;
		this.dbl_y = dbl_y;
		this.dbl_width = dbl_width;
		this.dbl_height = dbl_height;
		this.isLife = isLife;
		this.gs = gs;
	}
	//绘制
	public void drawBullet(Graphics g) {
		if(isLife==true){
			g.drawImage(gs.darts_image, dbl_x, dbl_y, dbl_width,dbl_height, gs);
			dartsbulletMove();
		}
	}
	//移动
	public void dartsbulletMove(){
		dbl_y -= 5;
		if(dbl_y<0){
			isLife=false;
		}
	}
	//获取大小
	public Rectangle getDartsBulletSize(){
		Rectangle rtg = new Rectangle(dbl_x,dbl_y,dbl_width,dbl_height);
		return rtg;
	}
	//与敌机的碰撞检测
	public void dbuEpIntersects(List<EnemyPlane> list){
		for (int i = 0; i < list.size(); i++) {
			EnemyPlane ep = list.get(i);
			if(this.isLife&&ep.isLife&&this.getDartsBulletSize().intersects(ep.getEnemyPlaneSize())){
				ep.isLife=false;
				Boom boom = new Boom(ep.ep_x-30, ep.ep_y-20, ep.ep_width, ep.ep_height, true, gs);
				gs.boom_list.add(boom);
				gs.gso.playSound("./music/Enemy_Boom.mp3");
				gs.score+=20;
			}
			
		}
	}
	//与敌人战机的碰撞检测
	public void dbuEcpIntersects(List<EnemyCombatPlane> list){
		for (int i = 0; i < list.size(); i++) {
			EnemyCombatPlane ept = list.get(i);
			if(this.isLife&&ept.isLife&&this.getDartsBulletSize().intersects(ept.getEnemyPlaneTSize())){
				ept.isLife=false;
				Boom boom = new Boom(ept.ecp_x-30, ept.ecp_y-20, ept.ecp_width, ept.ecp_height, true, gs);
				gs.boom_list.add(boom);
				gs.gso.playSound("./music/Enemy_Boom.mp3");
				gs.score+=40;
			}
			
		}
	}
	//与BOSS的碰撞检测
	public void dbuBossIntersects(List<Boss> list){
		for (int i = 0; i < list.size(); i++) {
			Boss boss = list.get(i);
			if(this.isLife&&boss.isLife&&this.getDartsBulletSize().intersects(boss.getBossSize())){
				boss.health-=1;
				if(gs.boss.health<=0){
					boss.health=0;
					gs.boss.isLife=false;
					gs.score+=300;
				}
				Boom boom = new Boom(boss.boss_x-30, boss.boss_y-20, boss.boss_width, boss.boss_height, true, gs);
				gs.boom_list.add(boom);
				gs.gso.playSound("./music/Enemy_Boom.mp3");
			}
		}
	}
}
