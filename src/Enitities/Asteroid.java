package Enitities;

import java.util.Random;

import sounds.SoundEffect;
import gr00p23.Screen;
import Graphics.Sprite;
import Level.Level;

public class Asteroid extends Mob{
	public int hp;
	Sprite sprite = Sprite.kittenFloat;
	
	public Asteroid(int x, int y, Level level){
		this.x = x;
		this.y = y;
		this.hp = 115615;
		this.level = level;
		damage = 10;
		Random r = new Random();
		int rand = r.nextInt(4);
		if (rand == 0){
			sprite = Sprite.asteroid1;
		}
		if (rand == 1){
			sprite = Sprite.asteroid2;
		}
		if (rand == 2){
			sprite = Sprite.asteroid3;
		}
		if (rand == 3){
			sprite = Sprite.asteroid4;
		}
	}
	
	public void update(){
		x--;
		if(x<-33){
			remove();
		}
	}
	
	public void chaserAIUp(){
	}
	
	public void chaserAIDown(){
	}
	
	public void chaserAI(int ky){
	}
	
	public void isHit(){
	}
	public void remove(){
		removed = true;
	}
	
	public void render(Screen screen){
		screen.renderSprite(x,y,sprite,false);
	}
}
