import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2917_늑대사냥꾼 {

	static int N;
	static int M;
	static int dis[][];
	static char map[][];
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static ArrayDeque<int[]> queue;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		dis = new int[N][M];
		queue = new ArrayDeque<>();

		
		int sr=0;
		int sc=0;
		
		for(int i=0; i<N; i++) {
			Arrays.fill(dis[i], Integer.MAX_VALUE);
			String str = br.readLine();
			map[i] = str.toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == '+') {
					queue.offer(new int[] {i,j,0});	// 나무
					dis[i][j]=0;
				} else if(map[i][j] =='V') {	// 늑대 위치
					sr = i;
					sc = j;
				} 
			}
		}
		
		BFS1();	// 나무들에서 최소거리 구하기 

		BFS2(sr, sc);
	
	
	}

	private static void BFS1() {
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur[0] + dir[d][0];
				int nc = cur[1] + dir[d][1];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M || map[nr][nc]=='+' || dis[nr][nc] <= cur[2] + 1 )	continue;
				
				queue.offer(new int[] {nr, nc, cur[2]+1});
				dis[nr][nc]=cur[2]+1;
				
			}
		}
	}
	
	private static void BFS2(int sr, int sc) {
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				
				return Integer.compare(o2[2], o1[2]);
			}
		});
		
		boolean visited[][] = new boolean[N][M];
		pq.offer(new int[] {sr, sc, dis[sr][sc]});
		visited[sr][sc] = true;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int r = cur[0];
			int c = cur[1];
			int mindis = cur[2];
			
			if(map[r][c] == 'J') {	// 오두막에 왔으면 끝
				System.out.println(mindis);
				return;
			}
			
			for(int d=0; d<4; d++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M || visited[nr][nc] )	continue;

				pq.offer(new int[] {nr,nc,Math.min(dis[nr][nc], mindis)});
				visited[nr][nc] = true;
			}
		}
	}
}
