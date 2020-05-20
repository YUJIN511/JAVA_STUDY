import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_14923_미로탈출 {
	static int N;
	static int M;
	static int Ex;
	static int Ey;
	static int map[][];
	static int visited[][][];
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static int result;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int Hx = Integer.parseInt(st.nextToken());
		int Hy = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		Ex = Integer.parseInt(st.nextToken());
		Ey = Integer.parseInt(st.nextToken());
		
		map = new int [N+1][M+1];
		visited = new int[N+1][M+1][2];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				visited[i][j][0] = Integer.MAX_VALUE;
				visited[i][j][1] = Integer.MAX_VALUE;
			}
		}
		
		result = Integer.MAX_VALUE;
		BFS(Hx, Hy, 0);
		
		System.out.println(result==Integer.MAX_VALUE? -1 : result);
	}
	private static void BFS(int x, int y, int time) {

		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {x, y, time, 0});
		visited[x][y][0]=time;
		
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			x = cur[0];
			y = cur[1];
			time = cur[2];
			int check = cur[3];		// 0: 벽 부신적 없음    1: 있음
			
			if(x == Ex && y == Ey)	{	// 도착
				result = time;
				return;
			}
			
			for(int d=0; d<4; d++) {
				int nx = x + dir[d][0];
				int ny = y + dir[d][1];
				
				if(nx<1 || nx>N || ny<1 || ny>M )	continue;	// 경계체크

				if(map[nx][ny] == 0) {	// 빈칸
					if(visited[nx][ny][check] > time+1) {		// [0] : 벽을 안부신 최단 거리    [1] : 벽을 부신 최단거리
						queue.offer(new int[] {nx, ny, time+1,check});
						visited[nx][ny][check] = time+1;
					}
				} else {	// 벽
					if(check==0 && visited[nx][ny][1] > time+1) {
						queue.offer(new int[] {nx, ny, time+1, 1});
						visited[nx][ny][1] = time+1;
					}
				}
				
			}
		}
	}
}
