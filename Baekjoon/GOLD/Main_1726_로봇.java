import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_1726_로봇 {
	
	static int M;
	static int N;
	static int map[][];
	static boolean visited[][][];
	static int end[];
	static int dir[][] = {{0,0}, {0,1},{0,-1},{1,0},{-1,0}};
	static int left[] = {0,4,3,2,1};
	static int right[] = {0,3,4,1,2};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
	
		map= new int[M+1][N+1];
		visited= new boolean[M+1][N+1][5];
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int start[] = new int[3];
		end = new int[3];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<3; i++) {
			start[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<3; i++) {
			end[i] = Integer.parseInt(st.nextToken());
		}
		
		int result = BFS(start[0], start[1], start[2]);
		
		System.out.println(result);
	}

	private static int BFS(int r, int c, int d) {
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c, d, 0});
		visited[r][c][d]=true;
		
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			r = cur[0];
			c = cur[1];
			d = cur[2];
			int cnt = cur[3];
			
			// 성공
			if(end[0]==r && end[1]==c && end[2]==d)	return cnt;
		
			// GO k
			for(int k = 1; k<=3; k++) {
				int nr = r + dir[d][0]*k;
				int nc = c + dir[d][1]*k;
				
				if(nr<1 || nr>M || nc<1 || nc>N || map[nr][nc]==1)	break;
				if(!visited[nr][nc][d]) {
					queue.offer(new int[] {nr, nc, d, cnt+1});
					visited[nr][nc][d]=true;
				}
			}
			
			if(!visited[r][c][left[d]]) {
				// left 90도
				queue.offer(new int[] {r,c,left[d], cnt+1});
				visited[r][c][left[d]]=true;
			}
			if(!visited[r][c][right[d]]) {
				// right 90도
				queue.offer(new int[] {r,c,right[d], cnt+1});
				visited[r][c][right[d]]= true;
			}
			
		}
		return -1;
	}
}
