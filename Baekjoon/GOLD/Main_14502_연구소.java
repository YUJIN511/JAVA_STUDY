import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_14502_연구소 {
	
	static int map[][];
	static int temp_map[][];
	static boolean selected[][];
	static int dir[][]= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N;
	static int M;
	static int max;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 세로 N
		M = Integer.parseInt(st.nextToken());	// 세로 M
		
		map = new int [N][M];
		selected = new boolean [N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		max=0;
		wall(0);	// 벽 세우기
		
		System.out.println(max);
	}

	private static void wall(int cnt) {
		if(cnt ==3) {
			temp_map = new int [N][M];
			for(int r=0; r<N; r++) {
				for(int c =0; c<M; c++) {
					temp_map[r][c]=map[r][c];
				}
			}
			
			for(int r=0; r<N; r++) {
				for(int c =0; c<M; c++) {
					if(temp_map[r][c]==2) {
						virus(r, c); // 바이러스 전파
					}
				}
			}
			
			// 안전 영역 크기 구하기
			int result=0;
			for(int r=0; r<N; r++) {
				for(int c =0; c<M; c++) {
					if(temp_map[r][c]==0) {
						result++;
					}
				}
			}
			max = Math.max(result, max);
			
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!selected[i][j] && map[i][j]==0) {
					selected[i][j]=true;
					map[i][j]=1;
					wall(cnt+1);
					selected[i][j]=false;		
					map[i][j]=0;
				}
			}
		}
	}

	private static void virus(int r, int c) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		
		int cur[];
		int nr;
		int nc;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			r= cur[0];
			c= cur[1];
			
			for(int d=0; d<4; d++) {
				nr = r + dir[d][0];
				nc = c + dir[d][1];
				
				if(nr>-1 && nr<N && nc>-1 && nc<M && temp_map[nr][nc]==0) {
					queue.offer(new int[] {nr, nc});
					temp_map[nr][nc]=2;
				}
			}
			
		}
	}
}
