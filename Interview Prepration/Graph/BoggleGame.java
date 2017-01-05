package GeeksForGeeks.Graph;

import java.util.Dictionary;

public class BoggleGame {
	static String[] dict = {"GEEKS", "FOR", "QUIZ", "GO"};
	static int dictSize = 4;
	
	public static boolean isWord(String str){
		for(int i = 0; i < dictSize; i++){
			if(str.compareTo(dict[i]) == 0){
				return true;
			}
		}
		return false;
	}
	
	public static void findWords(char[][] board){
		boolean[][] visited = new boolean[3][3];
		String str = "";
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(!visited[i][j]){
					findWordsUtil(visited, board, str, i, j);
				}
			}
		}
	}
	
	public static void findWordsUtil(boolean[][] visited, char[][] board, String str, int i, int j) {
		visited[i][j] = true;
		str = str + board[i][j];
		if (isWord(str)) {
			System.out.println(str);
		}
		for (int row = i - 1; row <= i + 1 && row < 3; row++) {
			for (int col = j - 1; col <= j + 1 && col < 3; col++) {
				if(row >= 0 && col >= 0 && !visited[row][col])
				findWordsUtil(visited, board, str, row, col);
			}
		}
		str =  str.substring(0, str.length()-1);
		visited[i][j] = false;
	}
	
	public static void main(String[] args) {
		char[][] board =  {{'G','I','Z'},
                		   {'O','E','K'},
                		   {'Q','S','E'}};
		System.out.println("Words possible on board: ");
		findWords(board);	
	}
}
