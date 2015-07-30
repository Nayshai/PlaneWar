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
 * ���ڣ�2015��7��3��
 * �汾��Beta
 * �汾�ţ�1.0
 * ���ߣ������ Hansen��Lau
 * 
 */

/*
 * 
 * Frame:��Ϊ����Ӧ�ó������
 * �̳У�A��̳�B�࣬��ôA��;�����B�������������Ժͷ���
 * 
 */

//��Ϊ�����Ľ���
@SuppressWarnings("serial")
public class GameStart extends Frame{
	
	/********ͼƬ����********/
	Toolkit tk = Toolkit.getDefaultToolkit();
	//�õ��ɻ�ͼƬ
	Image plane_image = tk.getImage(GameStart.class.getResource("/images/Plane.png"));
	//�õ��ɻ��˷������ͼƬ
	Image plane_Up = tk.getImage(GameStart.class.getResource("/images/Plane/Plane_Up.png"));
	Image plane_Down = tk.getImage(GameStart.class.getResource("/images/Plane/Plane_Down.png"));
	Image plane_Left = tk.getImage(GameStart.class.getResource("/images/Plane/Plane_Left.png"));
	Image plane_Right = tk.getImage(GameStart.class.getResource("/images/Plane/Plane_Right.png"));
	Image plane_LeftUp = tk.getImage(GameStart.class.getResource("/images/Plane/Plane_LeftUp.png"));
	Image plane_LeftDown = tk.getImage(GameStart.class.getResource("/images/Plane/Plane_LeftDown.png"));
	Image plane_RightUp = tk.getImage(GameStart.class.getResource("/images/Plane/Plane_RightUp.png"));
	Image plane_RightDown = tk.getImage(GameStart.class.getResource("/images/Plane/Plane_RightDown.png"));
	//�õ�����ͼƬ
	Image background_image = tk.getImage(GameStart.class.getResource("/images/Background.png"));
	//�л�ͼƬ
	Image enplane_image = tk.getImage(GameStart.class.getResource("/images/Enemy01.png"));
	//�о�ս��ͼƬ
	Image encbtplane_image01 = tk.getImage(GameStart.class.getResource("/images/Enemy02.png"));
	Image encbtplane_image02 = tk.getImage(GameStart.class.getResource("/images/Enemy03.png"));
	Image encbtplane_image03= tk.getImage(GameStart.class.getResource("/images/Enemy04.png"));
	Image encbtplane_image04= tk.getImage(GameStart.class.getResource("/images/Enemy05.png"));
	//�о�ս��ͼƬ����
	Image encbtplane_image[] ={
			encbtplane_image01,
			encbtplane_image02,
			encbtplane_image03,
			encbtplane_image04
	}; 
	//BossͼƬ
	Image boss_image = tk.getImage(GameStart.class.getResource("/images/Shit.png"));
	//boss�ӵ�ͼƬ
	Image[] bossbullet_image={
			tk.getImage(GameStart.class.getResource("/images/ShitBullet01.png")),
			tk.getImage(GameStart.class.getResource("/images/ShitBullet02.png"))};
	//�ӵ�ͼƬ
	Image bullet_image = tk.getImage(GameStart.class.getResource("/images/Bullet01.png"));
	Image bullet_image_tmp = tk.getImage(GameStart.class.getResource("/images/Bullet01.png"));
	Image doublebullet_image = tk.getImage(GameStart.class.getResource("/images/Bullet03.png"));
	Image darts_image = tk.getImage(GameStart.class.getResource("/images/Darts.png"));
	Image enemybullet_image = tk.getImage(GameStart.class.getResource("/images/Bullet02.png"));
	Image superfire_image = tk.getImage(GameStart.class.getResource("/images/SuperWeapon.png"));
	//ҽ�ư�ͼƬ
	Image medical_image = tk.getImage(GameStart.class.getResource("/images/Medical.png"));
	//�ӵ�������ͼƬ
	Image bulletupdate_image = tk.getImage(GameStart.class.getResource("/images/BulletUpdate.png"));
	//�����ӵ�������ͼƬ
	Image dartsfireupdate_image = tk.getImage(GameStart.class.getResource("/images/DartsUpdate.png"));
	//����������ͼƬ
	Image superfirebox_image = tk.getImage(GameStart.class.getResource("/images/SuperFireBox.png"));
	//��ʼ����
	Image start_image = tk.getImage(GameStart.class.getResource("/images/Title.png"));
	Image start_tips_image = tk.getImage(GameStart.class.getResource("/images/Title_Tips.png"));
	Image rocketfly01_image = tk.getImage(GameStart.class.getResource("/images/RocketFly01.png"));
	Image rocketfly02_image = tk.getImage(GameStart.class.getResource("/images/RocketFly02.png"));
	Image rocketfly03_image = tk.getImage(GameStart.class.getResource("/images/RocketFly03.png"));
	Image rocket_image = tk.getImage(GameStart.class.getResource("/images/Rocket.png"));
	//��������
	Image end_image = tk.getImage(GameStart.class.getResource("/images/GameOver.png"));
	Image end_tips_image = tk.getImage(GameStart.class.getResource("/images/GameOver_Tips.png"));
	
