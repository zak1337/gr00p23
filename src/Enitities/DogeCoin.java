package Enitities;

import gr00p23.Screen;
import Graphics.Sprite;

public class DogeCoin extends Mob{
	private int animationFrame = 0;
	
	public DogeCoin(int x, int y){
		this.x = x;
		this.y = y;
		this.sprite = Sprite.coin1;
		damage = 10;
	}
	public void update(){
		x-=2;
		animationFrame++;
		if(animationFrame > 60){
			animationFrame = 0;
		}
		if(animationFrame > 0){
			sprite = Sprite.coin1;
		}
		if(animationFrame > 15){
			sprite = Sprite.coin3;
		}
		if(animationFrame > 30){
			sprite = Sprite.coin2;
		}
		if(animationFrame > 45){
			sprite = Sprite.coin4;
		}
		if(x < -33){
			remove();
		}
	}
	public void render(Screen screen){
		screen.renderSprite(x, y, sprite, false);
	}
}
