package Pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class Edibles implements Drawable {
	
	public static HashMap<Integer, HashSet<Integer>> edibles;
	public static HashMap<Integer, HashSet<Integer>> cherries;
	public static HashMap<Integer, HashSet<Integer>> powerUps;
	
	public Edibles() {
		edibles = new HashMap<>();
		cherries = new HashMap<>();
		powerUps = new HashMap<>();
		getEdibles();
		getCherries();
		getPowerUps();
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		
		for(Map.Entry<Integer, HashSet<Integer>> entry: edibles.entrySet()) {
			int key = entry.getKey();
			for(int val: entry.getValue()) {
				g.fillOval(key + 6, val + 6, 6, 6);
			}
		}
		
		for(Map.Entry<Integer, HashSet<Integer>> entry: cherries.entrySet()) {
			int key = entry.getKey();
			BufferedImage cherry = null;
			try {
				cherry = ImageIO.read(new File("images/cherry.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			for(int val: entry.getValue()) {
				g.drawImage(cherry, key, val, null);
			}
		}
		
		for(Map.Entry<Integer, HashSet<Integer>> entry: powerUps.entrySet()) {
			int key = entry.getKey();
			for(int val: entry.getValue()) {
				g.fillOval(key + 3, val + 3, 15, 15);
			}
		}
	}
	
	public static void getPowerUps() {
		powerUps.put(340, new HashSet<>());
		powerUps.put(60, new HashSet<>());
		powerUps.put(420, new HashSet<>());
		powerUps.get(340).add(40);
		powerUps.get(60).add(400);
		powerUps.get(420).add(320);
	}
	
	public static void getCherries() {
		cherries.put(140, new HashSet<>());
		cherries.put(180, new HashSet<>());
		cherries.put(320, new HashSet<>());
		cherries.get(140).add(40);
		cherries.get(180).add(440);
		cherries.get(320).add(280);
	}
	
	public static void getEdibles() {
		
		for(int i = 20; i <= 460; i = i + 20) {
			edibles.put(i, new HashSet<>());
		}
		
		int[][] cells = Maze.maze;
		
		for(int i = 1; i < 24; i++) {
			for(int j = 1; j < 24; j++) {
				if(cells[i][j] == 0) {
					edibles.get(j * 20).add(i * 20);
				}
			}
		}
		
		edibles.get(40).remove(220);
		edibles.get(40).remove(260);
		edibles.get(60).remove(400);
		edibles.get(80).remove(80);
		edibles.get(80).remove(80);
		edibles.get(140).remove(40);
		edibles.get(180).remove(160);
		edibles.get(180).remove(320);
		edibles.get(180).remove(440);
		edibles.get(200).remove(240);
		edibles.get(220).remove(160);
		edibles.get(220).remove(240);
		edibles.get(220).remove(320);
		edibles.get(240).remove(120);
		edibles.get(240).remove(200);
		edibles.get(240).remove(220);
		edibles.get(240).remove(240);
		edibles.get(240).remove(360);
		edibles.get(260).remove(160);
		edibles.get(260).remove(240);
		edibles.get(260).remove(320);
		edibles.get(280).remove(240);
		edibles.get(300).remove(160);
		edibles.get(300).remove(320);
		edibles.get(320).remove(280);
		edibles.get(340).remove(40);
		edibles.get(420).remove(320);
		edibles.get(440).remove(220);
		edibles.get(440).remove(260);
	}

}
