import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution_1251_하나로_kruskal {
	public static class Node{
		int x;
		int y;
		long w;
		
		public Node(int x, int y, long w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}
	}
	static int parents[];
	static int rank[];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			int N = Integer.parseInt(br.readLine());
			int arr[][] = new int [N][2];
			

			st = new StringTokenizer(br.readLine());	// x 좌표
			for(int i=0; i<N; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());	// y 좌표
			for(int i=0; i<N; i++) {
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			double E = Double.parseDouble(br.readLine()); 
			
			ArrayList<Node> graph = new ArrayList<Node>();
			
			for(int i=0; i<N; i++) {
				for(int j=i+1; j<N; j++) {
					long x = Math.abs(arr[i][0] - arr[j][0]);
					long y = Math.abs(arr[i][1] - arr[j][1]);
					long l = x*x + y*y;
					graph.add(new Node(i, j, l));
				}
			}
			
			graph.sort(new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					return Long.compare(o1.w, o2.w);
				}
			});
			
			// 부모 초기화
			parents = new int[N];
			rank = new int[N];
			for(int i=0; i<N; i++) {
				parents[i] = i;
			}
			
			double result =0;
			int cnt = 0;
			for(int i=0; i<graph.size(); i++) {
				int v = graph.get(i).x;
				int u = graph.get(i).y;
				long w = graph.get(i).w;
				
				int pv = findSet(v);
				int pu = findSet(u);
					
				if(pv == pu)	continue;
				union(pv, pu);
				result += E * w;
				cnt++;
				if(cnt == N-1)	break;
			}
			
			System.out.println("#"+test_case+" "+Math.round(result));
		}
	}

	private static void union(int v, int u) {
		int pv = findSet(v);
		int pu = findSet(u);
		if(rank[pv] > rank[pu]) {
			parents[pu] = pv;
		}
		else {
			parents[pv]=pu;
			if(rank[pv]== rank[pu])	rank[pu]++;
		}
		
	}

	private static int findSet(int v) {
		if(v == parents[v]) {
			return v;
		}
		else {
			parents[v] = findSet(parents[v]);
			return parents[v];
		}
	}
	
	
}
