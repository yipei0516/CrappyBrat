package flappyBird;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {

	public static int WIDTH = 500;
    public static int HEIGHT = 558;
    public static void main(String[] args) {
    	
    	Audio audio = new Audio();
    	
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Crappy Brat");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon("images/ame.png");
        jFrame.setIconImage(img.getImage());
        KeyDetecting keyDetecting = KeyDetecting.instance();
        
        jFrame.addKeyListener(keyDetecting);
        JPanel panel = (JPanel)new Panel();
        
        jFrame.add(panel);
        jFrame.setResizable(false);
        jFrame.setSize(WIDTH, HEIGHT);
        audio.musicLoop();
        
    }

}
