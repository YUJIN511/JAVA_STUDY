import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_17142_연구소3 {
	static int N;
	static int M;
	static int lab[][];
	static boolean visited[][];
	static int selected;
	static int idx[];
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static ArrayList<int[]> virus;
	static int result;
	static int emptysize;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 연구소 크기
		M = Integer.parseInt(st.nextToken());	// 바이러스 개수
		virus = new ArrayList<>();
		lab = new int[N][N];
		idx = new int[M];
		emptysize=0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {	// 0:빈칸     1:벽     2:바이러스
				lab[i][j] = Integer.parseInt(st.nextToken());
				if(lab[i][j] == 2) {	// 바이러스만
					virus.add(new int[] {i,j});
				} else if(lab[i][j]==0) {
					emptysize++;
				}
				
			}
		}
		
		if(emptysize==0) {	// 빈칸이 없을 경우
			result = 0;
		}
		else {
			selected=0;
			result = Integer.MAX_VALUE;
			com(0,0);
		}
		
		System.out.println(result==Integer.MAX_VALUE? -1: result);
	}
	private static void com(int cnt, int s) {
		if(cnt == M) {	// M개만큼 뽑은 경우
			visited = new boolean[N][N];
			int time = bfs();
			if(time!=0) result = Math.min(result, time);
			return;
		}
		
		for(int i=s; i<virus.size(); i++) {
			if((selected & 1<<i) == 0) {	// 선택 안된 경우
				selected |= 1<<i;
				idx[cnt]=i;
				com(cnt+1, i+1);
				selected &= ~(1<<i);
			}
		}
	}
	private static int bfs() {
		int size = 0;
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		for(int i=0; i<idx.length; i++) {	// 조합으로 뽑은 바이러스 큐에 넣어줌
			int v [] = virus.get(idx[i]);
			queue.offer(new int[] {v[0], v[1], 0});
			visited[v[0]][v[1]] = true;
		}
		int time = 0;
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int t = cur[2];
			
			if(size == emptysize && lab[r][c]==0) {	// 마지막 빈칸의 최대 시간 값
				time = Math.max(time, t);
			}
			
			for(int d=0; d<4; d++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc] || lab[nr][nc] == 1)	continue;	// 경계를 넘어가고 방문했거나 벽이면 패쓰
				
				if(lab[nr][nc]==0) size++;	// 빈칸만 count
				
				queue.offer(new int[] {nr,nc,t+1});
				visited[nr][nc]=true;
			}
			
		}
		
		return time;
	}
}
