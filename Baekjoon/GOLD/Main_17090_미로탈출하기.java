import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_17090_미로탈출하기 {
	
	static int N;
	static int M;
	static int map[][]; // U(0)   D(1)  L(2)   R(3)
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static int visited[][];
	static int result;
	static int idx;
	static Set<Integer> set;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 미로의 크기
		N = Integer.parseInt(st.nextToken());	
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new int[N][M];
		set = new HashSet<>();
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				if(str.charAt(j)=='U')		map[i][j] = 0;
				else if(str.charAt(j)=='D')	map[i][j] = 1;
				else if(str.charAt(j)=='L')	map[i][j] = 2;
				else  						map[i][j] = 3;
			}
		}
		
		result = 0;
		idx=1;	// 0은 방문 안한, 1이상은 방문 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j]==0) {
					BFS(i,j, 1);
					idx++;
				}
			}
		}
		
		System.out.println(result);
		return;
	}

	private static void BFS(int r, int c, int cnt) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r,c,cnt});
		visited[r][c] = idx;
		
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			r = cur[0];
			c = cur[1];
			cnt = cur[2];
			int d = map[r][c];
			
			int nr = r + dir[d][0];
			int nc = c + dir[d][1];
			
			if(nr<0 || nr>=N || nc<0 || nc>=M) {	// 경계 체크
				set.add(idx);	// set에는 성공 인덱스만 
				result+=cnt;	// 탈출 카운트
				continue;	
			}
			
			if(visited[nr][nc] > 0) {	// 이동할 곳 방문했었는지 체크
				if(set.contains(visited[nr][nc])) {	// 탈출 성공했던 곳인지 확인
					set.add(idx);
					result+=cnt;
				}
				continue; 
			}
			queue.offer(new int[] {nr,nc,cnt+1});
			
			visited[nr][nc] = idx;
			
		}
	}

}
