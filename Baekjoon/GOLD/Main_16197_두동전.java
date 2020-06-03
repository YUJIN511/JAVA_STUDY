import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16197_두동전 {
	static int N;
	static int M;
	static char board[][];
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static int min;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		int coin[][] = new int[2][2];
		int idx=0;
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			board[i] = str.toCharArray();
			for(int j=0; j<M; j++) {
				if(board[i][j] == 'o') {
					coin[idx][0]=i;
					coin[idx++][1]=j;
					board[i][j]='.';
				}
			}
		}
		min = Integer.MAX_VALUE;
		DFS(1, coin[0][0], coin[0][1], coin[1][0], coin[1][1]);

		
		System.out.println(min==Integer.MAX_VALUE? -1 : min);
	}

	private static void DFS(int cnt, int r1, int c1, int r2, int c2) {
		if(cnt > 10)	return;	// 10번 이상이면 끝

		for(int d=0; d<4; d++) {
			int check=0;
			
			int nr1 = r1 + dir[d][0];
			int nc1 = c1 + dir[d][1];
			int nr2 = r2 + dir[d][0];
			int nc2 = c2 + dir[d][1];
				
			if(nr1<0 || nr1>=N || nc1<0 || nc1>=M) {	// 떨어진경우
				check++;
			} else if(board[nr1][nc1]=='#'){	// 벽인 경우
				nr1 = r1;
				nc1 = c1;
			}
			
			if(nr2<0 || nr2>=N || nc2<0 || nc2>=M) {	// 떨어진경우
				check++;
			} else if(board[nr2][nc2]=='#'){	// 벽인 경우
				nr2 = r2;
				nc2 = c2;
			}
			
			if(check>0) {	// 동전이 떨어진 경우
				if(check==1) {
					min = Integer.min(cnt, min);
					return;	
				}
				continue;
			}
		
			DFS(cnt+1, nr1, nc1, nr2, nc2);
		}
	}
}