	/********��������********/
	//����ɻ�����
	//int x=170,y=410;
	//����һ���հ׵Ļ���
	Image win_image = null;
	int score=0;
	//��ʼ����
	int style=0;
	int flag=0;
	
	/********��������********/
	//�ɻ�
	MyPlane mp = new MyPlane(160, 450, 80, 70, 100, 0, 0, 10, true, this);
	//����
	Background bg = new Background(0, 0, 400, 600,this);
	//ʵ����ҽ�ư�
	Medical md = null;
	//ʵ�����ӵ�������
	BulletUpdate buup = null;
	//ʵ���������ӵ�������
	DartsFireUpdate dbup = null;
	//ʵ��������������
	SuperFireBox spfbox = null;
	//ʵ�����л�
	EnemyPlane ep = null;
	//ʵ��������ս��
	EnemyCombatPlane ecp = null;
	//ʵ����BOSS
	Boss boss = null;
	//ʵ�����л�����
	List<EnemyPlane> ep_list = new ArrayList<EnemyPlane>();
	//ʵ��������ս������
	List<EnemyCombatPlane> ecp_list = new ArrayList<EnemyCombatPlane>();
	//ʵ�����ӵ�����
	List<Bullet> bl_list = new ArrayList<Bullet>();
	//ʵ���������ӵ�����
	List<DartsBullet> dbl_list = new ArrayList<DartsBullet>();
	//ʵ�����о��ӵ�����
	List<EnemyBullet> ebl_list = new ArrayList<EnemyBullet>();
	//ʵ����������������
	List<SuperFire> spf_list = new ArrayList<SuperFire>();
	//ʵ����ҽ�ư�����
	List<Medical> md_list = new ArrayList<Medical>();
	//ʵ�����ӵ�����������
	List<BulletUpdate> buup_list = new ArrayList<BulletUpdate>();
	//ʵ���������ӵ�����������
	List<DartsFireUpdate> dbup_list = new ArrayList<DartsFireUpdate>();
	//ʵ������������������
	List<SuperFireBox> spfbox_list = new ArrayList<SuperFireBox>();
	//ʵ����BOSS����
	List<Boss> boss_list=new ArrayList<Boss>();
	//ʵ����BOSS�ӵ�����
	List<BossBullet> bossBullet_list=new ArrayList<BossBullet>();
	//ʵ����һ�������
	Random rd = new Random();
	//�����õ���ʱ��
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
	//ʵ��������
	GameSound gso = new GameSound();
	//ʵ������ը����
	List<Boom> boom_list=new ArrayList<Boom>();
	//�����һ�α�ը
	{boom_list.add(new Boom(0, 0, 30, 30, true, this));}
	
	
	public GameStart(){
		//����Ĵ�С
		this.setSize(400,600);
		//����
		this.setTitle("PlaneFantasy");
		//��ֹ�ı䴰���С
		this.setResizable(false);
		//����
		this.setLocationRelativeTo(null);
		//����ļ���
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		//���̼���
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
		
		//�̵߳Ŀ���
		myThread mt = new myThread();
		mt.start();
		//�Ƿ���ʾ
		this.setVisible(true);
		gso.playBgSound("./music/background_music.mp3");
	}
	
