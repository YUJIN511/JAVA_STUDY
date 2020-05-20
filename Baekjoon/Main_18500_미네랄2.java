import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_18500_미네랄2 {
	
	static int R;
	static int C;
	static int N;
	static int mineral;
	static char map[][];
	static boolean visited[][];
	static int dir[][] = {{-1,0}, {1,0},{0,-1},{0,1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		mineral =0;
		
		for(int i=0; i<R; i++) {	// 동굴 정보 입력
			String str = br.readLine();
			map[i] = str.toCharArray();
			for(int j=0; j<C; j++) {
				if(map[i][j]=='x')	mineral++;
			}
		}
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int d = 1;
		for(int i=0; i<N; i++) {
			int r = R - Integer.parseInt(st.nextToken());
			stick(r, d*=-1);	// 막대기 던짐
		}
		
		for(int i=0; i<R; i++) {	// 결과 출력
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	private static void stick(int r, int d) {	// d=-1 왼쪽		d=1 오른쪽
		int c;
		if(d==-1) {	// 왼쪽에서 던질경우
			c = 0;
			while(c<C && map[r][c]=='.')	c++;
			
		} else {	// 오른쪽에서 던질경우
			c = C-1;
			while(c>-1 && map[r][c]=='.')	c--;
		}
		
		if(c==C || c==-1)	return;	// 미네랄이 파괴되지 않는 경우
		
		map[r][c]='.';	// 미네랄 파괴
		mineral--;		// 미네랄 갯수 -1
		
		int count=0;
		visited = new boolean[R][C];
		for(int i=0; i<C; i++) {	// 바닥에서부터 BFS
			if(map[R-1][i]=='x' && !visited[R-1][i]) {
				count += BFS(R-1,i);
			}
		}
			
		if(count != mineral) {	// 공중에 떠 있는 미네랄이 있는 경우
			move();	// 미네랄 중력작용
		}
	}

	private static int BFS(int r, int c) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		visited[r][c]=true;
		int count=1;
		
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			r = cur[0];
			c = cur[1];
			
			for(int i=0; i<4; i++) {
				int nr = r + dir[i][0];
				int nc = c + dir[i][1];
				
				if(nr<0 || nr>=R || nc<0 || nc>=C || map[nr][nc]=='.' || visited[nr][nc])	continue;
				
				queue.offer(new int[] {nr, nc} );
				visited[nr][nc]=true;	
				count++;
			}
		}
		return count;
	}

	private static void move() {
		Set<Integer> set = new HashSet<>();
		int min = R;
		for(int c=0; c<C; c++) {
			int check=R;
			for(int r=R-1; r>=0; r--) {
				if(map[r][c]=='x') {
					if(!visited[r][c]) {
						min = Math.min(min, check-r-1);		// 떨어질 최소의 행 체크
						set.add(c);							// 떨어질 미네랄이 있는 열
					} else {
						check=r;
					}
				}
			}
		}
		
		for(int c : set) {
			for(int r=R-1; r>=0; r--) {
				if(map[r][c]=='x' && !visited[r][c]) {
					map[r][c]='.';
					map[r+min][c]='x';
				}
			}
		}
		
	}
}
