import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_9944_NxM보드 {
	
	static char board[][];
	static boolean visited[][];
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};//상하좌우
	static int emptyCount;
	static int result;
	static int N;
	static int M;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase=1;
		String input;
		StringTokenizer st;
		
		while((input = br.readLine()) != null) {
			st = new StringTokenizer(input);
			
			N = Integer.parseInt(st.nextToken());	// 세로
			M = Integer.parseInt(st.nextToken());	// 가로
			
			board = new char[N][M];
			visited = new boolean[N][M];
			emptyCount = 0;
			
			// 정보 입력
			for(int i=0; i<N; i++) {
				String str = br.readLine();
				board[i] = str.toCharArray();
				for(int j=0; j<M; j++) {
					if(board[i][j] == '.')	emptyCount ++;
				}
			}
			
			result=Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(board[i][j] == '.') {
						visited[i][j] = true;
						emptyCount--;
						DFS(i,j, 0);
						visited[i][j] = false;
						emptyCount++;
					}
				}
			}
			
			System.out.println("Case "+testcase+": "+(result==Integer.MAX_VALUE?-1:result));
			testcase++;
		}
	}

	private static void DFS(int r, int c, int cnt) {
		
		if(emptyCount==0) {	// 모든 빈칸을 방문한 경우
			result = Math.min(result, cnt);	// 최소 이동 횟수
			return;
		}
		
		for(int d=0; d<4; d++) {

			int next[] = check(r+dir[d][0], c+dir[d][1], d);	// 방문 체크
			
			if(next[0]==r && next[1]==c) continue;	// d방향으로 공이 이동할 수 없는 경우
			DFS(next[0], next[1], cnt+1);
			uncheck(r+dir[d][0],c+dir[d][1],d,next[2]);			// 방문 체크 해제
			
		}
	
	}

	private static void uncheck(int r, int c, int d, int moveCount) {
		while(moveCount-- > 0) {
			visited[r][c] = false;
			emptyCount++;
			
			r += dir[d][0];
			c += dir[d][1];
		}
	}

	private static int[] check(int r, int c, int d) {
		int moveCount=0;
		
		while(r>=0 && r<N && c>=0 && c<M && !visited[r][c] && board[r][c]=='.') {
			visited[r][c]=true;
			emptyCount--;
			moveCount++;

			r += dir[d][0];
			c += dir[d][1];
		}
		return new int[] {r-dir[d][0], c-dir[d][1], moveCount};
	}
}
