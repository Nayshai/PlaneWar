package com.zhiqiang.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

//我军飞机的类
public class MyPlane {
	
	/***属性***/
	//坐标
	int mp_x,mp_y;
	//大小
	int mp_width,mp_height;
	//生命值
	int mp_health;
	//双发子弹数量
	int doublefire_num;
	//飞镖子弹数量
	int dartsfire_num;
	//超级武器数量
	int superfire_num;
	//生命状态
	boolean isLife;
	//窗体
	GameStart gs;
	
	//定义方向：初始方向为停止
	Direction dir = Direction.STOP;
	//对键盘按键的判断
	boolean isUp,isDown,isLeft,isRight;
	
	
	
	/***方法***/
	//发射
	public void fire(){
		Bullet bl = new Bullet(mp_x+35, mp_y-40, 15, 25, true, gs);
		gs.bl_list.add(bl);
	}
	//发射两发子弹
	public void doublefire(){
		Bullet bl_left = new Bullet(mp_x+10, mp_y-40, 15, 25, true, gs);
		Bullet bl_right = new Bullet(mp_x+60, mp_y-40, 15, 25, true, gs);
		gs.bl_list.add(bl_left);
		gs.bl_list.add(bl_right);
	}
	//发射三发特殊子弹
	public void dartsfire(){
		DartsBullet dbl_left = new DartsBullet(mp_x-15, mp_y-40, 25, 25, true, gs);
		DartsBullet dbl_right = new DartsBullet(mp_x+70, mp_y-40, 25, 25, true, gs);
		DartsBullet dbl_middle = new DartsBullet(mp_x+30, mp_y-40, 25, 25, true, gs);
		gs.dbl_list.add(dbl_left);
		gs.dbl_list.add(dbl_right);
		gs.dbl_list.add(dbl_middle);
	}
	//超级武器发射
		public void superfire(){
			SuperFire spf = new SuperFire(0,500, 400, 50, true, gs);
			gs.spf_list.add(spf);
		}
	//移动
	public void mpMove(){
		//得到飞机的移动方向
		getDirection();
		//根据方向来移动飞机
		if(dir == Direction.UP){
				mp_y -= 5;
		}else if(dir == Direction.DOWN){
				mp_y+= 5;
		}else if(dir == Direction.LEFT){
				mp_x-= 5;
		}else if(dir == Direction.RIGHT){
				mp_x+= 5;
		}else if(dir == Direction.Left_UP){
				mp_x -= 5;
				mp_y -= 5;
		}else if(dir == Direction.Left_Down){
				mp_x -= 5;
				mp_y += 5;
		}else if(dir == Direction.Right_UP){
				mp_x += 5;
				mp_y -= 5;
		}else if(dir == Direction.Right_Down){
				mp_x += 5;
				mp_y += 5;
		}
		
		//让飞机不要超出边界
		if(mp_y >= 530){
			mp_y = 530;
		}
		if(mp_x >= 355){
			mp_x = 355;
		}
		if(mp_y <= 25){
			mp_y = 25;
		}
		if(mp_x <= -35){
			mp_x = -35;
		}
	}
	
	//int bulletcount=0;
	//绘制
	public void drawMyPlane(Graphics g){
		//判断生命状态
		if(isLife){
//			if(dir == Direction.UP){
//				g.drawImage(gs.plane_Up, this.mp_x, this.mp_y, mp_width, mp_height, gs);
//			}else if(dir == Direction.DOWN){
//				g.drawImage(gs.plane_Down, this.mp_x, this.mp_y, mp_width, mp_height, gs);
//			}else if(dir == Direction.LEFT){
//				g.drawImage(gs.plane_Left, this.mp_x, this.mp_y, mp_width, mp_height, gs);
//			}else if(dir == Direction.RIGHT){
//				g.drawImage(gs.plane_Right, this.mp_x, this.mp_y, mp_width, mp_height, gs);
//			}else if(dir == Direction.Left_UP){
//				g.drawImage(gs.plane_LeftUp, this.mp_x, this.mp_y, mp_width, mp_height, gs);
//			}else if(dir == Direction.Left_Down){
//				g.drawImage(gs.plane_LeftDown, this.mp_x, this.mp_y, mp_width, mp_height, gs);
//			}else if(dir == Direction.Right_UP){
//				g.drawImage(gs.plane_RightUp, this.mp_x, this.mp_y, mp_width, mp_height, gs);
//			}else if(dir == Direction.Right_Down){
//				g.drawImage(gs.plane_RightDown, this.mp_x, this.mp_y, mp_width, mp_height, gs);
//			}else{
				g.drawImage(gs.plane_image, mp_x, mp_y, mp_width, mp_height, gs);
//			}
			
			mpMove();
			if(System.currentTimeMillis()-gs.bullet_time>=200){
				if(doublefire_num>0){
					gs.bullet_image=gs.doublebullet_image;
					doublefire();
					gs.gso.playSound("./music/DoubleBullet.mp3");
					gs.bullet_time = System.currentTimeMillis();
					doublefire_num--;
				}else if(dartsfire_num>0){
					dartsfire();
					gs.gso.playSound("./music/DartsFire.mp3");
					gs.bullet_time = System.currentTimeMillis();
					dartsfire_num--;
				}else{
					gs.bullet_image=gs.bullet_image_tmp;
					fire();
					gs.gso.playSound("./music/Bullet.mp3");
					gs.bullet_time = System.currentTimeMillis();
				}
			}
//			if(System.currentTimeMillis()-gs.bullet_time>=200){
//				if(bulletcount<50){
//					fire();
//					bulletcount++;
//					gs.gso.playSound("./music/shoot.mp3");
//					gs.bullet_time = System.currentTimeMillis();
//				}else{
//					
//				}
//			}
		}
	}
	
