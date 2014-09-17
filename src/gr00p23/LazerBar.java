package gr00p23;

import Graphics.Sprite;


public class LazerBar {
	private int xa, ya;
	double maxL, lazer;
	Sprite sprite;
	
	public LazerBar (int x, int y, int maxLazer){
		maxL = maxLazer;
		lazer = maxL;
		xa = x; //We need coordinates for this.
		ya = y; //Still need coordinates for this.
		sprite = Sprite.lazerbar;
	}
	
	public void update(int newLazer) {
		lazer = newLazer;
	}
	
	public void render(Screen screen){
		screen.renderSprite(xa, ya, sprite, false);
		for (int i = 0; i < (((lazer / maxL) * 100)); i++) {
			screen.renderSprite(xa + 1 + i, ya + 1, Sprite.lazerFill, false);
		}
	}
	

	
}
