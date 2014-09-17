package Enitities;

import gr00p23.Screen;

import java.util.Random;

import sounds.SoundEffect;
import Graphics.Sprite;
import Level.Level;

public class Shiba extends Mob {
	public int hp;
	private int maxhp;
	Sprite sprite = Sprite.kittenFloat;
	int asteroidD = 60;

	public Shiba(int x, int y, Level level) {
		this.x = x;
		this.y = y;
		this.hp = 2500;
		maxhp = 2500;
		this.level = level;
		this.sprite = Sprite.shiba;
		damage = 90;
	}

	public void update() {
		if (hp <= 1) {
			level.killCount++;
			
			SoundEffect.GAMEOVERWIN.play();
			remove();
		}
		x--;
		if (x < 500) {
			x = 500;
		}
		Random r = new Random();
		int coinChance = r.nextInt(30);
		if (coinChance < 1 && x == 500) {
			shootCoin(y + 32);
		}
		if (hp < maxhp/2 && coinChance < 1) {
			shootCoin(y);
			shootCoin(y + 48);
			shootCoin(y - 16);
			sprite = Sprite.shibaDemon;
		}
		if (hp < maxhp/4 && coinChance < 2) {
			
			shootCoin(y + 16);
			shootCoin(y + 64);
			shootCoin(20);
			shootCoin(360-36);
			sprite = Sprite.shibaDemon2;
			asteroidD = 120;
		}
		if (hp < 1875 && coinChance < 2){
			Random rwrq = new Random();
			int rand = r.nextInt(300);
			if(rand <= asteroidD){
				level.spawnAsteroid();
			}
			else if(rand <= asteroidD+2){
				level.spawnCatnip();
			}
		}
		
		if (y > 360 - 64) {
			y = 360 - 64;
		}
		if (hp % 500 == 0) {
			hp--;
			level.add(new Fish(x-4,y+32,level));
		}

	}

	private void shootCoin(int y) {
		level.add(new DogeCoin(x - 4, y));
	}

	public void remove() {

		removed = true;
	}

	public void chaserAIUp() {
		y--;
	}

	public void chaserAIDown() {
		y++;
	}

	public void chaserAI(int ky) {
		if (y + 8 > ky) {
			y--;
		}
		if (y + 8 < ky) {
			y++;
		}
	}

	public void isHit() {
		hp--;
	}

	public void render(Screen screen) {
		screen.renderSprite(x, y, sprite, false);
		screen.renderSprite(410, 2, Sprite.sleepbar, false);
		double hpdb = hp;
		double hpmaxdb = maxhp;
		for (int i = 0; i < (((hpdb / hpmaxdb) * 100)); i++) {
			screen.renderSprite(410 + 1 + i, 2 + 2, Sprite.sleepFill, false);
		}
	}
}
