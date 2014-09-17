package gr00p23;

import Graphics.Sprite;


public class SleepBar {
	private int xa, ya;
	double maxS, sleep;
	Sprite sprite;
	
	public SleepBar (int x, int y, int maxSleep){
		maxS = maxSleep;
		sleep = maxS;
		xa = x; //We need coordinates for this.
		ya = y; //Still need coordinates for this.
		sprite = Sprite.sleepbar;
	}
	
	public void update(int newSleep) {
		sleep = newSleep;
	}
	
	public void render(Screen screen){
		screen.renderSprite(xa, ya, sprite, false);
		for (int i = 0; i < (((sleep / maxS) * 100)); i++) {
			screen.renderSprite(xa + 1 + i, ya + 2, Sprite.sleepFill, false);
		}
	}
	

	
}
