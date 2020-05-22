import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Solution_3124_최소스패닝트리 {
	
	static class Node{
		int v;
		int w;
		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case= 1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			ArrayList<Node>[] graph = new ArrayList[V+1];
			
			PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					return Integer.compare(o1.w, o2.w);
				}
			});
			
			for(int i=1; i<V+1; i++) {
				graph[i] = new ArrayList<Node>();
			}
			
			for(int i = 0; i<E; i++) {	// 입력
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				graph[u].add(new Node(v,w));
				graph[v].add(new Node(u,w));
			}
			
			boolean visited[] = new boolean[V+1];
			
			pq.offer(new Node(1,0));
			long result =0;
			
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				int v = cur.v;
				int w = cur.w;
				
				if(!visited[v]) {
					visited[v] = true;
					result += w;
					for(int i=0; i<graph[v].size(); i++) {
						int nv = graph[v].get(i).v;
						int nw = graph[v].get(i).w;
						
						if(!visited[nv]) {
							pq.offer(new Node(nv, nw));
						}
					}
				}
				
			}
			
			System.out.println("#"+test_case+" "+result);
		}
	}
}
