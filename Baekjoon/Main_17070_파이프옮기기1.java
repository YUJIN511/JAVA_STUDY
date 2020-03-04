import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070_파이프옮기기1 {
	
	static int N;
	static int result;
	static int house[][];	// 오른쪽, 아래 , 대각선
	static int dir[][]= {{}, {0,1}, {1, 1}, {1, 0}};
	static boolean visited[][];

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		house = new int [N+1][N+1];
		visited = new boolean [N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				house[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		visited[1][1]=true;
		visited[1][2]=true;
		result=0;
		pipe(1,2,1);
		
		System.out.println(result);
	}
	private static void pipe(int r, int c, int d) {
		if(r==N && c==N) {
			result++;
			return;
		}
		int nr;
		int nc;
		for(int i=1; i<=3; i++) {
			if(d==1 && i==3)	continue;	// 파이프를 밀 수 있는 방향만 밀도록
			if(d==3 && i==1)	continue;	
			
			nr=r+dir[i][0];
			nc=c+dir[i][1];
			if(nr<=N && nc<=N && !visited[nr][nc] && house[nr][nc]!=1) {	// 경계 체크
				if(i==2 && (house[nr-1][nc]==1 || house[nr][nc-1]==1)) continue;	// 대각선일 때 비어있어야하는 칸 체크
				visited[nr][nc]=true;
				pipe(nr, nc, i);
				visited[nr][nc]=false;
			}
		}
		
	}
}
