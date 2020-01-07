import java.util.ArrayList;
import java.util.List;

public class PacificAtlanticWaterFlow {
	
	public List<int[]> pacificAtlantic(int[][] matrix) {
        int m =  matrix.length;
        int n = matrix[0].length;
        List<int[]> res = new ArrayList<>();
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(canFlowToPacific(matrix, i,j) && canFlowToAtlantic(matrix, i, j)) {
                    res.add(new int[] {i,j});
                }
            }
        }
        return res;
    }
    
    private boolean canFlowToPacific(int[][] matrix, int row, int col) {
        boolean left = true;
        boolean up = true;
        //check on left side
        for(int i=col-1; i>=0; i--) {
            if(matrix[row][i] > matrix[row][col]) {
                left = false;
                break;
            }
        }
        
         //check on up side
        for(int i=row-1; i>=0; i--) {
            if(matrix[i][col] > matrix[row][col]) {
                up = false;
                break;
            }
        }
        return left || up;
    }
    
    
    private boolean canFlowToAtlantic(int[][] matrix, int row, int col) {
        boolean right = true;
        boolean down = true;
        //check on right side
        for(int i=col+1; i<matrix[0].length; i++) {
            if(matrix[row][i] > matrix[row][col]) {
                right = false;
                break;
            }
        }
        
         //check on down side
        for(int i=row+1; i<matrix.length; i++) {
            if(matrix[i][col] > matrix[row][col]) {
                down = false;
                break;
            }
        }
        return right || down;
    }

	public static void main(String[] args) {
		int[][] matrix = {
				{1,2,2,3,5},
				{3,2,3,4,4},
				{2,4,5,3,1},
				{6,7,1,4,5},
				{5,1,1,2,4}
		};
		
		PacificAtlanticWaterFlow sol = new PacificAtlanticWaterFlow();
		List<int[]> res = sol.pacificAtlantic(matrix);
		res.stream().forEach(o1 -> {System.out.println("(" + o1[0] + "," + o1[1] + ")");});

	}

}
