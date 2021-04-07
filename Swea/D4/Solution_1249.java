import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_1249 {
	static int N;
	static int map[][];
	static int visited[][];
	static int dir[][] = {{1,0},{-1,0},{0,-1},{0,1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase=1; testCase<=T; testCase++) {
			N = Integer.parseInt(br.readLine());	// 지도 크기
			map = new int[N][N];
			visited = new int[N][N];
			
			for(int i=0; i<N; i++) {
				String str = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = str.charAt(j)-'0';
					visited[i][j] = Integer.MAX_VALUE;
				}
			}
			
			BFS();

			System.out.println("#"+testCase+" "+visited[N-1][N-1]);
		}
		
	}
	private static void BFS() {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {0,0});
		visited[0][0]=0;
		
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			int y=cur[0];
			int x=cur[1];
			
			for(int d=0; d<4; d++) {
				int nx = x + dir[d][0];
				int ny = y + dir[d][1];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N || visited[ny][nx] <= visited[y][x] + map[ny][nx])	continue;
				
				queue.offer(new int[] {ny,nx});
				visited[ny][nx] = visited[y][x]+map[ny][nx];
			}
		}
	}
}
