import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_13911_집구하기 {
	static class edge{
		int u;
		int w;
		public edge(int u, int w) {
			super();
			this.u = u;
			this.w = w;
		}
	}
	static int V;
	static int dist[][];
	static ArrayList<ArrayList<edge>> graph;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		
		for(int i=0; i<=V+2; i++) {
			graph.add(new ArrayList<edge>());
		}
		
		for(int i=0; i<E; i++) { // 그래프 입력
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(v).add(new edge(u,w));
			graph.get(u).add(new edge(v,w));
		}
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());	// 맥도날드 수
		int x = Integer.parseInt(st.nextToken());	// 조건
		
		st = new StringTokenizer(br.readLine());
		int mac=V+1;
		for(int j=0; j<M; j++) {
			int n = Integer.parseInt(st.nextToken());
			graph.get(mac).add(new edge(n,0));	// 맥도날드 집합
		}
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());	// 스타벅스 수
		int y = Integer.parseInt(st.nextToken());	// 조건
		
		st = new StringTokenizer(br.readLine());
		int star=V+2;
		for(int j=0; j<S; j++) {
			int n = Integer.parseInt(st.nextToken());
			graph.get(star).add(new edge(n,0));	// 스타벅스 집합
		}

		dist = new int[2][V+3];
		
		dij(mac,0);	// 맥도날드에서 시작
		dij(star,1);	// 스타벅스에서 시작
		
		int min = Integer.MAX_VALUE;
		
		for(int i=1; i<=V; i++) {
			if(dist[0][i]!=0 && dist[0][i]<=x) {	// 맥세권인 경우
				if(dist[1][i]!=0 && dist[1][i]<=y) {	// 스세권인 경우
					min = Math.min(min, dist[0][i]+dist[1][i]);
				}
			}
		}
		
		System.out.println(min==Integer.MAX_VALUE? -1 : min);
		
	}
	
	private static void dij(int start,int d) {
		
		Arrays.fill(dist[d], Integer.MAX_VALUE);
		dist[d][start] = 0;
			
		PriorityQueue<edge> pq = new PriorityQueue<>(new Comparator<edge>() {
			public int compare(edge o1, edge o2) {
				return Integer.compare(o1.w,o2.w);
			}
		});
			
		pq.offer(new edge (start,0)); 
			
		while(!pq.isEmpty()) {
			edge cur = pq.poll();
			
			for(edge next : graph.get(cur.u)) {
				if(dist[d][next.u] > dist[d][cur.u] + next.w) {
					dist[d][next.u] = dist[d][cur.u] + next.w;
					pq.offer(new edge (next.u, dist[d][next.u]));
				}
			}
		}
	}
}
