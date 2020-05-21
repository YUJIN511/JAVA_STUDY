import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_1194_달이차오른다가자 {
	static int N;	// 세로
	static int M;	// 가로
	static char maze[][];
	static boolean visited[][][];
	static int dir[][] = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	
	static class info{
		int r;
		int c;
		int mv;
		int key;
		
		public info(int r, int c, int mv, int key) {
			super();
			this.r = r;
			this.c = c;
			this.mv = mv;
			this.key=key;
		}
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new char[N][M];
		visited = new boolean[N][M][1<<6];
		int r=-1;
		int c=-1;
		for(int i=0; i<N; i++) {	// 미로 정보 입력
			String str = br.readLine();
			maze[i]=str.toCharArray();
			
			for(int j=0; j<M; j++) {
				if(maze[i][j]=='0') {	// 민식이 출발 좌표
					r=i;
					c=j;
				}
			}
		}
		int result = BFS(r, c, 0, 0);
		
		
		System.out.println(result);
	}
	private static int BFS(int r, int c, int mv, int key) {
		ArrayDeque<info> queue = new ArrayDeque<>();
		queue.offer(new info(r, c, mv, key));
				
		while(!queue.isEmpty()) {
			info cur = queue.poll();
			r = cur.r;
			c = cur.c;
			mv = cur.mv;
			key=cur.key;
			
			if(maze[r][c] == '1') {	// 미로 탈출
				return mv;
			}
			
			for(int d=0; d<4; d++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				int nkey = key;
				if(nr<0 || nr>=N || nc<0 || nc>=M || maze[nr][nc] == '#' || visited[nr][nc][key])	continue;
				
				
				if(maze[nr][nc]>='a' && maze[nr][nc]<='f') {	// 열쇠 발견
					nkey |= (1 << (maze[nr][nc]-'a'));
				} else if(maze[nr][nc]>='A' && maze[nr][nc]<='F') {	// 문 발견
					if( (nkey & (1<<(maze[nr][nc]-'A'))) == 0) 	continue;	// 대응하는 열쇠가 없을 경우
				} 
				
				queue.offer(new info(nr, nc, mv+1, nkey));
				visited[nr][nc][key] = true;
			}
		}
		return -1;
	}
}
