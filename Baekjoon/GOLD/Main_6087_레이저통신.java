import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_6087_레이저통신 {

	static int W;
	static int H;
	static char map[][];
	static ArrayList<int[]> C;
	static int visited[][][];
	static int dir[][] = {{-1,0},{0,1},{1,0},{0,-1}};
	static int result;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W =  Integer.parseInt(st.nextToken());
		H =  Integer.parseInt(st.nextToken());
		
		map = new char[H][W];
		visited = new int[H][W][6];
		C = new ArrayList<>();
		
		for(int i=0; i<H; i++) {
			String str = br.readLine();
			map[i] = str.toCharArray();
			for(int j=0; j<W; j++) {
				if(map[i][j] == 'C') C.add(new int[] {i,j});
				Arrays.fill(visited[i][j], Integer.MAX_VALUE);
			}
		}
		
		result = Integer.MAX_VALUE;
		bfs();
		System.out.println(result);
		
	}

	private static void bfs() {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		int r = C.get(0)[0];
		int c = C.get(0)[1];
		int prvd = 0;
		int d = 0;
		int cnt = 0;
		
		for(int i=0; i<4; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			if(nr<0 || nr>=H || nc<0 || nc>=W || map[nr][nc]=='*')	continue;
			
			if(visited[nr][nc][i] > cnt) {
				queue.offer(new int[] {nr,nc,i,i,cnt});
				visited[nr][nc][i]=cnt;				
			}
			if(visited[nr][nc][4] > cnt+1) {
				queue.offer(new int[] {nr,nc,i,4,cnt+1});	// '/'=4
				visited[nr][nc][4]=cnt+1;			
			}
			if(visited[nr][nc][5] > cnt+1) {
				queue.offer(new int[] {nr,nc,i,5,cnt+1});	// '\'=5
				visited[nr][nc][5]=cnt+1;				
			}
		}
	
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			r = cur[0];
			c = cur[1];
			prvd = cur[2];
			d = cur[3];
			cnt = cur[4];
			
			if(r == C.get(1)[0] && c == C.get(1)[1]) {
				result = Integer.min(result, cnt);
				continue;
			}
			
			int nd;
			if(d == 4) {
				if(prvd==0) 		nd = 1;
				else if(prvd==1)	nd = 0;
				else if(prvd==2) 	nd = 3;
				else 				nd = 2;
				
			} else if(d==5) {
				if(prvd==0) 		nd = 3;
				else if(prvd==1) 	nd = 2;
				else if(prvd==2) 	nd = 1;
				else 				nd = 0;
			
			} else {
				nd = d;
			}
			
			int nr = r + dir[nd][0];
			int nc = c + dir[nd][1];
			if(nr<0 || nr>=H || nc<0 || nc>=W ||  map[nr][nc]=='*')	continue;
	
			
			if(visited[nr][nc][nd] > cnt) {
				queue.offer(new int[] {nr,nc,nd,nd,cnt});
				visited[nr][nc][nd]=cnt;				
			}
			if(visited[nr][nc][4] > cnt+1) {
				queue.offer(new int[] {nr,nc,nd,4,cnt+1});	// '/'=4
				visited[nr][nc][4]=cnt+1;			
			}
			if(visited[nr][nc][5] > cnt+1) {
				queue.offer(new int[] {nr,nc,nd,5,cnt+1});	// '\'=5
				visited[nr][nc][5]=cnt+1;				
			}
		}
	}

}
