package Pacman;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BlueGhost implements Drawable {
	private int posx = 360, posy = 400;
	private long deadAt = Long.MAX_VALUE;
	private boolean isScatterMode = true;
	private boolean isChaseMode = false;
	private boolean isFrightenedMode = false;
	private long startTime = System.currentTimeMillis();
	private boolean reachedPos = false;
	
	@Override
	public void draw(Graphics g) {
		BufferedImage blueGhost = null;
		try {
			if(posx == -1) {
				if(System.currentTimeMillis() - deadAt > 5000) {
					blueGhost = ImageIO.read(new File("images\\blue.png"));
					posx = 360;
					posy = 400;
				} else {
					posx = -1;
					return;
				}
			} else {
				if(Pacman.isHunter) {
					blueGhost = ImageIO.read(new File("images\\afraid.png"));
				} else {
					blueGhost = ImageIO.read(new File("images\\blue.png"));
//					lastDead = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(System.currentTimeMillis() - startTime < 8500) {
			isScatterMode = true;
			isChaseMode = false;
			isFrightenedMode = false;
		} else if(System.currentTimeMillis() - startTime < 10500) {
			isScatterMode = false;
			isChaseMode = true;
			isFrightenedMode = false;
			reachedPos = false;
		} else {
			startTime = System.currentTimeMillis();
			reachedPos = false;
		}
		
		if(Pacman.isHunter) {
			isScatterMode = false;
			isChaseMode = false;
			isFrightenedMode = true;
		}
		
		if(isScatterMode && reachedPos) {
			isScatterMode = false;
			isChaseMode = false;
			isFrightenedMode = true;
		}
		
		if(isScatterMode) {
			int xdiff = Math.abs(posx - 40);
			int ydiff = Math.abs(posy - 260);
			
			if(xdiff > ydiff) {
				if(posx > 40) {
					posx -= 20;
				} else {
					posx += 20;
				}
			} else {
				if(posy > 260) {
					posy -= 20;
				} else {
					posy += 20;
				}
			}
			
			if(posx == 40 && posy == 260) {
				reachedPos = true;
			}
			
		} else if(isChaseMode) {
			int xdiff = Math.abs(posx - Pacman.posx);
			int ydiff = Math.abs(posy - Pacman.posy);
			
			if(xdiff > ydiff) {
				if(posx > Pacman.posx) {
					posx -= 20;
				} else {
					posx += 20;
				}
			} else {
				if(posy > Pacman.posy) {
					posy -= 20;
				} else {
					posy += 20;
				}
			}
			
		} else if(isFrightenedMode) {
			if(Math.random() > 0.5) {
				if(Math.random() > 0.5) {
					posx += 20;
				} else {
					posx -= 20;
				}
			} else {
				if(Math.random() > 0.5) {
					posy += 20;
				} else {
					posy -= 20;
				}
			}
		}
		
		if(posx > 480 )
			posx = 480;
		if(posy > 480)
			posy = 480;
		if(posx < 0)
			posx = 0;
		if(posy < 0)
			posy = 0;
		

		if(posx != -1)
			g.drawImage(blueGhost, posx, posy, null);
		
		if(posx == Pacman.posx && posy == Pacman.posy) {
			if(Pacman.isHunter) {
				Score.score = Score.score + 10;
				deadAt = System.currentTimeMillis();
				posx = -1;
			} else {
				Maze.endGame();
			}			
		}
	}
}
