package flappyBird;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Ame {
	public int x;
    public int y;
    public int width;
    public int height;
    public int edgeX;
    public int edgeY;
    public boolean gg;
    public double yVelocity;
    public double gravity;
    private static int Y_VELOCITY;
    private int jumpDelay;
    private double rotation;
    private Image image;
    private KeyDetecting keyDetecting;
    
	public Ame() {
		this.x = 100;
        this.y = 150;
        this.edgeX = 2;
        this.edgeY = 2;
        this.width = 45;
        this.height = 55;
        this.jumpDelay = 0;
        this.rotation = 0.0;
        this.gg = false;
        this.keyDetecting = KeyDetecting.instance();
        changeGravityAndVelocity();
	}
	
	private void changeGravityAndVelocity() {
		
		if(Setting.difficulty == "Easy") {
			this.gravity = 0.8;
			Y_VELOCITY = -9;
		}
		else if(Setting.difficulty == "Medium") {
			this.gravity = 1.4;
			Y_VELOCITY = -14;
		}
		else if(Setting.difficulty == "Hard") {
			this.gravity = 1.7;
			Y_VELOCITY = -17;
		}
		else if(Setting.difficulty == "Oni") {
			this.gravity = -1.7;
			Y_VELOCITY = 17;
		}
	}

	public void update() {
        this.yVelocity += this.gravity;

        if (this.jumpDelay > 0)
            this.jumpDelay--;

        if (this.keyDetecting.isPressed(KeyEvent.VK_SPACE) && this.jumpDelay <= 0) {
            this.yVelocity = Y_VELOCITY;
            this.jumpDelay = 10;
        }
        
        this.y += (int)this.yVelocity;
    }
	
	public Render render() {
		Render render = new Render();
		render.x = this.x;
		render.y = this.y;
		if (this.image == null)
			this.image = ImageTool.loadImage("images/ame.png");
		else {
			if (Setting.difficulty != "Oni") {
				if (this.yVelocity < Y_VELOCITY + 12)
					this.image = ImageTool.loadImage("images/ameJump.png");
				else
					this.image = ImageTool.loadImage("images/ame.png");
			}
			else {
				if (this.yVelocity > Y_VELOCITY - 12)
					this.image = ImageTool.loadImage("images/ameJump.png");
				else
					this.image = ImageTool.loadImage("images/ame.png");
			}
		}
		render.image = this.image;
		
		this.rotation = (90 * (this.yVelocity + 20) / 20) - 90;
        this.rotation = (this.rotation > 90) ? Math.PI / 2: this.rotation * Math.PI / 180;
        
        render.transform = new AffineTransform();
        render.transform.translate(this.x + this.width / 2, this.y + this.height / 2);
        render.transform.rotate(this.rotation);
        render.transform.translate(-this.width / 2, -this.height / 2);
		return render;
	}
}
