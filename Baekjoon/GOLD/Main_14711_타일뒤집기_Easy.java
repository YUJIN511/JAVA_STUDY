import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_14711_타일뒤집기_Easy {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		char board[][] = new char[N][N];
		boolean visited[][] = new boolean[N][N];
		int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};
		
		board[0] = br.readLine().toCharArray();
		
		for(int r=0; r<N-1; r++) {
			for(int c=0; c<N; c++) {
				
				if(board[r][c] == '#') {
					
					for(int d=1; d<4; d++) {
						int nr = r + dir[d][0];
						int nc = c + dir[d][1];
						
						if(nr<0 || nr>=N || nc<0 || nc>=N)	continue;
						
						visited[nr][nc]=!visited[nr][nc]; 
					}
				}
			}
			
			for(int c=0; c<N; c++) {
				if(visited[r][c])	board[r+1][c]='#';
				else				board[r+1][c]='.';
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		
	}
}
