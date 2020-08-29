import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class brandi_물난리 {
	static int N;
	static int house[][];
	static boolean visited[][];
	static int flooded[][];
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static int emptyCount;
	static int max;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());	// 집의 크기
		house = new int[N][N];
		visited = new boolean[N][N];
		flooded = new int[N][N];
		emptyCount=0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
				if(house[i][j]==0)	emptyCount++;
			}
		}
		max=0;
		BFS();
		
		System.out.println(max);
	}

	private static void BFS() {
		
		ArrayDeque<int[]> water = new ArrayDeque<>();
		ArrayDeque<int[]> hong = new ArrayDeque<>();
		
		water.offer(new int[] {0,0,1});
		hong.offer(new int[] {N-1,N-1,1});
		flooded[0][0] = 1;
		visited[N-1][N-1] = true;
		
		while(!water.isEmpty() && !hong.isEmpty()) {
			int W[] = water.poll();
			int H[] = hong.poll();
			
			house[H[0]][H[1]]=1;	// 벽 세우기
			int cnt = check();		// 안전한 공간 세기
			house[H[0]][H[1]]=0;	// 벽 세우기
			max = Math.max(max, cnt);	// 안전한 최대 공간
			
			for(int d=0; d<4; d++) {
				int WR = W[0] + dir[d][0];
				int WC = W[1] + dir[d][1];
				
				if(WR<0 || WR>=N || WC<0 || WC>=N || house[WR][WC]==1 || flooded[WR][WC]!=0) continue;
				
				water.offer(new int[] {WR,WC,W[2]+1});
				flooded[WR][WC]=W[2]+1;
			}
			
			for(int d=0; d<4; d++) {
				int HR = H[0] + dir[d][0];
				int HC = H[1] + dir[d][1];
				int Hcnt = H[2]+1;
				
				if(HR<0 || HR>=N || HC<0 || HC>=N || house[HR][HC]==1 || visited[HR][HC]
						|| (flooded[HR][HC]!=0 &&flooded[HR][HC]<=Hcnt)) continue;
				
				hong.offer(new int[] {HR,HC,Hcnt});
				visited[HR][HC]=true;
			}
		}
	}

	private static int check() {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0,0,1});
		
		int tempCnt = emptyCount-1;
		boolean tempFlooded[][] = new boolean[N][N];
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int d=0; d<4; d++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N || house[nr][nc]==1 || tempFlooded[nr][nc]) continue;
				
				q.offer(new int[] {nr,nc,cur[2]+1});				
				tempFlooded[nr][nc]=true;
				tempCnt--;
			
			}
		}
		return tempCnt;
	}
}
