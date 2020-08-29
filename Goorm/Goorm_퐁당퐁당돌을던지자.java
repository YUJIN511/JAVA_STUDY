import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class brandi_퐁당퐁당돌을던지자 {
	static int N;
	static int D;
	static int map[][];
	static boolean visited[][];
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 격자판의 크기
		D = Integer.parseInt(st.nextToken());	// 냇물이 퍼져나가는 거리
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int max=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 0) {
					visited = new boolean[N][N];
					max = Math.max(max, BFS(i,j,1));
				}
			}
		}
		
		System.out.println(max);
	}

	private static int BFS(int r, int c, int cnt) {
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r,c,cnt});
		visited[r][c] = true;
		int waterCnt = 1;
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			r = cur[0];
			c = cur[1];
			cnt = cur[2];
			
			if(cnt>D)	return waterCnt;
			
			for(int d=0; d<4; d++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N || map[nr][nc]==1 || visited[nr][nc]) continue;
				
				q.offer(new int[] {nr,nc,cnt+1});				
				visited[nr][nc]=true;
				waterCnt++;
			
			}
		}
		
		return waterCnt;
	}
}
