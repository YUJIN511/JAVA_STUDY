import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution_1953_탈주범검거 {
	static int N;	// 세로크기
	static int M;	// 가로크기
	static int L;	// 소요된 시간
	static int map[][];
	static boolean visited[][];
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};	// 0:상  1:하  2:좌  3:우
	static String tunnel[] = {"","0123","01","23","03","13","12","02"};
	static int result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); 
			M = Integer.parseInt(st.nextToken()); 
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new boolean [N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = 0;
			BFS(R, C, 1);
			
			System.out.println("#"+test_case+" "+result);
		}
		
	}
	private static void BFS(int r, int c, int t) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c, t});
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			r = cur[0];
			c = cur[1];
			t = cur[2];
			
			if(t>L)	continue;
			result++;	// 갈수 있는 경로 ++
			
			String type = tunnel[map[r][c]];
			for(int i=0; i<type.length(); i++) {
				int d = type.charAt(i)-'0';
				
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				// 경계 밖인지, 방문한적 있는지, 빈공간인지
				if(nr < 0 || nr>=N || nc<0 || nc>=M || visited[nr][nc] || map[nr][nc]==0)	continue;
				
				// 연결된 터널인지
				int nd=0;
				if(d==0 || d==2)	nd = d+1;
				else				nd = d-1;
				
				if(tunnel[map[nr][nc]].contains(nd+"")) {
					queue.offer(new int[] {nr, nc,t+1});
					visited[nr][nc]=true;
				}
			}
		}
		
	}
}
