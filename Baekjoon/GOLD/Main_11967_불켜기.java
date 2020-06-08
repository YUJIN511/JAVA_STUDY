import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_11967_불켜기 {
	static int N;
	static int result;
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static ArrayList<int[]> lights[];
	static boolean room[][];		// 방의 불이 켜있는지
	static boolean selected[][];	// 방의 스위치를 작동 했는지
	static boolean visited[][];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		lights = new ArrayList [N*N+1];
		room = new boolean[N+1][N+1];
		selected = new boolean[N+1][N+1];
		
		for(int i=0; i<N*N+1; i++) {
			lights[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			lights[(x-1)*N+y].add(new int[] {a,b});	// 방의 스위치 정보 입력
		}
		result=1;
		BFS(1,1);
		
		System.out.println(result);
	}
	private static void BFS(int r, int c) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		room[r][c]=true;
		selected[r][c]=true;
		for(int[] light : lights[(r-1)*N+c]) {
			if(!room[light[0]][light[1]]) {
				room[light[0]][light[1]]=true;	// 불 켜기
				result++;
			}
		}
		boolean flag=true;
		
		while(flag) {
			visited = new boolean[N+1][N+1];
			queue.offer(new int[] {1,1});
			visited[1][1]=true;
			
			flag=false;
			while(!queue.isEmpty()) {
				int cur[] = queue.poll();
				r = cur[0];
				c = cur[1];
				
				for(int d = 0; d < 4; d++) {
					int nr = r +dir[d][0];
					int nc = c +dir[d][1];
					
					if(nr<1 || nr>N || nc<1 || nc>N || !room[nr][nc] || visited[nr][nc])	continue;
					
					queue.offer(new int[] {nr, nc});
					visited[nr][nc]=true;
					if(!selected[nr][nc]) {	// 스위치 작동
						selected[nr][nc]=true;
						for(int[] light : lights[(nr-1)*N+nc]) {
							if(!room[light[0]][light[1]]) {
								room[light[0]][light[1]]=true;	// 불 켜기
								result++;
								flag=true;
							}
						}
					}
				}
			}
		}
	}
}
