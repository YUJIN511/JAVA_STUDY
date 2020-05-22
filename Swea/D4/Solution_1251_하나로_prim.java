import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_1251_하나로 {

	static int N;
	static double E;
	static int map[][];
	static long graph[][];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			N = Integer.parseInt(br.readLine());

			map = new int[N][2];
			StringTokenizer sx = new StringTokenizer(br.readLine());
			StringTokenizer sy = new StringTokenizer(br.readLine());
			
			for(int i=0; i<N; i++) {
				map[i][0] = Integer.parseInt(sy.nextToken());
				map[i][1] = Integer.parseInt(sx.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			
			// 섬 간의 가중치 그래프
			graph = new long[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					graph[i][j]=(long) (Math.pow((map[i][0]-map[j][0]),2) + Math.pow((map[i][1]-map[j][1]),2));
				}
			}
			
			double cost = prim(0) * E;
			
			System.out.println("#"+test_case+" "+Math.round(cost));
		}
	}

	private static double prim(int start) {
		//prim's 알고리즘은 어떤 vertex를 start로 하든 항상 같은 트리 형성
		
		
		// MST에 들어가지 않은 섬들
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		// 모든 섬들 관리
		Edge[] dynamicGraph = new Edge[N];
		
		for(int i=0; i<N; i++) {
			dynamicGraph[i] = new Edge(i, Long.MAX_VALUE);
			if(i==start) {
				dynamicGraph[i].cost=0;
			}
			pq.add(dynamicGraph[i]);
		}
		
		long cost =0;
		
		while(!pq.isEmpty()) {
			Edge front = pq.poll();
			cost+= front.cost;
			
			// 자식 탐색
			for(int i=0; i<dynamicGraph.length; i++) {
				Edge child = dynamicGraph[i];
				//pq : 비 MST
				if(pq.contains(child)) {
					long tempCost = graph[front.index][child.index];
					if(tempCost < child.cost) {
						child.cost = tempCost;
						pq.remove(child);
						pq.offer(child);
					}
				}
			}
		}
		return cost;
	}
	
	static class Edge implements Comparable<Edge>{
		int index;
		long cost;
		
		public Edge(int index, long cost) {
			super();
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
		
	}

}
