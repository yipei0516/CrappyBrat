package flappyBird;

import java.awt.Image;

public class Tube {
	public int x;
	public int y;
	public int width;
	public int height;
	public int velocity;
	public String NorS;
	private Image image;
	
	public Tube(String NorS) {
		this.width = 66;
        this.height = 400;
        this.x = Window.WIDTH + 2;
        this.NorS = NorS;
        changeVelocity();
        if (this.NorS.equals("S"))
        	this.y = -(int)(Math.random() * 150) - 200;
	}
     
	private void changeVelocity() {
		if      (Setting.difficulty == "Easy")   this.velocity = 4;
		else if (Setting.difficulty == "Medium") this.velocity = 6;
		else if (Setting.difficulty == "Hard")   this.velocity = 8;
		else if (Setting.difficulty == "Oni")    this.velocity = 8;
	}
	
	public void updateX() {
		this.x -= this.velocity;
	}
	
	public boolean ifCollides(int ameX, int ameY, int ameWidth, int ameHeight, int ameEdgeX, int ameEdgeY) {
		if (ameX + ameWidth - ameEdgeX > this.x && ameX + ameEdgeX < this.x + this.width && ((this.NorS.equals("N") && ameY + ameHeight - ameEdgeY > this.y) || (this.NorS.equals("S") && ameY + ameEdgeY < this.y + this.height)))
			return true;
		else
			return false;
	}
	
	public Render render() {
		Render render = new Render();
		render.x = this.x;
		render.y = this.y;
		if (this.image == null)
			this.image = ImageTool.loadImage("images/"+NorS+"Tube.png");
		render.image = this.image;
		return render;
	}
}
