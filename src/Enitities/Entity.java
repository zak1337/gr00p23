package Enitities;

import gr00p23.Screen;

import java.util.Random;

import Level.Level;

public class Entity {
	public int x;
	public int y;
	public int hp = 240;
	public int damage = 0;
	protected boolean removed = false;
	protected Level level;
	protected final Random random = new Random();

	public void update() {
	}

	public void render(Screen screen) {
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}
	public void chaserAIUp(){
	
	}
	public void chaserAIDown(){
		
	}
	public void chaserAI(int ky){
		
	}
	
	public void isHit(){
		
	}
	
	public boolean mobCollision(int ix, int iy){
		boolean result = false;
		for(int i = 0; i < 32; i ++){
			for(int j = 0; j < 32; j++){	
				if(x+i == 16+ix && y + j ==16+iy){
					return true;
				}	
			}
		}
		return result;
	}
	
	public void init(Level level) {
		this.level = level;
	}
}
