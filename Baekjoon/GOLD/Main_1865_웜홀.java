import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1865_웜홀 {
	
	static class Edge{
		int u;
		int w;
		
		public Edge(int u, int w) {
			this.u = u;
			this.w = w;
		}
		
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int C = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=C; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// 지점 개수
			int M = Integer.parseInt(st.nextToken());	// 도로 개수
 			int W = Integer.parseInt(st.nextToken());	// 웜홀 개수
 			
 			ArrayList<Edge> graph[] = new ArrayList[N+1];
 			
 			for(int i=0; i<=N; i++) {
 				graph[i] = new ArrayList<>();
 			}
 			
			for(int i=0; i<M; i++) {	// 도로
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());	// 연결된 지점 번호
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());	// 걸리는 시간
				
				graph[S].add(new Edge(E,T));
				graph[E].add(new Edge(S,T));
			}
			
			for(int i=0; i<W; i++) {	// 웜홀
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());	// 연결된 지점 번호
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());	// 걸리는 시간
				
				graph[S].add(new Edge(E,T*-1));
			}
			
			int upper[] = new int[N+1];
			
			Arrays.fill(upper, 900000000);
			upper[1] = 0;
				
			boolean flag = false;
			for(int i=1; i<=N; i++) {
				flag=false;
				
				for(int v=1; v<=N; v++) {
					for(Edge e : graph[v]) {
						if(upper[e.u] > upper[v]+e.w) {
							upper[e.u] = upper[v] + e.w;
							flag = true;
						}
					}
				}
				if(!flag) break;	// 업데이트가 안됬으면 이후에도 업데이트 안되므로 종료
			}
			
			if(flag)	System.out.println("YES");
			else		System.out.println("NO");
		}
	}
}