	//�����л�
	public void createEnemyPlane() {
		int x=rd.nextInt(350);
		ep = new EnemyPlane(x, -80, 50, 50, true, this);
		ep_list.add(ep);
	}
	
	//��������ս��
	public void createEnemyPlaneT() {
		int x=rd.nextInt(350);
		ecp = new EnemyCombatPlane(x, -80, 70, 70, true, this);
		ecp_list.add(ecp);
	}
	
	//����ҽ�ư�
	public void createMedical() {
		int x=rd.nextInt(350);
		md = new Medical(x, -80, 50, 60, true, this);
		md_list.add(md);
	}
	
	//�����ӵ�������
	public void createBulletUpdate() {
		int x=rd.nextInt(350);
		buup = new BulletUpdate(x, -80, 50, 50, true, this);
		buup_list.add(buup);
	}
	
	//���������ӵ�������
	public void createDartsFireUpdate() {
		int x=rd.nextInt(350);
		dbup = new DartsFireUpdate(x, -80, 50, 50, true, this);
		dbup_list.add(dbup);
	}
	
	//��������������
	public void createSpFireBox() {
		int x=rd.nextInt(350);
		spfbox = new SuperFireBox(x, -80, 50, 50, true, this);
		spfbox_list.add(spfbox);
	}
	
	//����BOSS
	public void createBoss() {
		gso.playSound("./music/Boss_Comming.mp3");
		boss = new Boss(120, 0, 80, 80, true, 100 ,this);
		boss_list.add(boss);
	}
	
	public static void main(String[] args) {
		//ʵ����
		@SuppressWarnings("unused")
		GameStart gs = new GameStart();
	}
	
