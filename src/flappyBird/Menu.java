package flappyBird;

import java.util.ArrayList;

public class Menu {
	
	public ArrayList<Render> renders() {
        ArrayList<Render> renders = new ArrayList<Render>();
        renders.add(new Render(0, 0, "images/menu.png"));
        return renders;
    }
}
