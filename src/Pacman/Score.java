package Pacman;

import java.awt.Color;
import java.awt.Graphics;

public class Score implements Drawable {
	static int score = 0;
	String scoreText = "score";
	
	@Override
	public void draw(Graphics g) {
		scoreText = "score: " + score;
		
		g.setColor(Color.BLACK);
		g.drawString(scoreText, 10, 550);
	}

}
