import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_5465_곰돌이 {
	
	static int N;
	static int S;
	static char map [][];
	static int beesMap [][];
	static boolean visited [][];
	static ArrayList<int[]> bees;
	static int dir [][] = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		map= new char[N][N];
		beesMap= new int[N][N];
		bees = new ArrayList<>();
		
		int sr=0, sc = 0;
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			map[i] = str.toCharArray();
			for(int j=0; j<N; j++) {
				if(map[i][j] == 'M') {			// 꿀단지(곰돌이 최초 위치)
					map[i][j] = 'G';
					sr = i;
					sc = j;
				} else if(map[i][j] == 'H'){	// 벌집	
					bees.add(new int[] {i,j});
				}
			}
		}
		
		beesMove();	// 벌들 이동 전처리
		
		int start = 0;
		int end = N*2;
		int max = -1;
		while(start <= end) {
			int mid=(start+end)/2;
			
			visited = new boolean [N][N];
			if(bearMove(sr, sc, mid*S)) {	// 곰돌이가 집에 갈 수 있는 안전한 시간
				max = mid;
				start=mid+1;
			} else {			// 벌들에게 잡히는 시간
				end = mid-1;
			}
			
		}
		
		System.out.println(max);
	}

	private static void beesMove() {
		ArrayDeque<int[]> beesQ = new ArrayDeque<>();
		for(int [] pos : bees) {	// 벌집 위치 큐에 넣기
			beesQ.offer(new int[] {pos[0], pos[1],0});
		}
		
		while(!beesQ.isEmpty()) {	// 곰돌이 먹는 시간동안 벌 이동
			int cur[] = beesQ.poll();
			int r = cur[0];
			int c = cur[1];
			int t = cur[2];
			
			for(int d=0; d<4; d++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N || map[nr][nc] != 'G'
						|| beesMap[nr][nc]!=0)	continue;
				
				beesQ.offer(new int[] {nr, nc, t+1});
				beesMap[nr][nc] = (t+1)*S; 
			}
		}
	}

	private static boolean bearMove(int r, int c, int t) {
		
		ArrayDeque<int[]> bearQ = new ArrayDeque<>();
		
		bearQ.offer(new int[] {r, c, t});
		visited[r][c] = true;
		
		while(!bearQ.isEmpty()) {	// 곰돌이 이동
			int cur[] = bearQ.poll();
			r = cur[0];
			c = cur[1];
			t = cur[2];
			
			if(map[r][c] == 'D')	return true;	// 곰돌이집 도착
			if(beesMap[r][c] <= t)	continue;		// 벌이 이미 와있으면
			
			for(int d=0; d<4; d++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N || map[nr][nc] == 'T' || map[nr][nc]=='H' 
						|| visited[nr][nc]) continue;
					
				bearQ.offer(new int[] {nr, nc, t+1});
				visited[nr][nc]=true;
				
			}
		}
		return false;
	}

}
