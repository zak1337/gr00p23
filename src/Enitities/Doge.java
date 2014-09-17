package Enitities;

import java.util.Random;

import sounds.SoundEffect;
import gr00p23.Screen;
import Graphics.Sprite;
import Level.Level;

public class Doge extends Mob{
	public int hp;
	Sprite sprite = Sprite.kittenFloat;
	
	public Doge(int x, int y, Level level){
		this.x = x;
		this.y = y;
		this.hp = 80;
		this.level = level;
		damage = 10;
		Random r = new Random();
		int rand = r.nextInt(4);
		if (rand == 0){
			sprite = Sprite.puppy1;
		}
		if (rand == 1){
			sprite = Sprite.puppy2;
		}
		if (rand == 2){
			sprite = Sprite.puppy3;
		}
		if (rand == 3){
			sprite = Sprite.puppy4;
		}
	}
	
	public void update(){
		int spd = 1;
		if (level.killCount > 10){
			spd = 2;
		}
		if (level.killCount > 20){
			spd = 3;
		}
		if(hp <= 1){
			level.killCount++;
			remove();
		}
		x-=spd;
		if(x<-33){
			remove();
		}
		
	}
	
	public void chaserAIUp(){
		y--;
	}
	
	public void chaserAIDown(){
		y++;
	}
	
	public void chaserAI(int ky){
		if (y < ky){
			chaserAIDown();
		}
		if (y > ky){
			chaserAIUp();
		}
	}
	
	public void isHit(){
		hp--;
	}
	public void remove(){
		if(x>-33){
			SoundEffect.ENEMYDEATHD.play();
		}
		removed = true;
	}
	
	public void render(Screen screen){
		screen.renderSprite(x,y,sprite,false);
	}
}
