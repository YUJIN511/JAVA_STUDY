import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_2140_지뢰찾기 {
	static int board[][];
	static int N;
	static int dir [][] = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		N = Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				board[i][j] = str.charAt(j)-'0';
			}
		}
		if(N==1) {
			System.out.println(0);
			return;
		}
		DFS(0,0,0);
	}
	private static void DFS(int sr, int sc, int sd) {
		
		// 다 체크한 경우
		if(sd!=0 && sr==0 && sc ==0) {
			int result = 0;
			for(int i=1; i<N-1; i++) {
				for(int j=1; j<N-1; j++) {
					if(board[i][j]== -1 || (i!=1 && i!=N-2 && j!=1 && j!=N-2))	result++;	// 지뢰 개수 세기
				}
			}
			System.out.println(result);
			System.exit(0);
		}
		
		if(board[sr][sc] == 0) {	// 주위에 지뢰가 없다는 의미
			int nr = sr + dir[sd][0];
			int nc = sc + dir[sd][1];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N)	sd+=2;
			
			DFS(sr + dir[sd][0], sc + dir[sd][1], sd);	// 테두리 좌표 이동
			return;
		}
		
		for(int d=0; d<8; d++) {
			int r = sr + dir[d][0];
			int c = sc + dir[d][1];
			
			if(r<0 || r>=N || c<0 || c>=N || board[r][c] != '#'-'0')	continue;	// 경계 체크
			board[r][c]=0;	// 한 번 지나간 자리는 0아니면 -1 (안 지나간 자리는 '#')
			
			if(arroundCheck(r, c)) {	// 지뢰 놓을 수 있는지 확인
				int tempboard [][] = new int[N][N];
				// 배열 복사
				for(int i=0; i<N; i++) {
					System.arraycopy(board[i], 0, tempboard[i], 0, board[i].length);
				}
				// 지뢰 주위 숫자 -1
				for(int nd=0; nd<8; nd++) {
					int nr = r + dir[nd][0];
					int nc = c + dir[nd][1];
					
					if(nr==0 || nr==N-1 || nc==0 || nc==N-1) board[nr][nc]--;
					
				}
				board[r][c] = -1;	//  지뢰 놓기
				
				int nr = sr + dir[sd][0];
				int nc = sc + dir[sd][1];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N)	sd+=2;
				
				DFS(sr + dir[sd][0], sc + dir[sd][1], sd);	// 테두리 좌표 이동
				
				board = tempboard;	// 원래대로
			}
		}
		return;
	}
	private static boolean arroundCheck(int r, int c) {
		
		for(int d=0; d<8; d++) {
			int nr = r + dir[d][0];
			int nc = c + dir[d][1];
			
			if(nr==0 || nr==N-1 || nc==0 || nc==N-1) {
				if(board[nr][nc] == 0) return false; 	// 지뢰를 놓을 수 없다
			}
		}
		
		return true;	// 지뢰 놓을 수 있다.
	}
}
