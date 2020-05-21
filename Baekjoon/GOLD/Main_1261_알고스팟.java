import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1261_알고스팟 {
	
	static int N;
	static int M;
	static int maze[][];
	static int dist[][];		// 상 하 좌 우
	static int dir[][]= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());	// 가로
		N = Integer.parseInt(st.nextToken());	// 세로
		maze = new int[N][M];
		dist = new int[N][M];
		// 미로 상태 입력
		String str;
		for(int i=0; i<N; i++) {
			str = br.readLine();
			for(int j=0; j<str.length(); j++) {
				maze[i][j]=str.charAt(j)-'0';
				dist[i][j]=Integer.MAX_VALUE;
			}
		}
		int result = dijkstara();
		
		System.out.println(result);
		
	}
	private static int dijkstara() {
		dist[0][0]=0;
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(0, 0, 0));
		

		while(!pq.isEmpty()) {
			Vertex cur = pq.poll();

			if(cur.r == N-1 && cur.c == M-1)	return cur.cost;	// 끝까지 간 경우
			
			int nr;
			int nc;
			
			for(int d=0; d<4; d++) {
				nr = cur.r + dir[d][0];
				nc = cur.c + dir[d][1];
				if(nr<0 || nr>=N || nc<0 || nc>=M)	continue;	// 경계 체크
				
				if(dist[nr][nc]>cur.cost + maze[nr][nc]) {	// dist에는 지금까지 벽 부순 최솟 값
					dist[nr][nc]= cur.cost + maze[nr][nc];
					pq.offer(new Vertex(nr, nc, dist[nr][nc]));
				}
			}
		}
		return -1;
	}
	
	static class Vertex implements Comparable<Vertex>{
		int r;
		int c;
		int cost;
		
		public Vertex(int r, int c, int cost) {
			super();
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.cost, o.cost);
		}		
	}
}