	//���Ʒ���--���������ʼ�������غ��ٴ���ʾ��
	//���Ʒɻ�
	@Override
	public void paint(Graphics g) {
		//���Ʊ���
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
			//���÷ɻ��Ļ��Ʒ���
			mp.drawMyPlane(g);
			//���Ƶл�
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
			//���Ƶ���ս��
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
			//����ҽ�ư�
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
			//�����ӵ�������
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
			//���Ʒ����ӵ�������
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
			//���Ƴ���������
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
			//�����ӵ�
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
			//���Ʒ����ӵ�
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
			//���Ƶ����ӵ�
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
			//���Ƴ����ӵ�
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
			
			//����BOSS
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
			
			//����BOSS�ӵ�
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
		
			//���Ʊ�ըЧ��
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
			
			//���Ʒ�����Ӱ
			g.setColor(Color.black);
			g.setFont(new Font("����",Font.PLAIN,24));
			g.drawString("������"+score,32,62);
			g.drawString("����ֵ��",182,62);
			//���Ʒ���
			g.setColor(Color.white);
			g.setFont(new Font("����",Font.PLAIN,24));
			g.drawString("������"+score,30,60);
			g.drawString("����ֵ��",180,60);
			//���ƿ���Ѫ������
			g.setColor(new Color(190,195,199));
			g.drawRect(275,42,101,18);
			//����ʵ��Ѫ������
			g.setColor(new Color(234,75,53));
			g.fillRect(276,43,mp.mp_health,17);
			//����Ѫ����ֵ��Ӱ
			g.setColor(Color.black);
			g.setFont(new Font("����",Font.PLAIN,16));
			g.drawString(""+mp.mp_health, 317, 59);
			//����Ѫ����ֵ
			g.setColor(Color.white);
			g.setFont(new Font("����",Font.PLAIN,16));
			g.drawString(""+mp.mp_health, 315, 57);
			//���Ƴ�������ͼ��
			g.drawImage(superfirebox_image,25,560,30,30,this);
			//���Ƴ�������������Ӱ
			g.setColor(Color.black);
			g.setFont(new Font("����",Font.PLAIN,24));
			g.drawString("��"+mp.superfire_num,59,585);
			//���Ƴ�����������
			g.setColor(Color.white);
			g.setFont(new Font("����",Font.PLAIN,24));
			g.drawString("��"+mp.superfire_num,57,583);
			if(mp.mp_health<=30){
				//��������ֵ������ʾ��Ӱ
				g.setColor(Color.black);
				g.setFont(new Font("����",Font.PLAIN,20));
				g.drawString("���棺����ֵ���ͣ�", 197, 84);
				//��������ֵ������ʾ
				g.setColor(Color.red);
				g.setFont(new Font("����",Font.PLAIN,20));
				g.drawString("���棺����ֵ���ͣ�", 195, 82);
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
			//GameOverͼƬ
			g.drawImage(end_image,30,100,350,140,this);
			//���ƽ���ʱ������ʾ��Ӱ
			g.setColor(Color.black);
			g.setFont(new Font("����",Font.PLAIN,36));
			g.drawString(""+score+"��",147,332);
			//���ƽ���ʱ������ʾ
			g.setColor(Color.white);
			g.setFont(new Font("����",Font.PLAIN,36));
			g.drawString(""+score+"��",145,330);
			//���ƽ���ʱ������ʾ��Ӱ
			g.setColor(Color.black);
			g.setFont(new Font("����",Font.BOLD,40));
			g.drawString("���ۣ�",77,427);
			//���ƽ���ʱ������ʾ
			g.setColor(Color.white);
			g.setFont(new Font("����",Font.BOLD,40));
			g.drawString("���ۣ�",75,425);
			if (score<1000) {
				//F
				g.setColor(Color.black);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("F",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("F",245,435);
			}else if (score>=1000 && score<2000) {
				//E
				g.setColor(Color.black);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("E",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("E",245,435);
			}else if (score>=2000 && score<5000) {
				//D
				g.setColor(Color.black);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("D",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("D",245,435);
			}else if (score>=5000 && score<10000) {
				//C
				g.setColor(Color.black);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("C",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("C",245,435);
			}else if (score>=10000 && score<20000) {
				//B
				g.setColor(Color.black);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("B",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("B",245,435);
			}else if (score>=20000 && score<50000) {
				//A
				g.setColor(Color.black);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("A",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("A",245,435);
			}else if (score>=50000 && score<100000) {
				//S
				g.setColor(Color.black);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("S",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("S",245,435);
			}else if (score>=100000 && score<200000) {
				//SS
				g.setColor(Color.black);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("SS",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("SS",245,435);
			}else if (score>=200000 && score<500000) {
				//SSS
				g.setColor(Color.black);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("SSS",247,437);
				g.setColor(Color.white);
				g.setFont(new Font("����",Font.BOLD,80));
				g.drawString("SSS",245,435);
			}else if (score>=500000 && score<1000000) {
				//��ʾ
				g.setColor(Color.black);
				g.setFont(new Font("����",Font.BOLD,24));
				g.drawString("��ȷ��û���ף�",207,422);
				g.setColor(Color.white);
				g.setFont(new Font("����",Font.BOLD,24));
				g.drawString("��ȷ��û���ף�",205,420);
			}
			//GameOver��ʾ����
			g.drawImage(end_tips_image,30,470,340,80,this);
		}
	}
	//��������˫����
	@Override
	public void update(Graphics g) {
		 if(win_image == null){
			 //����һ������
			 win_image = createImage(400,600);
			 //����:���µĻ����Ͻ��л滭
			 Graphics gp = win_image.getGraphics();
			 //��Ԫ�ػ����µĻ�����
			 paint(gp);
			 //���������Ƶ�������
			 g.drawImage(win_image, 0, 0, this);
			 
			 //��ջ���
			 win_image = null;
		 }
	}
	
	//�߳�
	class myThread extends Thread{
		@Override
		public void run() {
			super.run();
			//Ŀ�ģ��û��Ʒ�����ͣ����
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
						//ÿ��10ms��Ϣһ�Σ�������Դ����
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
						//ÿ��10ms��Ϣһ�Σ�������Դ����
						sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else if(flag == 2){
					repaint();
					try {
						//ÿ��10ms��Ϣһ�Σ�������Դ����
						sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}