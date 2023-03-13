package flappyBird;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.lang.Math;

public class Game {
	public static int PIPE_DELAY;
	
	public boolean started, paused, gameover;
	public int score;
	private int pipeDelay;
	private int pauseDelay;
	private int resetDelay;
	private int range;
	private double error;
	public Ame ame;
	private KeyDetecting keyDetecting;
	private ArrayList<Tube> tubes;
	
	public Game() {
		this.keyDetecting = KeyDetecting.instance();
		restart();
    }
	
	public void restart() {
		this.started = false;
		this.paused = false;
		this.gameover = false;
		this.score = 0;
		this.error = 3;
		this.pipeDelay = 0;
		this.pauseDelay = 0;
		this.resetDelay = 0;
		this.tubes = new ArrayList<Tube>();
		this.ame = new Ame();
		changeDelayAndRange();
	}
	
	private void changeDelayAndRange() { //DO NOT MODIFY
		if (Setting.difficulty == "Easy") {
			PIPE_DELAY = 100;
			this.range = 2;
		}
		else if (Setting.difficulty == "Medium") {
			PIPE_DELAY = 50;
			this.range = 0;
		}
		else if (Setting.difficulty == "Hard") {
			PIPE_DELAY = 30;
			this.range = 2;
		}
		else if (Setting.difficulty == "Oni") {
			PIPE_DELAY = 30;
			this.range = 2;
		}
	}
	
	public void update() {
		start();
		if (!this.started) return;
		reset();
		pause();		 
		if (this.paused)   return;
		if (!ame.gg) this.ame.update();
		if (this.gameover) return;
		moveTubes();
		collides();
    }
	
	public ArrayList<Render> renders() {
        ArrayList<Render> renders = new ArrayList<Render>();
        renders.add(new Render(0, 0, "images/background.png"));
        for (Tube tube : tubes) renders.add(tube.render());
        renders.add(new Render(0, 0, "images/foreground.png"));
        renders.add(ame.render());
        return renders;
    }
	
	private void start() {
		if (!this.started && this.keyDetecting.isPressed(KeyEvent.VK_SPACE))
			this.started = true;
		else if(!this.started) {
			if      (Setting.difficulty == "Easy")    PIPE_DELAY = 100;
	        else if (Setting.difficulty == "Medium")  PIPE_DELAY = 50;
	        else if (Setting.difficulty == "Hard")    PIPE_DELAY = 30;
	        else if (Setting.difficulty == "Oni")    PIPE_DELAY = 30;
		}
			
	}
	
	
	private void pause() {
		if (this.pauseDelay > 0)
			this.pauseDelay--;
		if (this.pauseDelay <= 0 && this.keyDetecting.isPressed(KeyEvent.VK_P)) {
			this.paused = !this.paused;
			this.pauseDelay = 10;
		}
	}
	
	private void reset() {
		if (this.resetDelay > 0)
			this.resetDelay--;
		if (this.resetDelay <= 0 && this.keyDetecting.isPressed(KeyEvent.VK_R)) {
			restart();
			this.resetDelay = 10;
		}
	}
	
	private void collides() {
		// Ame hits tubes
		for (Tube tube: tubes) {
			if (tube.ifCollides(ame.x, ame.y, ame.width, ame.height, ame.edgeX, ame.edgeY)) {
				this.gameover = true;
				this.ame.gg = true;
			}
			else if (Math.abs(ame.x - tube.x + range) < error && tube.NorS.equals("N")) {
				this.score++;
			}
		}
		
		// Ame hits ground
		if (Setting.difficulty == "Oni") {
			if (this.ame.y < 0) {
	            this.gameover = true;
	            this.ame.gg = true;
	            this.ame.y = 0;
			}
		}
		else {
			if (this.ame.y + this.ame.height - this.ame.edgeY > Window.HEIGHT - 65) {
	            this.gameover = true;
	            this.ame.gg = true;
	            this.ame.y = Window.HEIGHT - 80 - this.ame.height + this.ame.edgeY;
			}
        }
	}
	
	private void moveTubes() {
		this.pipeDelay--;
		
		if (this.pipeDelay < 0) { //renew tubes
			this.pipeDelay = PIPE_DELAY;
			
			if (this.tubes.size() > 0 && (this.tubes.get(0).x - this.tubes.get(0).width < 0)) { //screen-hitting tubes
				this.tubes.remove(0);
				this.tubes.remove(0);
			}
			
			//moving or no tubes
			Tube nTube = new Tube("N");
			Tube sTube = new Tube("S");
			this.tubes.add(nTube);		
			this.tubes.add(sTube);
			nTube.y = sTube.y + sTube.height + 175;
		}
		
		for (Tube tube : tubes)
            tube.updateX();
	}
}
