package Pacman;

import java.util.*;

public class ShortestPathBetweenCells {
	private static class Cell  {
        int x;
        int y;
        int dist;
        Cell prev;

        Cell(int x, int y, int dist, Cell prev) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.prev = prev;
        }
    }
	
	public static Cell[][] initializeCells(int[][] matrix) {
		Cell[][] cells = new Cell[matrix.length][matrix[0].length];
	    for (int i = 0; i < matrix.length; i++) {
	        for (int j = 0; j < matrix[i].length; j++) {
	            if (matrix[i][j] != 1) {
	                cells[i][j] = new Cell(i, j, Integer.MAX_VALUE, null);
	            }
	        }
	    }
	    
	    return cells;
	}
	
	public static ArrayList<int[]> shortestPath(int[][] matrix, int[] start, int[] end) {
		int sx = start[0], sy = start[1]; // start point
		int dx = end[0], dy = end[1]; // end point
		
		int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 4 directions

	    Cell[][] cells = initializeCells(matrix); // initial state of cells

	    Queue<Cell> path = new LinkedList<>(); 
	    Cell src = cells[sx][sy];
	    src.dist = 0;
	    path.add(src); //starting from source cell
	    Cell dest = null;
	    Cell cur;
	    
	    while ((cur = path.poll()) != null) { // following path each cell at a time
	        if (cur.x == dx && cur.y == dy) {  // found destination then no need to traverse any more. So, break
	            dest = cur;
	            break;
	        }
	        
	        for(int[] dir: dirs) { // visit 4 directions, left, right, up, down
	        	helper(cells, path, cur.x + dir[0], cur.y + dir[1], cur, matrix.length, matrix[0].length);
	        }
	    }
	    
	    ArrayList<int[]> res = new ArrayList<>(); //storing the shortest path
	    cur = dest; // need to traverse from reverse, to get correct path, des to src
        
        do {
            res.add(0, new int[] {cur.x * 20, cur.y * 20}); // adding the cells traversal to the list. As moving from dest to src, adding first
        } while ((cur = cur.prev) != null); // traversing the path in reverse order
        
        return res;
	}
	
	private static void helper(Cell[][] cells, Queue<Cell> path, int row, int col, Cell parent, int rows, int cols) { 
	    if (row < 0 || row >= rows || col < 0 || col >= cols || cells[row][col] == null) { // If out of bounds or not a valid cell no need to visit there 
	        return;
	    }
	    
	    Cell cur = cells[row][col];
	    
	    if (parent.dist + 1 < cur.dist) { // If found the better distance path go their and visit it
	    	cur.dist = parent.dist + 1; //updating the distance
	    	cur.prev = parent; //assigning the previous
	        path.add(cur);
	    }
	}
}
