package com.zhiqiang.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 
 * 日期：2015年7月3日
 * 版本：Beta
 * 版本号：1.0
 * 作者：刘汉宸 Hansen·Lau
 * 
 */

/*
 * 
 * Frame:作为桌面应用程序的类
 * 继承：A类继承B类，那么A类就具有了B类里面所有属性和方法
 * 
 */

//作为启动的界面
@SuppressWarnings("serial")
public class GameStart extends Frame{
	
	/********图片区域********/
	Toolkit tk = Toolkit.getDefaultToolkit();
	//得到飞机图片
	Image plane_image = tk.getImage(GameStart.class.getResource("/images/Plane.png"));
	//得到飞机八方向飞行图片
	Image plane_Up = tk.getImage(GameStart.class.getResource("/images/Plane/Plane_Up.png"));
	Image plane_Down = tk.getImage(GameStart.class.getResource("/images/Plane/Plane_Down.png"));
	Image plane_Left = tk.getImage(GameStart.class.getResource("/images/Plane/Plane_Left.png"));
	Image plane_Right = tk.getImage(GameStart.class.getResource("/images/Plane/Plane_Right.png"));
	Image plane_LeftUp = tk.getImage(GameStart.class.getResource("/images/Plane/Plane_LeftUp.png"));
	Image plane_LeftDown = tk.getImage(GameStart.class.getResource("/images/Plane/Plane_LeftDown.png"));
	Image plane_RightUp = tk.getImage(GameStart.class.getResource("/images/Plane/Plane_RightUp.png"));
	Image plane_RightDown = tk.getImage(GameStart.class.getResource("/images/Plane/Plane_RightDown.png"));
	//得到背景图片
	Image background_image = tk.getImage(GameStart.class.getResource("/images/Background.png"));
	//敌机图片
	Image enplane_image = tk.getImage(GameStart.class.getResource("/images/Enemy01.png"));
	//敌军战机图片
	Image encbtplane_image01 = tk.getImage(GameStart.class.getResource("/images/Enemy02.png"));
	Image encbtplane_image02 = tk.getImage(GameStart.class.getResource("/images/Enemy03.png"));
	Image encbtplane_image03= tk.getImage(GameStart.class.getResource("/images/Enemy04.png"));
	Image encbtplane_image04= tk.getImage(GameStart.class.getResource("/images/Enemy05.png"));
	//敌军战机图片集合
	Image encbtplane_image[] ={
			encbtplane_image01,
			encbtplane_image02,
			encbtplane_image03,
			encbtplane_image04
	}; 
	//Boss图片
	Image boss_image = tk.getImage(GameStart.class.getResource("/images/Shit.png"));
	//boss子弹图片
	Image[] bossbullet_image={
			tk.getImage(GameStart.class.getResource("/images/ShitBullet01.png")),
			tk.getImage(GameStart.class.getResource("/images/ShitBullet02.png"))};
	//子弹图片
	Image bullet_image = tk.getImage(GameStart.class.getResource("/images/Bullet01.png"));
	Image bullet_image_tmp = tk.getImage(GameStart.class.getResource("/images/Bullet01.png"));
	Image doublebullet_image = tk.getImage(GameStart.class.getResource("/images/Bullet03.png"));
	Image darts_image = tk.getImage(GameStart.class.getResource("/images/Darts.png"));
	Image enemybullet_image = tk.getImage(GameStart.class.getResource("/images/Bullet02.png"));
	Image superfire_image = tk.getImage(GameStart.class.getResource("/images/SuperWeapon.png"));
	//医疗包图片
	Image medical_image = tk.getImage(GameStart.class.getResource("/images/Medical.png"));
	//子弹升级包图片
	Image bulletupdate_image = tk.getImage(GameStart.class.getResource("/images/BulletUpdate.png"));
	//飞镖子弹升级包图片
	Image dartsfireupdate_image = tk.getImage(GameStart.class.getResource("/images/DartsUpdate.png"));
	//超级武器包图片
	Image superfirebox_image = tk.getImage(GameStart.class.getResource("/images/SuperFireBox.png"));
	//开始界面
	Image start_image = tk.getImage(GameStart.class.getResource("/images/Title.png"));
	Image start_tips_image = tk.getImage(GameStart.class.getResource("/images/Title_Tips.png"));
	Image rocketfly01_image = tk.getImage(GameStart.class.getResource("/images/RocketFly01.png"));
	Image rocketfly02_image = tk.getImage(GameStart.class.getResource("/images/RocketFly02.png"));
	Image rocketfly03_image = tk.getImage(GameStart.class.getResource("/images/RocketFly03.png"));
	Image rocket_image = tk.getImage(GameStart.class.getResource("/images/Rocket.png"));
	//结束界面
	Image end_image = tk.getImage(GameStart.class.getResource("/images/GameOver.png"));
	Image end_tips_image = tk.getImage(GameStart.class.getResource("/images/GameOver_Tips.png"));
	