	//按下键盘
	public void keyPressed(KeyEvent e){
		//得到键的值
		int num = e.getKeyCode();
		if(num == KeyEvent.VK_W){
			isUp = true;
		}else if(num == KeyEvent.VK_S){
			isDown = true;
		}else if(num == KeyEvent.VK_A){
			isLeft = true;
		}else if(num == KeyEvent.VK_D){
			isRight = true;
		}else if(gs.flag==0 && num == KeyEvent.VK_ENTER){
			gs.flag++;
		}else if(gs.flag==2 && num == KeyEvent.VK_ESCAPE){
			gs.flag=0;
			gs.score=0;
			gs.mp.isLife=true;
			gs.mp.mp_health=100;
		}else if(gs.flag==1 && num == KeyEvent.VK_SPACE){
			if(superfire_num>0){
				superfire();
				gs.gso.playSound("./music/SuperFire.mp3");
				superfire_num--;
			}
		}
	}
	//松开键盘
	public void keyReleased(KeyEvent e){
		//得到键的值
		int num = e.getKeyCode();
		if(num == KeyEvent.VK_W){
			isUp = false;
		}else if(num == KeyEvent.VK_S){
			isDown = false;
		}else if(num == KeyEvent.VK_A){
			isLeft = false;
		}else if(num == KeyEvent.VK_D){
			isRight = false;
		}
	}
	//根据这个按键的状态来确定飞机移动的方向
	public void getDirection(){
		if(isUp && !isDown && !isLeft && !isRight){
			dir = Direction.UP;
		}else if(!isUp && isDown && !isLeft && !isRight){
			dir = Direction.DOWN;
		}else if(!isUp && !isDown && isLeft && !isRight){
			dir = Direction.LEFT;
		}else if(!isUp && !isDown && !isLeft && isRight){
			dir = Direction.RIGHT;
		}else if(isUp && !isDown && isLeft && !isRight){
			dir = Direction.Left_UP;
		}else if(!isUp && isDown && isLeft && !isRight){
			dir = Direction.Left_Down;
		}else if(isUp && !isDown && !isLeft && isRight){
			dir = Direction.Right_UP;
		}else if(!isUp && isDown && !isLeft && isRight){
			dir = Direction.Right_Down;
		}else if(!isUp && !isDown && !isLeft && !isRight){
			dir = Direction.STOP;
		}
	}
	
	/***构造函数***/
	public MyPlane(int mp_x, int mp_y, int mp_width, int mp_height,
			int mp_health,int doublefire_num,int dartsfire_num,int superfire_num,boolean isLife, GameStart gs) {
		super();
		this.mp_x = mp_x;
		this.mp_y = mp_y;
		this.mp_width = mp_width;
		this.mp_height = mp_height;
		this.mp_health = mp_health;
		this.doublefire_num = doublefire_num;
		this.dartsfire_num = dartsfire_num;
		this.superfire_num = superfire_num;
		this.isLife = isLife;
		this.gs = gs;
	}
	
	//获取飞机面积
	public Rectangle getMyPlaneSize(){
		Rectangle rtg = new Rectangle(mp_x,mp_y,mp_width,mp_height);
		return rtg;
	}
	
}
