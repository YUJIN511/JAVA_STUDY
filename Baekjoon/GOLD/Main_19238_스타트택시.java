import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_19238_스타트택시 {
	
	static int N;
	static int F;
	static int map[][];
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());	// 초기 연료

		// 백준이 활동할 영역의 지도  0:빈칸, 1:벽
		map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 백준이 운전을 시작하는 칸
		st = new StringTokenizer(br.readLine());
		int taxiR = Integer.parseInt(st.nextToken());
		int taxiC = Integer.parseInt(st.nextToken());
		
		// 승객 출발지 정보 & 목적지 정보
		int p[][] = new int[M+2][4];
		int cnt=2;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				p[i+2][j] = Integer.parseInt(st.nextToken());
			}
			map[p[i+2][0]][p[i+2][1]] = cnt++;
		}
		
		// M명의 승객을 태우는 것이 목표
		// 특정 위치로 이동할 때 항상 최단경로로만
		// 최단경로가 여러개면 => 행 번호 작은, 열 번호 작은 
		// 연료는 한칸당 1 소모
		// 승객을 목적지로 이동시키면, 소모한 연료 양의 두배 충전
		// 이동하는 도중 연료 바닥나면 이동 실패
		
		while(M-- > 0) {
			// 가장 가까이에 있는 손님 찾기
			int start[] = BFS1(taxiR, taxiC);
			
			if(start == null) {
				F=-1;
				break;
			}
			F-= start[2];	// 연료 차감
			
			// 손님 목적지로 이동
			int num = map[start[0]][start[1]];
			map[start[0]][start[1]]=0;	// 손님 있던 자리 빈칸으로
			cnt = BFS2(start[0], start[1], p[num][2], p[num][3]);
			
			if(cnt == -1) {
				F=-1;
				break;
			}
			F = F - cnt + (cnt*2);
			taxiR = p[num][2];
			taxiC = p[num][3];
		}
		
		System.out.println(F);
	}



	private static int[] BFS1(int r, int c) {
		boolean visited[][] = new boolean[N+1][N+1];
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[2] == o2[2]) {
					if(o1[0]==o2[0])	return Integer.compare(o1[1], o2[1]);
					else				return Integer.compare(o1[0], o2[0]);
				} else {
					return Integer.compare(o1[2], o2[2]);
				}
			}
		});
		pq.offer(new int[] {r, c, 0});
		visited[r][c] = true;
		
		while(!pq.isEmpty()) {
			int cur[] = pq.poll();
			r = cur[0];
			c = cur[1];
			int cnt = cur[2];
			
			if(map[r][c] > 1) {	// 승객
				return new int[] {r,c,cnt};
			}
			
			if(cnt>=F)	return null;	// 연료가 떨어진 경우
			
			for(int d=0; d<4; d++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				if(nr<1 || nr>N || nc<1 || nc>N || visited[nr][nc] || map[nr][nc]==1)	continue;
				
				pq.offer(new int[] {nr,nc, cnt+1});
				visited[nr][nc] =true;
					
			}
		}
		
		return null;
	}
	
	private static int BFS2(int sr, int sc, int dr, int dc) {
		boolean visited[][] = new boolean[N+1][N+1];
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		queue.offer(new int[] {sr, sc, 0});
		visited[sr][sc] = true;
		
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			sr = cur[0];
			sc = cur[1];
			int cnt = cur[2];
			
			if(sr==dr && sc==dc) {	// 승객
				return cnt;
			}
			
			if(cnt>=F)	return -1;	// 연료가 떨어진 경우
			
			for(int d=0; d<4; d++) {
				int nr = sr + dir[d][0];
				int nc = sc + dir[d][1];
				
				if(nr<1 || nr>N || nc<1 || nc>N || visited[nr][nc] || map[nr][nc]==1)	continue;
				
				queue.offer(new int[] {nr,nc, cnt+1});
				visited[nr][nc] =true;
					
			}
		}
		
		return -1;
	}
}
