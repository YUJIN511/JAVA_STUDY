import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_2573_빙산 {
	
	static int N;
	static int M;
	static int size;
	static int map[][];
	static int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int result;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 열
		M = Integer.parseInt(st.nextToken());	// 행
		
		size=0;
		map = new int[N][M];	// 빙하 정보
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]!=0) {
					size++;
				}
			}
		}
		
		result=-1;
		top:
		while(true) {
			result++;
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					if(map[r][c]!=0) {
						if(melt(r, c))	continue top;		// 빙산 녹이기
						else		break top;
					}
				}
			}
			result=0;	// 분리되지 않는 경우
			break top;
		}
		
		System.out.println(result);
	
	}

	private static boolean melt(int r, int c) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		boolean visited[][] = new boolean[N][M];
		
		queue.offer(new int[] {r,c});
		visited[r][c]=true;
		
		int check=0;
		int cur[];
		while(!queue.isEmpty()) {
			cur = queue.poll();
			r = cur[0];
			c = cur[1];
						
			int sea = 0;
			for(int d=0; d<4; d++) {	// 주변 빙산과 바다 체크
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
						
				if(nr<0 || nr>=N || nc<0 || nc>=M || visited[nr][nc]) continue;
						
				if(map[nr][nc]==0) {	// 바다 체크
					sea++;
					continue;
				}
				queue.offer(new int[] {nr, nc});
				visited[nr][nc]=true;
			}
			
			if(map[r][c]-sea <= 0) {	// 빙산이 다 녹을 경우
				map[r][c] =0;
				size--;
			}
			else {
				map[r][c]-=sea;
				check++;
			}
		}
		
		if(check==size)	return true;
		else			return false;
	}
}
