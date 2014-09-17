package Enitities;

import java.util.Random;

import sounds.SoundEffect;
import gr00p23.Screen;
import Graphics.Sprite;
import Input.Keyboard;
import Level.Level;

public class Kitten extends Mob {
	Keyboard input;
	public boolean shooting= false;
	public int lazorBar = 100;
	public int energyBar = 100;
	private int energyBarDegr = 45;
	private int energyBarDegrC = 0;
	private int cooldown = 60;
	private int cooldownTimer = 0;
	private int invulnFrames = 40;
	private int invulnFrame = 0;
	private int soundFrameHolder = 0;
	private int soundFrameStart = 21;
	private int soundFrameCon = 60;
	private int soundFrameHolder2 = 0;
	private int catNipTimer = 0;
	public boolean catNipMode = false;
	
	public Kitten(int x, int y, Keyboard input, Level level){
		this.x = x;
		this.y = y;
		this.input = input;
		this.level = level;
		sprite = Sprite.kittenFloat;
	}
	
	public boolean isVulnerable(){
		boolean result = false;
		if (invulnFrame == invulnFrames){
			result = true;
		}
		return result;
	}
	
	public void isHit(int damage){
		energyBar-=damage;
		if(damage > 0){
			invulnFrame = 0;
			Random r = new Random();
			int chance = r.nextInt(2);
			if(chance == 0){
				SoundEffect.CATHURT.play();
			}
			if(chance == 1){
				SoundEffect.CATHURT2.play();
			}
		}
		else if(energyBar > 100){
			energyBar = 100;
		}
		if(damage == -1){
			catNipMode = true;
			catNipTimer = 0;
		}
		
	}
	
	public void update(){
		int spd = 4;
		int ax = 0;
		int ay = 0;
		if(energyBar < 25){
			spd = 1;
		}
		else if(energyBar <50){
			spd = 2;
		}
		else if(energyBar <75){
			spd = 3;
		}
			
		invulnFrame++;
		if(invulnFrame > invulnFrames){
			invulnFrame=40;
		}
		energyBarDegrC++;
		if(energyBarDegrC == energyBarDegr){
			energyBar--;
			energyBarDegrC = 0;
		}
		
		if (input.up){
			ay-=spd;
		}
		if (input.down){
			ay+=spd;
		}
		if (input.right){
			ax+=spd;
		}
		if (input.left){
			ax-=spd;
		}
		if(input.spacebar && lazorBar>0){
			shooting=true;
			if(!catNipMode){
				lazorBar-=1;
			}
			ax-=1;
			soundFrameHolder++;
			if(cooldownTimer!=0){
				SoundEffect.LAZERSTART.play();
			}
			if(soundFrameHolder > soundFrameStart && soundFrameHolder2 == 0){
				SoundEffect.LAZER.play();
			}
			System.out.println(soundFrameHolder2);
			soundFrameHolder2++;
			if(soundFrameHolder2 > soundFrameCon){
				soundFrameHolder2=0;
			}
			cooldownTimer = 0;
		}
		else{
			shooting=false;
			SoundEffect.LAZER.stop();
			if(soundFrameHolder2 != 0){
				SoundEffect.LAZEREND.play();
			}
			soundFrameHolder2 = 0;
			cooldownTimer++;
			if(cooldownTimer > cooldown){
				lazorBar+=3;
				if(lazorBar > 100){
					lazorBar = 100;
				}
			}
		}
		if(catNipMode){
			catNipTimer++;
			lazorBar = 100;
			if(catNipTimer > 600){
				catNipTimer = 0;
				catNipMode = false;
			}
		}
		if(ax== 0){
			sprite = Sprite.kittenFloat;
		}
		if(ax < 0){
			sprite=Sprite.kittenLeft;
		}
		if(ax>0){
			sprite=Sprite.kittenRight;
		}
		x+=ax;
		y+=ay;
		if (x < 0){
			x = 0;
		}
		if (x > 1280/2-32){
			x = 1280/2-32;
		}
		if(y < 0){
			y = 0;
		}
		if(y > 720/2-32){
			y = 720/2-32;
		}
	}
	public void render(Screen screen){
		if(invulnFrame%2 == 0){
			screen.renderSprite(x, y, sprite, false);
		}
		if(shooting){
			screen.renderSprite(x,y,Sprite.lazer,false);
		}
	}
}
