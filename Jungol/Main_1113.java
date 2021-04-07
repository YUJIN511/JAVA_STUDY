import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_1113 {
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}}; // 상 하 좌 우
	static int M;	// 배열 크기
	static int N;
	static int m;	// 도착 지점
	static int n;
	static int map[][];
	static int result;
	static int visited[][];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
	
		map = new int[N+1][M+1];
		visited = new int[N+1][M+1];
		result = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				visited[i][j]=-1;
			}
		}
		BFS(0, 0, 0, -1);
		
		System.out.println(result);
		
	}

	private static void BFS(int y, int x, int cnt, int d) {
		ArrayDeque<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {y,x,cnt, d});
		visited[y][x]=0;
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			y = cur[0];
			x = cur[1];
			cnt = cur[2];
			d = cur[3];
			
			if(cnt>=result)	continue;
			
			if(y==m && x==n) {
				result = Math.min(result, cnt);
				continue;
			}
			
			for(int nd=0; nd<4; nd++) {
				int ny = y+dir[nd][0];
				int nx = x+dir[nd][1];
				
				if(ny<N && ny>=0 && nx<M && nx>=0 && map[ny][nx]==1) {
					
					if(d==-1 || d==nd) {	// 직진
						if(visited[ny][nx]==-1 || visited[ny][nx]>cnt) {
							q.offer(new int[] {ny, nx, cnt, nd});
							visited[ny][nx]=cnt;
						}
					} else {	// 코너
						if(visited[ny][nx]==-1 || visited[ny][nx]>cnt+1) {
							q.offer(new int[] {ny, nx, cnt+1, nd});
							visited[ny][nx]=cnt+1;
						}
					}
				}
			}
			
			
		}
		
	}
}
