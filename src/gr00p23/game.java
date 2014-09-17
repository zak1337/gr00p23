package gr00p23;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import sounds.SoundEffect;
import Enitities.Doge;
import Enitities.Kitten;
import Graphics.Sprite;
import Input.Keyboard;
import Level.Level;
import gr00p23.Screen;

public class game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private static int scale = 2;
	private static int width = 1280 / scale;
	private static int height = 720 / scale;
	public static String title = "Osgrr";
	private boolean running = false;
	private Thread thread;
	private JFrame frame;
	private BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
			.getData();

	private Screen screen;
	private Kitten kitten;
	private Doge doge;
	private Keyboard key;
	Level level;
	private int katX = 50;
	private int katY = 180;
	private int dandy = 9600;
	private int abom = 10680;
	private int musicCounter = 0;
	private int musicggCounter = 0;
	private boolean chicken = false;
	private SleepBar sleepBar = new SleepBar(100, 2, 100);
	private LazerBar lazerBar = new LazerBar(100, 22, 100);
	private boolean started = false;
	private int score = 0;
	private double backgroundWrap = 0;
	private boolean gameover = false;
	
	public game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = new Level();
		kitten = new Kitten(katX, katY, key, level);
		doge = new Doge(500,200,level);
		level.add(doge);
		addKeyListener(key);
		level.kitten = kitten;
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0.0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + updates + " ups, " + frames
						+ " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	public void update(){
		key.update();
		if(kitten.energyBar<=0){
			gameover = true;
		}
		
		if(gameover && key.g){
			level.killCount = 0;
			level.shiba=false;
			kitten.energyBar=100;
			kitten.x=katX;
			kitten.y = katY;
			level.removeAll();
			level.add(new Doge(500,200,level));
			score = 0;
			gameover = false;
			musicCounter=0;
			started = false;
			musicggCounter = 0;
			chicken = false;
		}
		if(!started){
			if(key.one){
				level.kill2Count = 10;
			}
			if(key.two){
				level.kill2Count = 20;
			}
			if(key.three){
				level.kill2Count = 30;
			}
		}
		if(key.p&&musicCounter>0){
			started = true;
		}
			if(kitten.energyBar >= 1){
				backgroundWrap -=4.5;
				if(started){
					score++;
				kitten.update();
				level.update();
				sleepBar.update(kitten.energyBar);
				lazerBar.update(kitten.lazorBar);
				if(kitten.shooting){
					level.hitDetect(kitten.x,kitten.y);
				}
			}
				if(!level.shiba){
					if(musicCounter == 0){
						SoundEffect.MUSIC.play();
					}
					musicCounter++;
					if(musicCounter > dandy){
						musicCounter = 0;
					}
				}
				if(musicCounter != 0 && level.shiba && !chicken){
					SoundEffect.MUSIC.stop();
					musicCounter = 0;
					chicken = true;
				}
				if(level.shiba){
					if(musicCounter == 0){
						SoundEffect.BOSSMUSIC.play();
					}
					musicCounter++;
					if(musicCounter > abom){
						musicCounter = 0;
					}
				}
		}
		else{
			
			SoundEffect.BOSSMUSIC.stop();
			SoundEffect.MUSIC.stop();
			if (musicggCounter == 0){
				SoundEffect.GGMUSIC.play();
			}
			musicggCounter++;
		}
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		
		screen.renderSprite((int)backgroundWrap, 0, Sprite.background, false);
		screen.renderSprite((int)backgroundWrap+1940/2, 0, Sprite.background, false);
		if(backgroundWrap < -1940/2){
			backgroundWrap = 0;
		}
		level.render(screen);
		kitten.render(screen);
		sleepBar.render(screen);
		lazerBar.render(screen);
		if(!started){
			screen.renderBackground(Sprite.tutorial);
		}
		if(kitten.energyBar < 1){
			screen.renderBackground(Sprite.defeatScreen);
		}
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.RED);
		g.setFont(new Font("Verdana", 1, 50));
		if(started){
			g.drawString("SCORE: "+ (score+(level.killCount*1000)), 100+width/2, 50);
		}
	
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		game osgrr = new game();
		osgrr.frame.setResizable(false);
		osgrr.frame.setTitle(title);
		osgrr.frame.add(osgrr);
		osgrr.frame.pack();
		osgrr.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		osgrr.frame.setLocationRelativeTo(null);
		osgrr.frame.setVisible(true);
		osgrr.start();
		
		
	}
}