	/********属性区域********/
	//定义飞机坐标
	//int x=170,y=410;
	//定义一个空白的画布
	Image win_image = null;
	int score=0;
	//开始界面
	int style=0;
	int flag=0;
	
	/********对象区域********/
	//飞机
	MyPlane mp = new MyPlane(160, 450, 80, 70, 100, 0, 0, 10, true, this);
	//背景
	Background bg = new Background(0, 0, 400, 600,this);
	//实例化医疗包
	Medical md = null;
	//实例化子弹升级包
	BulletUpdate buup = null;
	//实例化飞镖子弹升级包
	DartsFireUpdate dbup = null;
	//实例化超级武器包
	SuperFireBox spfbox = null;
	//实例化敌机
	EnemyPlane ep = null;
	//实例化敌人战机
	EnemyCombatPlane ecp = null;
	//实例化BOSS
	Boss boss = null;
	//实例化敌机集合
	List<EnemyPlane> ep_list = new ArrayList<EnemyPlane>();
	//实例化敌人战机集合
	List<EnemyCombatPlane> ecp_list = new ArrayList<EnemyCombatPlane>();
	//实例化子弹集合
	List<Bullet> bl_list = new ArrayList<Bullet>();
	//实例化飞镖子弹集合
	List<DartsBullet> dbl_list = new ArrayList<DartsBullet>();
	//实例化敌军子弹集合
	List<EnemyBullet> ebl_list = new ArrayList<EnemyBullet>();
	//实例化超级武器集合
	List<SuperFire> spf_list = new ArrayList<SuperFire>();
	//实例化医疗包集合
	List<Medical> md_list = new ArrayList<Medical>();
	//实例化子弹升级包集合
	List<BulletUpdate> buup_list = new ArrayList<BulletUpdate>();
	//实例化飞镖子弹升级包集合
	List<DartsFireUpdate> dbup_list = new ArrayList<DartsFireUpdate>();
	//实例化超级武器包集合
	List<SuperFireBox> spfbox_list = new ArrayList<SuperFireBox>();
	//实例化BOSS集合
	List<Boss> boss_list=new ArrayList<Boss>();
	//实例化BOSS子弹集合
	List<BossBullet> bossBullet_list=new ArrayList<BossBullet>();
	//实例化一个随机数
	Random rd = new Random();
	//定义用到的时间
	long time = System.currentTimeMillis();
	long ept_time = System.currentTimeMillis();
	long boss_time = System.currentTimeMillis();
	long bullet_time = System.currentTimeMillis();
	long medical_time = System.currentTimeMillis();
	long bossbullet_time = System.currentTimeMillis();
	long supefirebox_time = System.currentTimeMillis();
	long enemybullet_time = System.currentTimeMillis();
	long bulletupdate_time = System.currentTimeMillis();
	long dartsfireupdate_time = System.currentTimeMillis();
	//实例化声音
	GameSound gso = new GameSound();
	//实例化爆炸集合
	List<Boom> boom_list=new ArrayList<Boom>();
	//解决第一次爆炸
	{boom_list.add(new Boom(0, 0, 30, 30, true, this));}
	
	
	public GameStart(){
		//窗体的大小
		this.setSize(400,600);
		//标题
		this.setTitle("PlaneFantasy");
		//禁止改变窗体大小
		this.setResizable(false);
		//居中
		this.setLocationRelativeTo(null);
		//窗体的监听
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		//键盘监听
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				mp.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				mp.keyReleased(e);
			}
			
		});
		
		//线程的开启
		myThread mt = new myThread();
		mt.start();
		//是否显示
		this.setVisible(true);
		gso.playBgSound("./music/background_music.mp3");
	}
	
	//产生敌机
	public void createEnemyPlane() {
		int x=rd.nextInt(350);
		ep = new EnemyPlane(x, -80, 50, 50, true, this);
		ep_list.add(ep);
	}
	
	//产生敌人战机
	public void createEnemyPlaneT() {
		int x=rd.nextInt(350);
		ecp = new EnemyCombatPlane(x, -80, 70, 70, true, this);
		ecp_list.add(ecp);
	}
	
	//产生医疗包
	public void createMedical() {
		int x=rd.nextInt(350);
		md = new Medical(x, -80, 50, 60, true, this);
		md_list.add(md);
	}
	
	//产生子弹升级包
	public void createBulletUpdate() {
		int x=rd.nextInt(350);
		buup = new BulletUpdate(x, -80, 50, 50, true, this);
		buup_list.add(buup);
	}
	
	//产生飞镖子弹升级包
	public void createDartsFireUpdate() {
		int x=rd.nextInt(350);
		dbup = new DartsFireUpdate(x, -80, 50, 50, true, this);
		dbup_list.add(dbup);
	}
	
	//产生超级武器包
	public void createSpFireBox() {
		int x=rd.nextInt(350);
		spfbox = new SuperFireBox(x, -80, 50, 50, true, this);
		spfbox_list.add(spfbox);
	}
	
	//产生BOSS
	public void createBoss() {
		gso.playSound("./music/Boss_Comming.mp3");
		boss = new Boss(120, 0, 80, 80, true, 100 ,this);
		boss_list.add(boss);
	}
	
	public static void main(String[] args) {
		//实例化
		@SuppressWarnings("unused")
		GameStart gs = new GameStart();
	}
	
	//绘制方法--（当窗体初始化或隐藏后再次显示）
	//绘制飞机
	@Override
	public void paint(Graphics g) {
		//绘制背景
		bg.drawBackground(g);
		if(flag == 0){
			g.drawImage(start_image,30,220,350,140,this);
			g.drawImage(start_tips_image,30,450,340,80,this);
			if(style>=0&&style<=20){
				g.drawImage(rocket_image,20,100,70,40,this);
			}
			if(style>20&&style<=40){
				g.drawImage(rocketfly01_image,20,100,50,50,this);
				g.drawImage(rocket_image,120,100,70,40,this);
			}
			if(style>40&&style<=60){
				g.drawImage(rocketfly02_image,20,100,50,50,this);
				g.drawImage(rocketfly01_image,120,100,50,50,this);
				g.drawImage(rocket_image,220,100,70,40,this);
			}
			if(style>60&&style<=80){
				g.drawImage(rocketfly03_image,20,100,50,50,this);
				g.drawImage(rocketfly02_image,120,100,50,50,this);
				g.drawImage(rocketfly01_image,220,100,50,50,this);
				g.drawImage(rocket_image,320,100,70,40,this);
			}
			if(style>80&&style<=100){
				g.drawImage(rocketfly03_image,120,100,50,50,this);
				g.drawImage(rocketfly02_image,220,100,50,50,this);
				g.drawImage(rocketfly01_image,320,100,50,50,this);
				
			}
			if(style>100&&style<=120){
				g.drawImage(rocketfly03_image,220,100,50,50,this);
				g.drawImage(rocketfly02_image,320,100,50,50,this);
			}
			if(style>120&&style<=140){
				g.drawImage(rocketfly01_image,320,100,50,50,this);
			}
			if(style>140&&style<=380){
				
			}
		}
		else if(flag==1){
			//调用飞机的绘制方法
			mp.drawMyPlane(g);
			//绘制敌机
			for(int i = 0;i < ep_list.size();i++){
				EnemyPlane enplane =ep_list.get(i);
				if(enplane.isLife==true){
					enplane.drawEnPlane(g);
					enplane.epEpIntersects();
					if(flag==2){
						enplane.isLife=false;
						ep_list.removeAll(ep_list);
					}
				}else{
					ep_list.remove(enplane);
				}
			}
			//绘制敌人战机
			for(int i = 0;i < ecp_list.size();i++){
				EnemyCombatPlane enplanet =ecp_list.get(i);
				if(enplanet.isLife==true){
					enplanet.drawEnPlaneT(g);
					enplanet.eptEpIntersects();
					if(flag==2){
						enplanet.isLife=false;
						ecp_list.removeAll(ecp_list);
					}
				}else{
					ecp_list.remove(enplanet);
				}
			}
			//绘制医疗包
			for(int i = 0;i < md_list.size();i++){
				Medical medic =md_list.get(i);
				if(medic.isLife==true){
					medic.drawMedical(g);
					medic.mdMpIntersects();
					if(flag==2){
						medic.isLife=false;
						md_list.removeAll(md_list);
					}
				}else{
					md_list.remove(medic);
				}
			}
			//绘制子弹升级包
			for(int i = 0;i < buup_list.size();i++){
				BulletUpdate buup =buup_list.get(i);
				if(buup.isLife==true){
					buup.drawBulletUpdate(g);
					buup.bulletUpMpIntersects();
					if(flag==2){
						buup.isLife=false;
						buup_list.removeAll(buup_list);
					}
				}else{
					buup_list.remove(buup);
				}
			}
			//绘制飞镖子弹升级包
			for(int i = 0;i < dbup_list.size();i++){
				DartsFireUpdate dbup =dbup_list.get(i);
				if(dbup.isLife==true){
					dbup.drawDartsFireUpdate(g);
					dbup.dbUpMpIntersects();
					if(flag==2){
						dbup.isLife=false;
						dbup_list.removeAll(dbup_list);
					}
				}else{
					dbup_list.remove(buup);
				}
			}
			//绘制超级武器包
			for(int i = 0;i < spfbox_list.size();i++){
				SuperFireBox spfbox =spfbox_list.get(i);
				if(spfbox.isLife==true){
					spfbox.drawSpFireBox(g);
					spfbox.sfbMpIntersects();
					if(flag==2){
						spfbox.isLife=false;
						spfbox_list.removeAll(spfbox_list);
					}
				}else{
					spfbox_list.remove(spfbox);
				}
			}
			//绘制子弹
			for(int i = 0;i < bl_list.size();i++){
				Bullet bullet =bl_list.get(i);
				if(bullet.isLife==true){
					bullet.drawBullet(g);
					bullet.buEpIntersects(ep_list);
					bullet.buEcpIntersects(ecp_list);
					bullet.buBossIntersects(boss_list);
					if(flag==2){
						bullet.isLife=false;
						bl_list.removeAll(bl_list);
					}
				}else{
					bl_list.remove(bullet);
				}
			}
			//绘制飞镖子弹
			for(int i = 0;i < dbl_list.size();i++){
				DartsBullet dbullet =dbl_list.get(i);
				if(dbullet.isLife==true){
					dbullet.drawBullet(g);
					dbullet.dbuEpIntersects(ep_list);
					dbullet.dbuEcpIntersects(ecp_list);
					if(flag==2){
						dbullet.isLife=false;
						dbl_list.removeAll(dbl_list);
					}
				}else{
					dbl_list.remove(dbullet);
				}
			}
			//绘制敌人子弹
			for(int i = 0;i < ebl_list.size();i++){
				EnemyBullet enemybullet =ebl_list.get(i);
				if(enemybullet.isLife==true){
					enemybullet.drawEnemyBullet(g);
					enemybullet.eBuMpIntersects();
					if(flag==2){
						enemybullet.isLife=false;
						ebl_list.removeAll(ebl_list);
					}
				}else{
					ebl_list.remove(enemybullet);
				}
			}
			//绘制超级子弹
			for(int i = 0;i < spf_list.size();i++){
				SuperFire spf =spf_list.get(i);
				if(spf.isLife==true){
					spf.drawSuperFire(g);;
					spf.subuEpIntersects(ep_list);
					spf.subuEcpIntersects(ecp_list);
					spf.subuEbuIntersects(ebl_list);
					spf.subuBossIntersects(boss_list);
					spf.subuBossBuIntersects(bossBullet_list);
					if(flag==2){
						spf.isLife=false;
						spf_list.removeAll(spf_list);
					}
				}else{
					spf_list.remove(spf);
				}
			}
			
			//绘制BOSS
			for(int i = 0;i < boss_list.size();i++){
				Boss boss =boss_list.get(i);
				if(boss.isLife==true&&boss.health>0){
					boss.drawBoss(g);
					boss.bossMpIntersects();
					if(flag==2){
						boss.isLife=false;
						boss_list.removeAll(boss_list);
					}
				}else{
					boss_list.remove(boss);
				}
			}
			
			//绘制BOSS子弹
			for(int i = 0;i < bossBullet_list.size();i++){
				BossBullet bossbullet =bossBullet_list.get(i);
				if(bossbullet.isLife==true){
					bossbullet.drawBossBullet(g);
					bossbullet.bossBuMpIntersects();
					if(flag==2){
						bossbullet.isLife=false;
						bossBullet_list.removeAll(bossBullet_list);
					}
				}else{
					bossBullet_list.remove(bossbullet);
				}
			}
		
			//绘制爆炸效果
			for(int i=0;i<boom_list.size();i++){
				Boom boom=boom_list.get(i);
				if(boom.isLife==true){
					boom.drawBoom(g);
					if(flag==2){
						boom.isLife=false;
						boom_list.removeAll(boom_list);
					}
				}else{
					boom_list.remove(boom);
				}
			}
			
			//绘制分数阴影
			g.setColor(Color.black);
			g.setFont(new Font("黑体",Font.PLAIN,24));
			g.drawString("分数："+score,32,62);
			g.drawString("生命值：",182,62);
			//绘制分数
			g.setColor(Color.white);
			g.setFont(new Font("黑体",Font.PLAIN,24));
			g.drawString("分数："+score,30,60);
			g.drawString("生命值：",180,60);
			//绘制空心血条方框
			g.setColor(new Color(190,195,199));
			g.drawRect(275,42,101,18);
			//绘制实心血条方框
			g.setColor(new Color(234,75,53));
			g.fillRect(276,43,mp.mp_health,17);
			//绘制血条数值阴影
			g.setColor(Color.black);
			g.setFont(new Font("黑体",Font.PLAIN,16));
			g.drawString(""+mp.mp_health, 317, 59);
			//绘制血条数值
			g.setColor(Color.white);
			g.setFont(new Font("黑体",Font.PLAIN,16));
			g.drawString(""+mp.mp_health, 315, 57);
			//绘制超级武器图标
			g.drawImage(superfirebox_image,25,560,30,30,this);
			//绘制超级武器数量阴影
			g.setColor(Color.black);
			g.setFont(new Font("黑体",Font.PLAIN,24));
			g.drawString("："+mp.superfire_num,59,585);
			//绘制超级武器数量
			g.setColor(Color.white);
			g.setFont(new Font("黑体",Font.PLAIN,24));
			g.drawString("："+mp.superfire_num,57,583);
			if(mp.mp_health<=30){
				//绘制生命值过低提示阴影
				g.setColor(Color.black);
				g.setFont(new Font("黑体",Font.PLAIN,20));
				g.drawString("警告：生命值过低！", 197, 84);
				//绘制生命值过低提示
				g.setColor(Color.red);
				g.setFont(new Font("黑体",Font.PLAIN,20));
				g.drawString("警告：生命值过低！", 195, 82);
			}
		}else if(flag == 2){
			time = System.currentTimeMillis();
			ept_time = System.currentTimeMillis();
			boss_time = System.currentTimeMillis();
			bullet_time = System.currentTimeMillis();
			medical_time = System.currentTimeMillis();
			bossbullet_time = System.currentTimeMillis();
			supefirebox_time = System.currentTimeMillis();
			enemybullet_time = System.currentTimeMillis();
			bulletupdate_time = System.currentTimeMillis();
			dartsfireupdate_time = System.currentTimeMillis();
			//GameOver图片
			g.drawImage(end_image,30,100,350,140,this);
			//绘制结束时分数显示阴影
			g.setColor(Color.black);
			g.setFont(new Font("黑体",Font.PLAIN,36));
			g.drawString(""+score+"分",147,332);
			//绘制结束时分数显示
			g.setColor(Color.white);
			g.setFont(new Font("黑体",Font.PLAIN,36));
			g.drawString(""+score+"分",145,330);
			//绘制结束时评价显示阴影
			g.setColor(Color.black);
			g.setFont(new Font("黑体",Font.BOLD,40));
			g.drawString("评价：",77,427);
			//绘制结束时评价显示
			g.setColor(Color.white);
			g.setFont(new Font("黑体",Font.BOLD,40));
			g.drawString("评价：",75,425);
			if (score<1000) {
				//F
				g.setColor(Color.black);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("F",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("F",245,435);
			}else if (score>=1000 && score<2000) {
				//E
				g.setColor(Color.black);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("E",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("E",245,435);
			}else if (score>=2000 && score<5000) {
				//D
				g.setColor(Color.black);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("D",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("D",245,435);
			}else if (score>=5000 && score<10000) {
				//C
				g.setColor(Color.black);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("C",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("C",245,435);
			}else if (score>=10000 && score<20000) {
				//B
				g.setColor(Color.black);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("B",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("B",245,435);
			}else if (score>=20000 && score<50000) {
				//A
				g.setColor(Color.black);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("A",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("A",245,435);
			}else if (score>=50000 && score<100000) {
				//S
				g.setColor(Color.black);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("S",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("S",245,435);
			}else if (score>=100000 && score<200000) {
				//SS
				g.setColor(Color.black);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("SS",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("SS",245,435);
			}else if (score>=200000 && score<500000) {
				//SSS
				g.setColor(Color.black);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("SSS",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("黑体",Font.BOLD,80));
				g.drawString("SSS",245,435);
			}else if (score>=500000 && score<1000000) {
				//提示
				g.setColor(Color.black);
				g.setFont(new Font("黑体",Font.BOLD,24));
				g.drawString("你确定没作弊？",207,422);
				g.setColor(Color.white);
				g.setFont(new Font("黑体",Font.BOLD,24));
				g.drawString("你确定没作弊？",205,420);
			}
			//GameOver提示返回
			g.drawImage(end_tips_image,30,470,340,80,this);
		}
	}
	//用于制作双缓冲
	@Override
	public void update(Graphics g) {
		 if(win_image == null){
			 //创建一个画布
			 win_image = createImage(400,600);
			 //画笔:在新的画布上进行绘画
			 Graphics gp = win_image.getGraphics();
			 //将元素画到新的画布上
			 paint(gp);
			 //将画布绘制到窗体上
			 g.drawImage(win_image, 0, 0, this);
			 
			 //清空画布
			 win_image = null;
		 }
	}
	
	//线程
	class myThread extends Thread{
		@Override
		public void run() {
			super.run();
			//目的：让绘制方法不停绘制
			for(;;){
				if(flag==1){
					if(System.currentTimeMillis()-time>=600){
						createEnemyPlane();
						time = System.currentTimeMillis();
					}
					if(System.currentTimeMillis()-ept_time>=800){
						createEnemyPlaneT();
						ept_time = System.currentTimeMillis();
					}
					if(System.currentTimeMillis()-medical_time>=18800){
						createMedical();
						medical_time = System.currentTimeMillis();
					}
					if(System.currentTimeMillis()-bulletupdate_time>=27300){
						createBulletUpdate();
						bulletupdate_time = System.currentTimeMillis();
					}
					if(System.currentTimeMillis()-dartsfireupdate_time>=50700){
						createDartsFireUpdate();
						dartsfireupdate_time = System.currentTimeMillis();
					}
					if(System.currentTimeMillis()-supefirebox_time>=97300){
						createSpFireBox();
						supefirebox_time = System.currentTimeMillis();
					}
					if(System.currentTimeMillis()-boss_time>=71920){
						createBoss();
						boss_time = System.currentTimeMillis();
					}
					repaint();
					try {
						//每隔10ms休息一次，缓解资源问题
						sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else if(flag == 0){
					style+=2;
					if(style>380){
						style=0;
					}
					repaint();
					try {
						//每隔10ms休息一次，缓解资源问题
						sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else if(flag == 2){
					repaint();
					try {
						//每隔10ms休息一次，缓解资源问题
						sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}