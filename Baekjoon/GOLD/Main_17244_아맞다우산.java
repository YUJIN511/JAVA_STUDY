import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_17244_아맞다우산 {

	static char map[][];
	static boolean visited[][][];
	static int dir[][] ={{-1,0},{1,0},{0,-1},{0,1}};
	static int CHECK;
	
	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 가로
		int M = Integer.parseInt(st.nextToken());	// 세로
		map = new char[M][N];
		visited = new boolean[M][N][1<<5];
		int S[] = new int[2];
		
		int num=0;
		CHECK=0;
		for(int i=0; i<M; i++) {
			String str = br.readLine();
			map[i] = str.toCharArray();
			for(int j=0; j<N; j++) {
				if(map[i][j] == 'S') {			// 시작 위치
					S= new int[] {i,j};
				} else if(map[i][j] == 'X') {	// 물건 인덱스
					map[i][j] = (char) (num +'0');
					CHECK |= 1<<num++;
				}
			}
		}
		
		int result=0;
		result = BFS(S[0], S[1], 0, 0);
		System.out.println(result);
		
	}

	private static int BFS(int r, int c, int cnt, int bit) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r,c, cnt, bit});
		visited[r][c][bit]=true;
		
		while(!queue.isEmpty()) {
			int cur [] = queue.poll();
			r = cur[0];
			c = cur[1];
			cnt = cur[2];
			bit = cur[3];
			
			if(map[r][c] == 'E') {
				if(bit == CHECK) 	return cnt;		// 물건을 다 챙긴 경우
				else				continue;
			}
			
			for(int d=0; d<4; d++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				int nb = bit;
				
				if(map[nr][nc]=='#' || visited[nr][nc][bit])	continue;	// 경계 체크
				
				if(!".SE".contains(map[nr][nc]+"")) {
					nb = bit|1<<(map[nr][nc]-'0');	// 물건 있는 곳
				}
	
				queue.offer(new int[] {nr, nc, cnt+1, nb});
				visited[nr][nc][nb] = true;
			}
		}
		
		return -1;
	}
}
