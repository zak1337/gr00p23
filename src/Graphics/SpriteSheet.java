package Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	public int[] pixels;
	public final int SPRITE_WIDTH, SPRITE_HEIGHT;
	private int width,height;
	
	public static SpriteSheet osgrr = new SpriteSheet("/Osgrr.png",96,32);
	public static SpriteSheet lazer = new SpriteSheet("/Lazer.png",700,32);
	public static SpriteSheet shibaDemon = new SpriteSheet("/ShibaDemon.png",64,64);
	public static SpriteSheet shibaDemon2 = new SpriteSheet("/ShibaDemon2.png",64,64);
	public static SpriteSheet shiba = new SpriteSheet("/Shiba.png",64,64);
	public static SpriteSheet puppies = new SpriteSheet("/Puppies.png",64,64);
	public static SpriteSheet dogeCoins = new SpriteSheet("/dogeCoins.png",64,16);
	public static SpriteSheet background = new SpriteSheet("/background.png",1940/2,720/2);
	public static SpriteSheet barFill = new SpriteSheet("/gradiant.png",1,15);
	public static SpriteSheet barFill2 = new SpriteSheet("/gradiantblue.png",1,13);
	public static SpriteSheet sleepbar = new SpriteSheet("/sleepBar.png",102,18);
	public static SpriteSheet lazerBar = new SpriteSheet("/lazerBar.png",102,15);
	public static SpriteSheet sleepScreen = new SpriteSheet("/SleepScreen.png",640,360);
	public static SpriteSheet asteroid = new SpriteSheet("/asteroid.png",64,64);
	public static SpriteSheet items = new SpriteSheet("/Fish_Catnip.png",32,64);
	public static SpriteSheet tutScreen = new SpriteSheet("/tutorial.png",642,361);
	
	public SpriteSheet(String path, int xsize, int ysize) {
		this.path = path;
		SPRITE_WIDTH = xsize;
		SPRITE_HEIGHT = ysize;
		load();
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
	private void load() {
		try {
			System.out.print("Loading: " + path + " ...  ");
			BufferedImage image = ImageIO.read(SpriteSheet.class
					.getResource(path));
			System.out.println("[ Success! ]");
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("[ Failed! ] Check the path!");
		} catch (Exception e) {
			System.err.println("[ Failed! ] Check the path!");
		}

	}

}
