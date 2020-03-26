import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_1197_최소스패닝트리 {
	
	static class Node{
		int u, v, w;

		public Node(int u, int v, int w) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
	}
	
	static int[] root;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		ArrayList<Node> Graph = new ArrayList<>();
		root = new int[V+1];
		int A, B, C;
		
		for(int i=1; i<=V; i++) {
			root[i]=i;
		}
		
		for(int j=0; j<E; j++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			Graph.add(new Node(A, B, C));
		}
		
		Graph.sort(new Comparator<Node>() {	// 가중치 오른차순으로 정렬
			@Override
			public int compare(Node o1, Node o2) {
				return (o1.w - o2.w);
			}
		});
		
		int result =0;
		
		for(int i=0; i<Graph.size(); i++)
		{		
			if(union(Graph.get(i).u, Graph.get(i).v)) {
				result+=Graph.get(i).w;	// 최소 가중치 더하기
			}
		}
		
		System.out.println(result);
	}
	
	public static boolean union(int u, int v) {	// 합치기
		if(find(u) != find(v)) {
			root[find(u)]=v;
			return true;
		}
				
		return false;
	}

	public static int find(int v) {	// 부모 찾기
		if(root[v] == v)	return v;
		else 				return root[v] = find(root[v]);
	}
}
