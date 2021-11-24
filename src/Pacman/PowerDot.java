package Pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PowerDot implements Drawable, ActionListener {
	private int posX, posY;
	long activatedAt;
	
	public PowerDot(int X, int Y) {
		posX = X;
		posY = Y;
		activatedAt = 0;
	}

	@Override
	public void draw(Graphics g) {
		int pacmanX = Pacman.posx;
		int pacmanY = Pacman.posy;
		
		if(posX == pacmanX && posY == pacmanY) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(posX, posY, 20, 20);
			
			Score.score = Score.score + 1;
			activatedAt = System.currentTimeMillis();
			
			posX = -1;
			posY = -1;
			Pacman.isHunter = true;
			Pacman.hunterAt = activatedAt;
			
		} else if(posX == -1 && posY == -1 && System.currentTimeMillis() - activatedAt > 25000) {
			posX = (int) (Math.random() * 500);
			posY = (int) (Math.random() * 500);
				
			posX = posX - posX % 20;
			posY = posY - posY % 20;
				
			if(posX < 0) {
				posX = 0;
			}
			if(posX > 480) {
				posX = 480;
			}
			if(posY < 0) {
				posY = 0;
			}
			if(posY > 480) {
				posY = 480;
			}
			
		} else {
			g.setColor(Color.CYAN);
			if(posX != -1)
				g.fillOval(posX, posY, 20, 20);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
