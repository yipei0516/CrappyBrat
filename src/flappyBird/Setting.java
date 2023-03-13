package flappyBird;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Setting {
	
	public static String difficulty;
	private KeyDetecting keyDetecting;
	
	
	public Setting() {
		this.keyDetecting = KeyDetecting.instance();
	}
	
	public void update() {
		if      (this.keyDetecting.isPressed(KeyEvent.VK_1)) difficulty = "Easy";
		else if (this.keyDetecting.isPressed(KeyEvent.VK_2)) difficulty = "Medium";
		else if (this.keyDetecting.isPressed(KeyEvent.VK_3)) difficulty = "Hard";
		else if (this.keyDetecting.isPressed(KeyEvent.VK_4)) difficulty = "Oni";
	}
	
	
	public ArrayList<Render> renders() {
        ArrayList<Render> renders = new ArrayList<Render>();
        renders.add(new Render(0, 0, "images/setting.png"));
        return renders;
    }
}
