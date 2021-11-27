package Pacman;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class RedGhost implements Drawable {
	public static int posx = 240, posy = 200;
	private long deadAt = Long.MAX_VALUE;
	private boolean isScatterMode = true;
	private boolean isChaseMode = false;
	private boolean isFrightenedMode = false;
	private long startTime = System.currentTimeMillis();
	private boolean reachedPos = false;
	private Queue<int[]> path = new LinkedList<>();
	
	@Override
	public void draw(Graphics g) {
		BufferedImage redGhost = null;
		try {
			if(posx == -1) {
				if(System.currentTimeMillis() - deadAt > 5000) {
					redGhost = ImageIO.read(new File("images\\red.png"));
					posx = 260;
					posy = 240;
				} else {
					posx = -1;
					return;
				}
			} else {
				if(Pacman.isHunter) {
					redGhost = ImageIO.read(new File("images\\afraid.png"));
				} else {
					redGhost = ImageIO.read(new File("images\\red.png"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(System.currentTimeMillis() - startTime < 5000) {
			isScatterMode = true;
			isChaseMode = false;
			isFrightenedMode = false;
		} else if(System.currentTimeMillis() - startTime < 9000) {
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
			int desX = 400, desY = 80;
			int[] next = new int[] {-1, -1};
			
			if(!path.isEmpty()) {
				next = path.poll();
				posx = next[1];
				posy = next[0];
			} else {
				path.addAll(Maze.getPathForGhost(posy / 20, posx / 20, desY / 20, desX / 20));
			}
			
			if(posx == 400 && posy == 80) {
				reachedPos = true;
			}
			
		} else if(isChaseMode) {
			int desX = Pacman.posx, desY = Pacman.posy;
			int[] next = new int[] {-1, -1};
			
			if(!path.isEmpty()) {
				next = path.poll();
				posx = next[1];
				posy = next[0];
			} else {
				path.addAll(Maze.getPathForGhost(posy / 20, posx / 20, desY / 20, desX / 20));
			}
		} else if(isFrightenedMode) {
			path = new LinkedList<>();
			int prex = posx, prey = posy;
			if(Math.random() > 0.5) {
				if(Math.random() > 0.5) {
					posx += posx >= 440 ? 0 : 20;
				} else {
					posx -= 20;
				}
			} else {
				if(Math.random() > 0.5) {
					posy += posy >= 440 ? 0 : 20;
				} else {
					posy -= 20;
				}
			}
			
			if(Maze.maze[posy / 20][posx / 20] == 1) {
				posx = prex;
				posy = prey;
			}
		}
		
		g.drawImage(redGhost, posx, posy, null);
		
		if(posx == Pacman.posx && posy == Pacman.posy) {
			if(Pacman.isHunter) {
//				Score.score = Score.score + 10;
				deadAt = System.currentTimeMillis();
				posx = -1;
				path = new LinkedList<>();
			} else {
				Maze.endGame();
			}			
		}
	}
}
