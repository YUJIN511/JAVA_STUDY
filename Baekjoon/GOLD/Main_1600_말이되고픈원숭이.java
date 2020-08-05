import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_1600_말이되고픈원숭이 {
	static int map[][];
	static boolean visited[][][];
	static int K;
	static int W;
	static int H;
	static int dirM[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static int dirH[][] = {{-2,-1},{-2,1},{2,-1},{2,1},{-1,-2},{-1,2},{1,-2},{1,2}};
	static int result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		result = Integer.MAX_VALUE;
		BFS(0,0);
		
		System.out.println(result==Integer.MAX_VALUE? -1 : result);
	}

	private static void BFS(int r, int c) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		visited = new boolean[H][W][K+1];
		
		queue.offer(new int[] {r,c,0,0});	// 행, 열, 말움직임, 동작수
		visited[r][c][0]=true;
		
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			r = cur[0];
			c = cur[1];
			int k = cur[2];
			int cnt = cur[3];
			
			if(r == H-1 && c == W-1) {
				result = cnt;
				return;
			}
			if(k < K) {	// K번만 말 처럼 움직일  수 있으므로
				for(int d=0; d<8; d++) {
					int nr = r + dirH[d][0];
					int nc = c + dirH[d][1];
					
					if(nr<0 || nr>=H || nc<0 || nc>=W || visited[nr][nc][k+1] || map[nr][nc]==1)	continue;
					queue.offer(new int[] {nr,nc,k+1, cnt+1});
					visited[nr][nc][k+1]=true;
				}
			}			
			
			for(int d=0; d<4; d++) {
				int nr = r + dirM[d][0];
				int nc = c + dirM[d][1];
				
				if(nr<0 || nr>=H || nc<0 || nc>=W || visited[nr][nc][k] || map[nr][nc]==1)	continue;
				queue.offer(new int[] {nr,nc,k, cnt+1});	
				visited[nr][nc][k]=true;
			}
			
		}
		
	}
}
