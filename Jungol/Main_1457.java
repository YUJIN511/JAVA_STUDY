import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1457 {
	static int M;
	static int N;
	static int map[][];
	static boolean visited[][];
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static ArrayList<Integer> result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());	// ¸Ê Å©±â
		N = Integer.parseInt(st.nextToken());	// 
		int K = Integer.parseInt(st.nextToken());	// 
		map = new int[M][N];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1= Integer.parseInt(st.nextToken());
			int y1= Integer.parseInt(st.nextToken());
			int x2= Integer.parseInt(st.nextToken());
			int y2= Integer.parseInt(st.nextToken());
			make(x1,M-y1,x2,M-y2);
		}
		//
		result = new ArrayList<Integer>();
		visited = new boolean[M][N];
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==0 && !visited[i][j]) {
					result.add(bfs(i,j));					
				}
			}
		}
		System.out.println(result.size());
		Collections.sort(result);
		for(int i : result) {
			System.out.print(i+" ");
		}
		System.out.println();
	}

	private static int bfs(int y, int x) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {y,x,0});
		visited[y][x] = true;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			y = cur[0];
			x = cur[1];
			
			for(int d=0; d<4; d++) {
				int ny = y + dir[d][0];
				int nx = x + dir[d][1];
				
				if(ny<0 || ny>=M || nx<0 || nx>=N || map[ny][nx]==1 || visited[ny][nx])	continue;
				q.offer(new int[] {ny, nx});
				visited[ny][nx] = true;
				cnt++;
			}
		}
		return cnt;
	}

	private static void make(int x1, int y1, int x2, int y2) {
		for(int i=y2; i<y1; i++) {
			for(int j=x1; j<x2; j++) {
				map[i][j]=1;
			}
		}
	}
}
