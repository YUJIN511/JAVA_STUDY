import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1967_트리의지름 {
	
	static int n;
	static ArrayList<Node> tree[];
	static boolean visited[];
	
	static class Node{
		int num;
		int w;
		
		public Node(int num, int w) {
			super();
			this.num = num;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());	// 노드의 개수
		tree = new ArrayList [n+1];

		for(int i=0; i<=n; i++) {
			tree[i] = new ArrayList<>();
		}
		for(int i=1; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			tree[p].add(new Node(c,w));
			tree[c].add(new Node(p,w));
		}
		
		visited = new boolean[n+1];
		int startNode = bfs(1).num;	//  루트 노드에서 가장 먼 노드
		visited = new boolean[n+1];
		int result= bfs(startNode).w;	// 구한 노드에서 가장 먼 노드
		
		System.out.println(result);
	}

	private static Node bfs(int start) {
		
		ArrayDeque<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(start, 0));
		visited[start] = true;
		Node max = new Node(0,0);
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			// 연결된 노드 
			for(int i=0; i<tree[cur.num].size(); i++) {
				Node next = tree[cur.num].get(i);
				if(!visited[next.num]) {
					queue.offer(new Node (next.num, cur.w+next.w));
					
					visited[next.num] = true;
				}
			}

			if(max.w < cur.w) {
				max.w = cur.w;
				max.num = cur.num;
			}
		}
		
		return max;
	}
}
