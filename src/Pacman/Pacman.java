package Pacman;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pacman implements Drawable {
	static int posy, posx;
	static boolean isHunter = false;
	static long hunterAt;
	
	Pacman(){
		hunterAt = 0;
	}

	@Override
	public void draw(Graphics g) {
		BufferedImage pacman = null;
		try {
			pacman = ImageIO.read(new File("images\\pacman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(System.currentTimeMillis() - hunterAt > 10000) {
			isHunter = false;
		}
		
		if(posx > 480 )
			posx = 480;
		if(posy > 480)
			posy = 480;
		if(posx < 0)
			posx = 0;
		if(posy < 0)
			posy = 0;
		
		g.drawImage(pacman, posx, posy, null);
	}
	
	public void Up() {
		posy -= 20;
	}
	
	public void Down() {
		posy +=20;
	}
	
	public void Left() {
		posx -=20;
	}
	
	public void Right() {
		posx +=20;
	}
}
