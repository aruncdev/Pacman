package Pacman;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pacman implements Drawable {
	static int posy, posx;
	static boolean isHunter;
	static long hunterAt;
	private BufferedImage pacman;
	
	Pacman(){
		posx = 80;
		posy = 80;
		isHunter = false;
		hunterAt = 0;
		try {
			pacman = ImageIO.read(new File("images/rightPacman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g) {
		if(System.currentTimeMillis() - hunterAt > 10000) {
			isHunter = false;
		}
		
		g.drawImage(pacman, posx, posy, null);
		
		if(Edibles.edibles.containsKey(posx) && Edibles.edibles.get(posx).contains(posy)) {
			Edibles.edibles.get(posx).remove(posy);
			Maze.score = Maze.score + 1;
			if(Edibles.edibles.get(posx).size() == 0) {
				Edibles.edibles.remove(posx);
			}
			
			if(Edibles.edibles.size() == 0 && Edibles.cherries.size() == 0) {
				Maze.endGame();
			}
		}
		
		if(Edibles.cherries.containsKey(posx) && Edibles.cherries.get(posx).contains(posy)) {
			Edibles.cherries.get(posx).remove(posy);
			Maze.score = Maze.score + 10;
			if(Edibles.cherries.get(posx).size() == 0) {
				Edibles.cherries.remove(posx);
			}
			
			if(Edibles.edibles.size() == 0 && Edibles.cherries.size() == 0) {
				Maze.endGame();
			}
		}
		
		if(Edibles.powerUps.containsKey(posx) && Edibles.powerUps.get(posx).contains(posy)) {
			Edibles.powerUps.get(posx).remove(posy);
			Maze.score = Maze.score + 20;
			if(Edibles.powerUps.get(posx).size() == 0) {
				Edibles.powerUps.remove(posx);
			}
			isHunter = true;
			hunterAt = System.currentTimeMillis();
		}
		
		boolean isDead = (posx == OrangeGhost.posx && posy == OrangeGhost.posy) || 
				(posx == BlueGhost.posx && posy == BlueGhost.posy) ||
				(posx == PinkGhost.posx && posy == PinkGhost.posy) ||
				(posx == RedGhost.posx && posy == RedGhost.posy);
		
		if(isDead && !isHunter) {
			Maze.endGame();
		}
	}
	
	public void Up() {
		if(Maze.maze[(posy - 20) / 20][posx / 20] != 1) {
			posy -= 20;
		}
		try {
			pacman = ImageIO.read(new File("images/upPacman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Down() {
		if(Maze.maze[(posy + 20) / 20][posx / 20] != 1) {
			posy += 20;
		}
		try {
			pacman = ImageIO.read(new File("images/downPacman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Left() {
		if(posy / 20 == 12 && ((posx - 20) / 20) == 0) {
			posy = 12 * 20;
			posx = 23 * 20;
			return;
		}
		if(Maze.maze[posy / 20][(posx - 20) / 20] != 1) {
			posx -= 20;
		}
		try {
			pacman = ImageIO.read(new File("images/leftPacman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Right() {
		if(posy / 20 == 12 && ((posx + 20) / 20) == 24) {
			posy = 12 * 20;
			posx = 1 * 20;
			return;
		}
		if(Maze.maze[posy / 20][(posx + 20) / 20] != 1) {
			posx += 20;
		}
		try {
			pacman = ImageIO.read(new File("images/rightPacman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
