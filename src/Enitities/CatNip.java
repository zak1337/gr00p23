package Enitities;

import gr00p23.Screen;
import sounds.SoundEffect;
import Graphics.Sprite;
import Level.Level;

public class CatNip extends Mob {
	public CatNip(int x, int y, Level level){
		this.x = x;
		this.y = y;
		this.level = level;
		this.sprite = Sprite.catNip;
		hp = 1;
		damage = -1;
	}
	public void remove(){
		SoundEffect.NOM.play();
		removed = true;
	}
	
	public void update(){
		x--;
		if(hp<1){
			remove();
		}
	}
	
	public void render(Screen screen){
		screen.renderSprite(x, y, sprite, false);
	}
}
