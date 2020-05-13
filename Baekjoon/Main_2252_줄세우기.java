import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2252_줄세우기 {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 학생 수
		int M = Integer.parseInt(st.nextToken());	// 비교한 횟수
		int edgecount[] = new int[N+1];
		ArrayList<ArrayList<Integer>>graph = new ArrayList<>();
		for(int i=0; i<N+1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<M; i++) {	// 정보 입력
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			graph.get(v).add(u);
			edgecount[u]++;
		}
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		for(int i=1; i<N+1; i++) {
			if(edgecount[i] == 0) {	// 진입차수가 0인 정점만
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int v = q.poll();
			System.out.print(v+" ");
			
			for(int u : graph.get(v)) {	// 연결된 인접 정점 탐색
				edgecount[u]--;
				
				if(edgecount[u]==0) {	// 진입차수가 0일경우 큐에 삽입
					q.offer(u);
				}
			}
		}
		
		System.out.println();
	}
}
