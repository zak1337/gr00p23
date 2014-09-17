package Level;

import gr00p23.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sounds.SoundEffect;
import Enitities.Asteroid;
import Enitities.CatNip;
import Enitities.Doge;
import Enitities.Entity;
import Enitities.Fish;
import Enitities.Kitten;
import Enitities.Shiba;
import Graphics.Sprite;

public class Level {
	protected int width, height;
	protected int[] timesInt;
	protected int[] tiles;
	public Kitten kitten;
	private List<Entity> entities = new ArrayList<Entity>();
	public int killCount = 0;
	public int kill2Count = 30;
	private int killTimer = 0;
	private int killTimerCD = 60;
	private int lastKillCount = 0;
	private boolean killTiming = false;
	public boolean shiba = false;
	Shiba shibaD;
	
	public Level(){

	}
	
	public void add(Entity e){
		e.init(this);
		entities.add(e);
	}
	
	public void update(){
		for (int i = 0; i < entities.size(); i ++){
			entities.get(i).update();
		
			entities.get(i).chaserAI(kitten.y);
			
			if(kitten.isVulnerable()){
				if(entities.get(i).mobCollision(kitten.x, kitten.y)){
					kitten.isHit(entities.get(i).damage);
					entities.get(i).hp -=10;
				}
			}
		}
		remove();
		killTimer++;
		if(killTimer > killTimerCD){
			killTimer = 0;
			killingSprees();
			lastKillCount = killCount;
		}
		if(killCount < kill2Count){
			generateEntities();
		}
		else if(!shiba){
			shibaD = new Shiba(1000,360,this);
			add(shibaD);
			shiba = true;
		}
		if(killCount >= kill2Count){
			if(shibaD.isRemoved()){
				generateEntities();
			}
		}
	}
	
	private void killingSprees(){
		int deltaKC = killCount-  lastKillCount;
		System.out.println(deltaKC);
		if (deltaKC ==2){
			SoundEffect.DOUBLEKILL.play();
		}
		if (deltaKC ==3){
			SoundEffect.TRIPLEKILL.play();
		}
		if (deltaKC ==4){
			SoundEffect.QUADRAKILL.play();
		}
		if (deltaKC ==5){
			SoundEffect.KILLINGSPREE.play();
		}
		if (deltaKC ==6){
			SoundEffect.HOTDOG.play();
		}
		if (deltaKC >=7){
			SoundEffect.DOGSLAYER.play();
		}
	}
	
	public void render(Screen screen){
//		screen.setOffset(xScroll, yScroll);
//		int x0 = xScroll >> 4;
//		int x1 = (xScroll + screen.width + 16) >> 4;
//		int y0 = yScroll >> 4;
//		int y1 = (yScroll + screen.height + 16) >> 4;
	//	screen.renderBackground(0, 0, Sprite.background);
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
	}
	
	public void remove(){
		for (int i = 0; i < entities.size(); i++){
			if (entities.get(i).isRemoved()){
				entities.remove(i);
				
			}
		}
	}

	public boolean enemyCollision(int ix, int iy) {
		boolean result = false;
		for (int i = 0; i < entities.size(); i++){
			if (entities.get(i).x==ix && entities.get(i).y==iy){
				result = true;
			}
		}
		return result;
	}

	private void generateEntities(){
		int randomInt;
		Random Dice = new Random();
		randomInt = Dice.nextInt(840);
		if(randomInt <= killCount+2){
			spawnDoge();
		}
		randomInt = Dice.nextInt(540);
		if(randomInt == 2){
			spawnFish();
		}
		randomInt = Dice.nextInt(360);
		if(randomInt == 3){
			spawnAsteroid();
		}
		randomInt = Dice.nextInt(1600);
		if(randomInt == 5){
			spawnCatnip();
		}
	}
	
	public void spawnCatnip(){
		Random r = new Random();
		int yl = r.nextInt(720/2-32);
		entities.add(new CatNip(640,yl,this));
	}
	
	private void spawnDoge(){
		Random r = new Random();
		int yl = r.nextInt(720/2-32);
		entities.add(new Doge(640,yl,this));
	}
	
	private void spawnFish(){
		Random r = new Random();
		int yl = r.nextInt(720/2-32);
		entities.add(new Fish(640,yl,this));
	}
	
	public void spawnAsteroid(){
		Random r = new Random();
		int yl = r.nextInt(720/2-32);
		entities.add(new Asteroid(640,yl,this));
	}
	
	public void hitDetect(int x, int y) {
		for (int i = 0; i < entities.size(); i ++){
			boolean hit = false;
			if(x < entities.get(i).x){
				for (int iy = 9; iy < 19; iy++){
					for(int ey = 0; ey < 32; ey++){
						if(entities.get(i).y+ey == y+iy){
							hit = true;
						}
					}
				}
			}
			if (hit){
				entities.get(i).isHit();
			}
		}
		
	}

	public void removeAll() {
		for(int i = 0; i < entities.size();i++){
			entities.get(i).remove();
		}
		
	}
}
