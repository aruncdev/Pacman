package Pacman;

import java.awt.*;
import java.awt.Graphics;

import javax.swing.*;

public class Maze extends JPanel {
	
	private static Drawable items[];	
	
	public Maze(Pacman pacman, RedGhost redGhost, PinkGhost pinkGhost, BlueGhost blueGhost, OrangeGhost orangeGhost) {
		items = new Drawable[7];	
		items[0] = pacman;
		items[1] = redGhost;
		items[2] = pinkGhost;
		items[3] = blueGhost;
		items[4] = orangeGhost;
		items[5] = new PowerDot(300, 40);
		items[6] = new Score();
	}
	
	public static void endGame() {
		items = new Drawable[0];
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame,
			    "Game Over!!! Your score: " + Score.score,
			    "Game Over!!!",
			    JOptionPane.PLAIN_MESSAGE);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(0, 0, 500, 500);
		
		for(int i = 0; i < 500; i = i + 10) {
			g.drawLine(i, 0, i, 500);
		}
		
		for(int i = 0; i < 500; i = i + 10) {
			g.drawLine(0, i, 500, i);
		}
		
		for(int i = 0; i < items.length; i++) {
			items[i].draw(g);
		}
		
	}
}
