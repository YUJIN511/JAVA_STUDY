import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1949_등산로조성 {
	static int N;
	static int map[][];
	static int result[][];
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			result = new int[N][N];
			ArrayList<int[]> list = new ArrayList<>();
			
			int max =0;
			for(int i=0; i<N; i++) {	// 정보 입력
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(max<map[i][j]) {
						max = map[i][j];
						list.clear();
						list.add(new int[] {i, j});
					}
					else if(max == map[i][j]) {
						list.add(new int[] {i,j});
					}
				}
			}
			
			for(int[] cur : list) {
				result[cur[0]][cur[1]] =1;
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					for(int k=0; k<=K; k++) {
						map[i][j]-=k;
						for(int[] cur : list) {
							if(cur[0]==i && cur[1]==j)	continue;	// 높은 봉우리가 안되므로
							BFS(cur[0], cur[1]);
						}
						map[i][j]+=k;
					}
				}
			}
			
			max = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					max = Math.max(max, result[i][j]);
				}
			}
			System.out.println("#"+test_case+" "+max);
		}
	
	}

	private static void BFS(int r, int c) {
		Queue<int []> q = new ArrayDeque<>();
		
		q.offer(new int[] {r, c, 1});
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			r = cur[0];
			c = cur[1];
			int l = cur[2];
			
			for(int d = 0; d<4; d++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N || map[r][c] <= map[nr][nc] || result[nr][nc] > l+1) continue;
				
				q.offer(new int[] {nr, nc, l+1});
				result[nr][nc] = Math.max(result[nr][nc], l+1);
			}
			
		}
	}
}
