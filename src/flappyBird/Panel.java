package flappyBird;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Panel extends JPanel implements Runnable{
	
	private Game game;
	private Setting setting;
	private Menu menu;
	private Score score;
	private KeyDetecting keyDetecting;
	private String mode;
	
	public Panel() {
		this.setting = new Setting();
		Setting.difficulty = "Easy";
		this.mode = "Menu";
		this.menu = new Menu();
        this.game = new Game();
        this.score = new Score();
        this.keyDetecting = KeyDetecting.instance();
        new Thread(this).start();
    }
	
	public void update() {
		if (mode == "Game") 
			this.game.update();
        else if (mode == "Setting")
        	this.setting.update();
        repaint();
    }
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        switch (whichScene()) {
        case "Menu":
        	for (Render r : menu.renders())
			    if (r.transform != null)
			        g2D.drawImage(r.image, r.transform, null);
			    else
			        g.drawImage(r.image, r.x, r.y, null);
        	break;
        
        case "Setting":
        	for (Render r : setting.renders())
			    if (r.transform != null)
			        g2D.drawImage(r.image, r.transform, null);
			    else
			        g.drawImage(r.image, r.x, r.y, null);
        	break;
        	
        case "Game":
			for (Render r : game.renders())
			    if (r.transform != null)
			        g2D.drawImage(r.image, r.transform, null);
			    else
			        g.drawImage(r.image, r.x, r.y, null);
			if (!this.game.started) {
				g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		        g2D.drawString("Press Space to start", 150, 330);
			}
			break;
        case "Score":
        	for (Render r : this.score.renders())
			    if (r.transform != null)
			        g2D.drawImage(r.image, r.transform, null);
			    else
			        g.drawImage(r.image, r.x, r.y, null);
        	score.drawScore(this.game.score, g2D);
        	break;
        }
    }
	
	public void run() {
        try {
            while (true) {
                update();
                Thread.sleep(25); //fps
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private String whichScene() {
		if (mode != "Game" || !this.game.started || this.game.gameover) {
			if (mode == "Menu" && this.keyDetecting.isPressed(KeyEvent.VK_S)) {
				this.mode = "Setting";
				this.game.restart();
			}
			else if (mode != "Game" && this.keyDetecting.isPressed(KeyEvent.VK_M)) {
				this.mode = "Menu";
				this.game.restart();
			}
			else if (mode != "Score" && this.keyDetecting.isPressed(KeyEvent.VK_G)) {
				this.mode = "Game";
				this.game.restart();
			}
			else if (this.game.gameover)				
				this.mode = "Score";
			if(this.keyDetecting.isPressed(KeyEvent.VK_R))
				this.mode = "Game";
		}
		return this.mode;
	}
}
