package flappyBird;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyDetecting implements KeyListener{
	private static KeyDetecting instance;
	private boolean[] keys;
	
	private KeyDetecting() {
		this.keys = new boolean[256];
	}
	
	public static KeyDetecting instance() {
		if (instance == null)
			instance = new KeyDetecting();
		return instance;
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() >= 0 && e.getKeyCode() < this.keys.length)
			this.keys[e.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() >= 0 && e.getKeyCode() < this.keys.length)
			this.keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {}

	public boolean isPressed(int key) {
		if (key >= 0 && key < this.keys.length)
			return this.keys[key];
		return false;
	}
	
}
