package Pacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.Timer;

public class Driver extends JFrame implements ActionListener, KeyListener {
	private Maze maze;
	private Pacman pacman;
	private RedGhost redGhost;
	private PinkGhost pinkGhost;
	private BlueGhost blueGhost;
	private OrangeGhost orangeGhost;
	
	public Driver() {
		pacman = new Pacman();
		redGhost = new RedGhost();
		pinkGhost = new PinkGhost();
		blueGhost = new BlueGhost();
		orangeGhost = new OrangeGhost();
		
		maze = new Maze(pacman, redGhost, pinkGhost, blueGhost, orangeGhost);
		this.getContentPane().add(maze);
		this.addKeyListener(this);
	}
	
	public static void main(String[] args) {
		Driver win = new Driver();
		win.setSize(650, 650);
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.startTimer();
	}
	
	public void startTimer() {
		Timer timer = new Timer(100, this);
		timer.start();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		switch(keyCode) {
		case KeyEvent.VK_UP: pacman.Up(); break;
		case KeyEvent.VK_DOWN: pacman.Down(); break;
		case KeyEvent.VK_LEFT: pacman.Left(); break;
		case KeyEvent.VK_RIGHT: pacman.Right(); break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		int keyCode = e.getKeyCode();
//		
//		switch(keyCode) {
//		case KeyEvent.VK_UP: pacman.Up(); break;
//		case KeyEvent.VK_DOWN: pacman.Down(); break;
//		case KeyEvent.VK_LEFT: pacman.Left(); break;
//		case KeyEvent.VK_RIGHT: pacman.Right(); break;
//		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		maze.repaint();
	}
}
