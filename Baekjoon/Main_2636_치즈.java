import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_2636_치즈 {
	static int N;
	static int M;
	static int map[][];
	static boolean visited[][];
	static ArrayDeque<int[]> cheese;
	static int dir[][] = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static int size;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 세로
		M = Integer.parseInt(st.nextToken());	// 가로
		map = new int[N][M];
		cheese = new ArrayDeque<>();
		size = 0;	// 치즈 갯수
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1)	size ++;
			}
		}
		
		int result=0;
		int time=0;
		while(true) {
			visited  = new boolean[N][M];
			time++;
			result = size;
			
			bfs(0,0);
			
			if(size==0) {
				break;
			}
		}
		System.out.println(time);
		System.out.println(result);
	}


	private static void bfs(int r, int c) {
		
		ArrayDeque<int []> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		visited[r][c]=true;
		
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			r = cur[0];
			c = cur[1];
		
			for(int d=0; d<4; d++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
						
				if(nr<0 || nr>=N || nc<0 || nc>=M || visited[nr][nc])	continue;
				
				if(map[nr][nc]==1) {	// 녹게될 치즈
					cheese.offer(new int[] {nr,nc});
					map[nr][nc]=0;
					size--;
				}
				else {	// 빈공간
					queue.offer(new int[] {nr, nc});
				}
				visited[nr][nc]=true;
			}
		}
	}
	
}
