import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1753_최단경로 {
	
	static class Edge implements Comparable<Edge>{
		int v;
		int w;
		
		public Edge(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.w, o.w);
		}
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken()); 
		int E = Integer.parseInt(st.nextToken()); 
		
		int start = Integer.parseInt(br.readLine());	// 시작 정점
		List<Edge>[] adj = new ArrayList [V+1];

		for(int i=1; i<=V; i++) {
			adj[i] = new ArrayList<>();
		}
		
		// 간선 정보 입력
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Edge(b, c));
		}
		
		int dij[] = new int[V+1];
		Arrays.fill(dij, Integer.MAX_VALUE);
		dij[start] = 0;
	
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start,0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			for(Edge next : adj[cur.v]) {
				if(dij[next.v] > dij[cur.v] + next.w) {
					dij[next.v] = dij[cur.v] + next.w;
					pq.offer(new Edge(next.v, dij[next.v]));
				}
				
			}
		}
		for(int i=1; i<=V; i++) {
			if(dij[i] == Integer.MAX_VALUE) System.out.println("INF");
			else							System.out.println(dij[i]);
			
		}
	}
}
