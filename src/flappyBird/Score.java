package flappyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Score {
	
	public void drawScore(int score, Graphics2D g2D) {
		g2D.setColor(Color.BLACK);
		g2D.setFont(new Font("TimesRoman", Font.PLAIN, 100));
        g2D.drawString(String.valueOf(score), 210, 300);
	}
	
	public ArrayList<Render> renders() {
        ArrayList<Render> renders = new ArrayList<Render>();
        renders.add(new Render(0, 0, "images/score.png"));
        return renders;
    }
}
