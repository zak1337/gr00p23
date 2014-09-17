package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[120];
	public boolean up,down,left,right,spacebar,p,one,two,three,g;
	
	public void update(){
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S] || keys[KeyEvent.VK_C];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		spacebar = keys[KeyEvent.VK_SPACE];
		p = keys[KeyEvent.VK_P];
		one = keys[KeyEvent.VK_1];
		two = keys[KeyEvent.VK_2];
		three = keys[KeyEvent.VK_3];
		g = keys[KeyEvent.VK_G];
	}
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	public void keyTyped(KeyEvent e){
		
	}
}
