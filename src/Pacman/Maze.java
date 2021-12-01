package Pacman;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Maze extends JPanel {
	
	private static Drawable items[];	
	public static int[][] maze;
	public static int score;
	public static int pacmanLives;
	
	public Maze(Pacman pacman, RedGhost redGhost, PinkGhost pinkGhost, BlueGhost blueGhost, OrangeGhost orangeGhost) {
		items = new Drawable[6];	
		items[0] = pacman;
		items[1] = redGhost;
		items[2] = pinkGhost;
		items[3] = blueGhost;
		items[4] = orangeGhost;
		maze = new int[25][25];
		designMaze();
		items[5] = new Edibles();
		score = 0;
		pacmanLives = 3;
	}
	
	public static void endGame() {
		if(Edibles.edibles.size() == 0 && Edibles.cherries.size() == 0) {
			items = new Drawable[0];
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame,
				    "You Won!!! Your score: " + score,
				    "You Won!!!",
				    JOptionPane.PLAIN_MESSAGE);
		}  else if(pacmanLives > 1) {
			Pacman.posx = 80;
			Pacman.posy = 80;
			items[1] = new RedGhost();
			items[2] = new PinkGhost();
			items[3] = new BlueGhost();
			items[4] = new OrangeGhost();
			pacmanLives--;
			
			Long curTime = System.currentTimeMillis();
			
			while(System.currentTimeMillis() - curTime <= 1500) {
				
			}
			Pacman.posx = 80;
			Pacman.posy = 80;
		}
		else {
			items = new Drawable[0];
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame,
				    "Game Over!!! Your score: " + score,
				    "Game Over!!!",
				    JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.LIGHT_GRAY);
		
		for(int i = 20; i < 500; i = i + 20) {
			g.drawLine(i, 20, i, 480);
		}
		
		for(int i = 20; i < 500; i = i + 20) {
			g.drawLine(20, i, 480, i);
		}
		
		for(int i = 0; i < items.length; i++) {
			items[i].draw(g);
		}
		
		g.setColor(Color.BLUE);
		
		for(int i = 1; i < 24; i++) {
			for(int j = 1; j < 24; j++) {
				if(maze[i][j] == 1)
					g.fillRect(j * 20, i * 20, 20, 20);
			}
		}
		
		
		g.setColor(Color.BLACK);
		g.drawString("score: " + score, 500, 36);
		
		BufferedImage pacman = null;
		try {
			pacman = ImageIO.read(new File("images/rightPacman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < pacmanLives; i++) {
			g.drawImage(pacman, 500 + (i * 22), 45, null);
		}
	}
	
	private static void designMaze() {
		int[][] pathBlocks = new int[][] {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}, {0, 8}, {0, 9}, {0, 10}, {0, 11},
			{0, 12}, {0, 13}, {0, 14}, {0, 15}, {0, 16}, {0, 17}, {0, 18}, {0, 19}, {0, 20}, {0, 21}, {0, 22}, {0, 23}, {1, 0}, {1, 1}, 
			{1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}, {1, 8}, {1, 9}, {1, 10}, {1, 11}, {1, 12}, {1, 13}, {1, 14}, {1, 15}, {1, 16}, 
			{1, 17}, {1, 18}, {1, 19}, {1, 20}, {1, 21}, {1, 22}, {1, 23}, {2, 0}, {2, 1}, {2, 5}, {2, 19}, {2, 23}, {3, 0}, {3, 1}, {3, 3}, 
			{3, 5}, {3, 7}, {3, 8}, {3, 9}, {3, 10}, {3, 11}, {3, 13}, {3, 14}, {3, 15}, {3, 16}, {3, 17}, {3, 19}, {3, 21}, {3, 23}, {4, 0}, 
			{4, 1}, {4, 9}, {4, 15}, {4, 23}, {5, 0}, {5, 1}, {5, 2}, {5, 3}, {5, 5}, {5, 7}, {5, 9}, {5, 11}, {5, 12}, {5, 13}, {5, 15}, 
			{5, 17}, {5, 19}, {5, 21}, {5, 22}, {5, 23}, {6, 0}, {6, 1}, {6, 5}, {6, 7}, {6, 9}, {6, 11}, {6, 13}, {6, 15}, {6, 17}, {6, 19}, 
			{6, 23}, {7, 0}, {7, 1}, {7, 3}, {7, 5}, {7, 7}, {7, 9}, {7, 11}, {7, 12}, {7, 13}, {7, 15}, {7, 17}, {7, 19}, {7, 21}, {7, 23}, 
			{8, 0}, {8, 1}, {8, 5}, {8, 7}, {8, 17}, {8, 19}, {8, 23}, {9, 0}, {9, 1}, {9, 2}, {9, 3}, {9, 4}, {9, 5}, {9, 7}, {9, 9}, {9, 10},
			{9, 11}, {9, 12}, {9, 13}, {9, 14}, {9, 15}, {9, 17}, {9, 19}, {9, 20}, {9, 21}, {9, 22}, {9, 23}, {10, 0}, {10, 1}, {10, 7}, 
			{10, 17}, {10, 23}, {11, 0}, {11, 1}, {11, 3}, {11, 4}, {11, 5}, {11, 6}, {11, 7}, {11, 9}, {11, 10}, {11, 11}, {11, 13}, {11, 14}, 
			{11, 15}, {11, 17}, {11, 18}, {11, 19}, {11, 20}, {11, 21}, {11, 23}, {12, 0}, {12, 9}, {12, 15}, {13, 0}, {13, 1}, {13, 3}, {13, 4}, 
			{13, 5}, {13, 6},{13, 7}, {13, 9}, {13, 10}, {13, 11}, {13, 12}, {13, 13}, {13, 14}, {13, 15}, {13, 17}, {13, 18}, {13, 19}, {13, 20}, 
			{13, 21}, {13, 23}, {14, 0}, {14, 1}, {14, 7}, {14, 17}, {14, 23}, {15, 0}, {15, 1}, {15, 2}, {15, 3}, {15, 4}, {15, 5}, {15, 7}, 
			{15, 9}, {15, 10}, {15, 11}, {15, 12}, {15, 13}, {15, 14}, {15, 15}, {15, 17}, {15, 19}, {15, 20}, {15, 21}, {15, 22}, {15, 23}, 
			{16, 0}, {16, 1}, {16, 5}, {16, 7}, {16, 17}, {16, 19}, {16, 23}, {17, 0}, {17, 1}, {17, 3}, {17, 5}, {17, 7}, {17, 9}, {17, 11}, 
			{17, 12}, {17, 13}, {17, 15}, {17, 17}, {17, 19}, {17, 21}, {17, 23}, {18, 0}, {18, 1}, {18, 5}, {18, 7}, {18, 9}, {18, 11}, 
			{18, 13}, {18, 15}, {18, 17}, {18, 19}, {18, 23}, {19, 0}, {19, 1}, {19, 2}, {19, 3}, {19, 5}, {19, 7}, {19, 9}, {19, 11}, {19, 12},
			{19, 13}, {19, 15}, {19, 17}, {19, 19}, {19, 21}, {19, 22}, {19, 23}, {20, 0}, {20, 1}, {20, 9}, {20, 15}, {20, 23}, {21, 0}, 
			{21, 1}, {21, 3}, {21, 5}, {21, 7}, {21, 8}, {21, 9}, {21, 10}, {21, 11}, {21, 13}, {21, 14}, {21, 15}, {21, 16}, {21, 17}, {21, 19},
			{21, 21}, {21, 23}, {22, 0}, {22, 1}, {22, 5}, {22, 19}, {22, 23}, {23, 0}, {23, 1}, {23, 2}, {23, 3}, {23, 4}, {23, 5}, {23, 6}, 
			{23, 7}, {23, 8}, {23, 9}, {23, 10}, {23, 11}, {23, 12}, {23, 13}, {23, 14}, {23, 15}, {23, 16}, {23, 17}, {23, 18}, {23, 19}, 
			{23, 20}, {23, 21}, {23, 22}, {23, 23}};
			
			for(int[] path: pathBlocks) {
				maze[path[0]][path[1]] = 1;
			}
	}
	
	public static ArrayList<int[]> getPathForGhost(int x1, int y1, int x2, int y2) {
		ArrayList<int[]> path = new ArrayList<>();
		path = ShortestPathBetweenCells.shortestPath(maze, new int[] {x1, y1}, new int[] {x2, y2});
		
		return path;
		
	}
}
