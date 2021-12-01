package Pacman;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class BlueGhost implements Drawable {
	public static int posx, posy;
	private long deadAt;
	private boolean isScatterMode;
	private boolean isChaseMode;
	private boolean isFrightenedMode;
	private long startTime;
	private boolean reachedPos;
	private Queue<int[]> path;
	
	public BlueGhost() {
		posx = 280;
		posy = 240;
		deadAt = Long.MAX_VALUE;
		isScatterMode = true;
		isChaseMode = false;
		isFrightenedMode = false;
		startTime = System.currentTimeMillis();
		reachedPos = false;
		path = new LinkedList<>();
	}
	
	@Override
	public void draw(Graphics g) {
		BufferedImage blueGhost = null;
		try {
			if(posx == -1) {
				if(System.currentTimeMillis() - deadAt > 5000) {
					blueGhost = ImageIO.read(new File("images/blue.png"));
					posx = 280;
					posy = 240;
				} else {
					posx = -1;
					return;
				}
			} else {
				if(Pacman.isHunter) {
					blueGhost = ImageIO.read(new File("images/afraid.png"));
				} else {
					blueGhost = ImageIO.read(new File("images/blue.png"));
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
			int desX = 440, desY = 240;
			int[] next = new int[] {-1, -1};
			
			if(!path.isEmpty()) {
				next = path.poll();
				posx = next[1];
				posy = next[0];
			} else {
				path.addAll(Maze.getPathForGhost(posy / 20, posx / 20, desY / 20, desX / 20));
			}
			
			if(posx == 440 && posy == 240) {
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
		
		g.drawImage(blueGhost, posx, posy, null);
		
		if(posx == Pacman.posx && posy == Pacman.posy) {
			if(Pacman.isHunter) {
				deadAt = System.currentTimeMillis();
				posx = -1;
				path = new LinkedList<>();
			} else {
				Maze.endGame();
			}			
		}
	}
}
