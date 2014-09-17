package Graphics;

public class Sprite {
	private int width;
	private int height;
	private int x, y;
	public int[] pixels;
	protected SpriteSheet sheet;
	
	public static Sprite kittenFloat = new Sprite(SpriteSheet.osgrr,32,32,0,0);
	public static Sprite kittenRight = new Sprite(SpriteSheet.osgrr,32,32,1,0);
	public static Sprite kittenLeft = new Sprite(SpriteSheet.osgrr,32,32,2,0);
	public static Sprite lazer = new Sprite(SpriteSheet.lazer,700,32,0,0);
	public static Sprite shiba = new Sprite(SpriteSheet.shiba,64,64,0,0);
	public static Sprite shibaDemon = new Sprite(SpriteSheet.shibaDemon,64,64,0,0);
	public static Sprite shibaDemon2 = new Sprite(SpriteSheet.shibaDemon2,64,64,0,0);
	public static Sprite puppy1 = new Sprite(SpriteSheet.puppies,32,32,0,0);
	public static Sprite puppy2 = new Sprite(SpriteSheet.puppies,32,32,0,1);
	public static Sprite puppy3 = new Sprite(SpriteSheet.puppies,32,32,1,1);
	public static Sprite puppy4 = new Sprite(SpriteSheet.puppies,32,32,1,0);
	public static Sprite coin1 = new Sprite(SpriteSheet.dogeCoins,16,16,0,0);
	public static Sprite coin2 = new Sprite(SpriteSheet.dogeCoins,16,16,1,0);
	public static Sprite coin3 = new Sprite(SpriteSheet.dogeCoins,16,16,2,0);
	public static Sprite coin4 = new Sprite(SpriteSheet.dogeCoins,16,16,3,0);
	public static Sprite background = new Sprite(SpriteSheet.background,1940/2,720/2,0,0);
	public static Sprite lazerbar = new Sprite(SpriteSheet.lazerBar,102,15,0,0);
	public static Sprite sleepbar = new Sprite(SpriteSheet.sleepbar,102,18,0,0);
	public static Sprite sleepFill = new Sprite(SpriteSheet.barFill,1,15,0,0);
	public static Sprite lazerFill = new Sprite(SpriteSheet.barFill2,1,13,0,0);
	public static Sprite defeatScreen = new Sprite(SpriteSheet.sleepScreen,640,360,0,0);
	public static Sprite asteroid1 = new Sprite(SpriteSheet.asteroid,32,32,0,0);
	public static Sprite asteroid2 = new Sprite(SpriteSheet.asteroid,32,32,0,1);
	public static Sprite asteroid3 = new Sprite(SpriteSheet.asteroid,32,32,1,0);
	public static Sprite asteroid4 = new Sprite(SpriteSheet.asteroid,32,32,1,1);
	public static Sprite fish = new Sprite(SpriteSheet.items,32,32,0,0);
	public static Sprite catNip = new Sprite(SpriteSheet.items,32,32,0,1);
	public static Sprite tutorial = new Sprite(SpriteSheet.tutScreen,640,360,0,0);
	
	
	
	public Sprite(SpriteSheet sheet, int width, int height, int x, int y){
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		this.x = x * width;
		this.y = y * height;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(Sprite sprite){ //Horizontal Flip
		width = sprite.width;
		height = sprite.height;
		pixels = new int[width * height];
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				pixels[x + y * width] = sprite.pixels[width-1 - x + y * sprite.width];
			}
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.getWidth()];
			}
		}
	}
}
