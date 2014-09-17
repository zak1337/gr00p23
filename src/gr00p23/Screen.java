package gr00p23;

import java.util.Random;

import Graphics.Sprite;

public class Screen {
	public int width;
	public int height;
	public int[] pixels;
	public final int MAP_SIZE = 16;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	public int xOffset, yOffset;
	private Random random = new Random();
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
			tiles[0] = 0;
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != 0xffff00ff) {
				pixels[xa + ya * width] = sprite.pixels[x + y
						* sprite.getWidth()];
				}
			}
		}
	}
	
	public void renderRect(int xp, int yp, int col, int xsi, int ysi){
		for (int y = 0; y < ysi; y++){
			int ya = y + yp;
			for (int x = 0; x < xsi; x++){
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				pixels[xa+ya*width] = col;
			}
		}
	}
	
	public void renderBackground(Sprite sprite){
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != 0xffff00ff) {
				pixels[xa + ya * width] = sprite.pixels[x + y
						* sprite.getWidth()];
				}
			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
