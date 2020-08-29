import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class brandi_레이싱게임 {
	
	final static int INF = 99999;
	
	static class Data{
		int v;
		int w;
		
		public Data() {}
		public Data(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 총 지점
		int M = Integer.parseInt(st.nextToken());	// 지점 사이 정보의 개수
		
		ArrayList<Data> graph[] = new ArrayList [N+1];
		
		for(int i=0; i<=N; i++) {
			graph[i] = new ArrayList<Data>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
				
			graph[v].add(new Data(u, w));
				
		}
		
		int dist[] = new int[N+1];
		Arrays.fill(dist, INF);
		boolean flag = false;
		
		dist[1]=0;
		for(int i=0; i<N; i++) {	// N-1번만큼 돈다. 마지막 한번은 음의 싸이클 확인용
			for(int j=1; j<=N; j++) {
				for(Data next : graph[j]) {
					if(dist[j] != INF && dist[next.v] > dist[j] + next.w) {
						dist[next.v] = dist[j] + next.w;
						if(i==N-1) flag = true;	// N번째일때 갱신되면 음의 싸이클
					}
				}
			}
			
		}
		if(flag)	System.out.println("bug");
		else		System.out.println(dist[N]==INF? 0 :dist[N]);
	}
}
