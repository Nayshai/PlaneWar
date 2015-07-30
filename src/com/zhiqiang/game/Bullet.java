package com.zhiqiang.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class Bullet {
	/***属性***/
	//坐标
	int bl_x,bl_y;
	//大小
	int bl_width,bl_height;
	//生命状态
	boolean isLife;
	//窗体
	GameStart gs;
	public Bullet(int bl_x, int bl_y, int bl_width, int bl_height,
			boolean isLife, GameStart gs) {
		super();
		this.bl_x = bl_x;
		this.bl_y = bl_y;
		this.bl_width = bl_width;
		this.bl_height = bl_height;
		this.isLife = isLife;
		this.gs = gs;
	}
	
	public void drawBullet(Graphics g) {
		if(isLife==true){
			g.drawImage(gs.bullet_image, bl_x, bl_y, bl_width,bl_height, gs);
			bulletMove();
		}
	}
	
//	double a = 0.2;
//	bl_x += Math.sin(a)*10;
//	a += 0.2;
	public void bulletMove(){
		bl_y -= 5;
		if(bl_y<0){
			isLife=false;
		}
	}
	
	//获取子弹面积
	public Rectangle getBulletSize(){
		Rectangle rtg = new Rectangle(bl_x,bl_y,bl_width,bl_height);
		return rtg;
	}
	
	//子弹与敌机碰撞(相交)
	public void buEpIntersects(List<EnemyPlane> list){
		for (int i = 0; i < list.size(); i++) {
			EnemyPlane ep = list.get(i);
			if(this.isLife&&ep.isLife&&this.getBulletSize().intersects(ep.getEnemyPlaneSize())){
				this.isLife=false;
				ep.isLife=false;
				Boom boom = new Boom(ep.ep_x-30, ep.ep_y-20, ep.ep_width, ep.ep_height, true, gs);
				gs.boom_list.add(boom);
				gs.gso.playSound("./music/Enemy_Boom.mp3");
				gs.score+=20;
			}
		}
	}
	
	
	//子弹与敌人战机碰撞(相交)
	public void buEcpIntersects(List<EnemyCombatPlane> list){
		for (int i = 0; i < list.size(); i++) {
			EnemyCombatPlane ept = list.get(i);
			if(this.isLife&&ept.isLife&&this.getBulletSize().intersects(ept.getEnemyPlaneTSize())){
				this.isLife=false;
				ept.isLife=false;
				Boom boom = new Boom(ept.ecp_x-30, ept.ecp_y-20, ept.ecp_width, ept.ecp_height, true, gs);
				gs.boom_list.add(boom);
				gs.gso.playSound("./music/Enemy_Boom.mp3");
				gs.score+=40;
			}
		}
	}
	
	//子弹与BOSS碰撞
	public void buBossIntersects(List<Boss> list){
		for (int i = 0; i < list.size(); i++) {
			Boss boss = list.get(i);
			if(this.isLife&&boss.isLife&&this.getBulletSize().intersects(boss.getBossSize())){
				this.isLife=false;
				boss.health-=5;
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
