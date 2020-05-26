import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1937_욕심쟁이판다 {
	
	static int n;	// 대나무 숲 크기
	static int forest[][];	// 대나무 숲 정보
	static int count[][];	// 대나무 숲 정보
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static int K;
	
	static class data{
		int y;
		int x;
		public data(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		forest = new int[n][n];
		count = new int[n][n];
		PriorityQueue<data> pq = new PriorityQueue<>(new Comparator<data>() {
			@Override
			public int compare(data o1, data o2) {
				return Integer.compare(forest[o1.y][o1.x], forest[o2.y][o2.x]);
			}
		});
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				forest[i][j] =  Integer.parseInt(st.nextToken());
				pq.offer(new data(i,j));
			}
		}
		
		K = 1;
		while(!pq.isEmpty()) {
			data cur = pq.poll();
			solve(cur.y, cur.x);
		}
		
		System.out.println(K);
	}

	private static void solve(int r, int c) {
		
		PriorityQueue<data> queue = new PriorityQueue<>(new Comparator<data>() {
			@Override
			public int compare(data o1, data o2) {
				return Integer.compare(forest[o1.y][o1.x], forest[o2.y][o2.x]);
			}
		});
		
		queue.offer(new data(r,c));
		
		while(!queue.isEmpty()) {
			data cur = queue.poll();
			r = cur.y;
			c = cur.x;
			
			if(count[r][c] == 0)	count[r][c]++;
			int cnt = count[r][c];
			
			
			for(int d=0; d<4; d++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				if(nr<0 || nr>=n || nc<0 || nc>=n || forest[r][c] >= forest[nr][nc])	continue;
				
				if(count[nr][nc] < cnt+1) {
					count[nr][nc]=cnt+1;
					queue.offer(new data(nr,nc));
				}
				K = Math.max(K, count[nr][nc]);
			}
		}
	}
}
