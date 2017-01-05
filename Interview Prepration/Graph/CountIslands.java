package GeeksForGeeks.Graph;

public class CountIslands {

	public static boolean isSafe(boolean[][] visited, int[][] sea, int R, int C){
		return (R >= 0) && (R < 5) && (C >= 0) && (C < 5) && (!visited[R][C]) && (sea[R][C] == 1);
	}
	public static int countIslands(int[][] sea){
		boolean[][] visited = new boolean[5][5];
		int count = 0;
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				if(!visited[i][j] && sea[i][j] == 1){
					countIslandsUtil(visited, sea, i, j);
					count++;
				}
			}
		}
		return count;
	}
	
	public static void countIslandsUtil(boolean[][] visited, int[][] sea, int i, int j){
		visited[i][j] = true;
		int[] row = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] col = {-1, 0, 1, -1, 1, -1, 0, 1};
		
		for(int p = 0; p < 8; p++){
			if(isSafe(visited, sea, i + row[p], j + col[p])){
				countIslandsUtil(visited, sea, i + row[p], j + col[p]);
			}
		}		
	}
	
	public static void main(String[] args) {
		int[][] sea = {{1, 1, 0, 0, 0},
                	   {0, 1, 0, 0, 1},
                	   {1, 0, 0, 1, 1},
                	   {0, 0, 0, 0, 0},
                	   {0, 0, 1, 0, 1}};
		System.out.println("Number of Islands: " + countIslands(sea));
	}
}
