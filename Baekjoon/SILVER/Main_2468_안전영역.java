import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_2468_안전영역 {
	static int N;
	static int map[][];
	static boolean visited[][];
	static int dir[][] = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int min = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {	// 배열 정보 입력
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(min, map[i][j]);
			}
		}
		
		int count=1;
		int result=1;	// 아무지역도 안잠길 수 있으므로 최소 1
		while(count!=0) {
			visited = new boolean[N][N];
			count=0;
			for(int i=0; i<N; i++) {	// 잠기는 지역 체크
				for(int j=0; j<N; j++) {
					if(map[i][j]<=min) {
						visited[i][j]=true;
					}
				}
			}
		
			for(int i=0; i<N; i++) {	// 물에 안잠기는 영역 개수 세기
				for(int j=0; j<N; j++) {
					if(!visited[i][j]) {	// 안잠기는 지역중 안 센 영역 
						BFS(i,j);
						count++;
					}
				}
			}
			min++;
			result = Math.max(count, result);
		}
		
		System.out.println(result);
	}

	private static void BFS(int r, int c) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			r = cur[0];
			c = cur[1];
			
			for(int d=0; d<4; d++) {
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc])	continue;
				
				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
		
	}
}
