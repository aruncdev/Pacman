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
        
        @Override
        public String toString(){
        	return "(" + x + "," + y + ")";
        }
    }
	
	public static ArrayList<int[]> shortestPath(int[][] matrix, int[] start, int[] end) {
		int sx = start[0], sy = start[1];
		int dx = end[0], dy = end[1];	

		int m = matrix.length;
	    int n = matrix[0].length;	    
	    
	    Cell[][] cells = new Cell[m][n];
	    for (int i = 0; i < m; i++) {
	        for (int j = 0; j < n; j++) {
	            if (matrix[i][j] != 1) {
	                cells[i][j] = new Cell(i, j, Integer.MAX_VALUE, null);
	            }
	        }
	    }

	    Queue<Cell> queue = new LinkedList<>();       
	    Cell src = cells[sx][sy];
	    src.dist = 0;
	    queue.add(src);
	    Cell dest = null;
	    Cell p;
	    while ((p = queue.poll()) != null) {
	        if (p.x == dx && p.y == dy) { 
	            dest = p;
	            break;
	        }      

	        visit(cells, queue, p.x - 1, p.y, p);        

	        visit(cells, queue, p.x + 1, p.y, p);        

	        visit(cells, queue, p.x, p.y - 1, p);        

	        visit(cells, queue, p.x, p.y + 1, p);
	    }
	    
	    ArrayList<int[]> res = new ArrayList<>();
	    LinkedList<Cell> path = new LinkedList<>();
        p = dest;
        
        do {
            path.addFirst(p);
            res.add(0, new int[] {p.x * 20, p.y * 20});
        } while ((p = p.prev) != null);
        
        return res;
	}
	
	private static void visit(Cell[][] cells, Queue<Cell> queue, int x, int y, Cell parent) { 
	    if (x < 0 || x >= cells.length || y < 0 || y >= cells[0].length || cells[x][y] == null) {
	        return;
	    }    
	    
	    int dist = parent.dist + 1;
	    Cell p = cells[x][y];
	    
	    if (dist < p.dist) {
	        p.dist = dist;
	        p.prev = parent;
	        queue.add(p);
	    }
	}
}
