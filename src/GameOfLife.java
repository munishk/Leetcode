import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameOfLife {
	private static class Coordinate {
        int i; int j;
        
       public Coordinate(int i, int j) {
            this.i = i;
            this.j = j;
        }
        
        public boolean equals(Object obj) {
            if(obj == null || !(obj instanceof Coordinate)) {
                return false;
            }
            
            Coordinate c = (Coordinate) obj;
            return this.i == c.i && this.j == c.j;
        }
        
        public int hashCode() {
            int hash = 1;
            hash = 31 * hash + i;
            hash = 31 + hash * j;
            return hash;
        }
    }
    
    public void gameOfLife(int[][] board) {
        Set<Coordinate> lives = new HashSet<>();
        int m = board.length;
        int n = board[0].length;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(board[i][j] == 1) {
                    lives.add(new Coordinate(i, j));
                }
            }
        }
        
        Set<Coordinate> nextLives = nextLives(lives);
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                Coordinate c = new Coordinate(i, j);
                board[i][j] = nextLives.contains(c) ? 1: 0;
            }
        }
    }
    
    private Set<Coordinate> nextLives(Set<Coordinate> lives) {
        Map<Coordinate, Integer> neighbours = new HashMap<>();
        for(Coordinate cell : lives) {
            for(int i=cell.i-1; i< cell.i+2; i++) {
                for(int j=cell.j-1; j < cell.j+2; j++) {
                    if(i == cell.i && j == cell.j) continue;
                    Coordinate c = new Coordinate(i, j);
                    if(neighbours.containsKey(c)) {
                        neighbours.put(c, neighbours.get(c) + 1);
                    }else {
                        neighbours.put(c, 1);
                    }
                }
            }
        }
        
        Set<Coordinate> nextLives = new HashSet<>();
        for(Map.Entry<Coordinate, Integer> cell : neighbours.entrySet()) {
            if(cell.getValue() ==3 || (cell.getValue() ==2 && lives.contains(cell.getKey()))) {
                nextLives.add(cell.getKey());
            }
        }
        return nextLives;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
