import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_1251 {

	static int N;
	static long graph[][];
	static Double E;
	static class Data{
		int idx;
		long L;
		
		public Data(int idx, long l) {
			this.idx = idx;
			this.L = l;
		}
		
	}
	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++) {
			N = Integer.parseInt(br.readLine());	// ¼¶ÀÇ °³¼ö
			int X[] = new int[N];
			int Y[] = new int[N];
			graph = new long[N][N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				X[i] = Integer.parseInt(st.nextToken());	// ¼¶µéÀÇ ÁÂÇ¥
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				Y[i] = Integer.parseInt(st.nextToken());	
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					graph[i][j] = (long) (Math.pow(Math.abs(X[i]-X[j]), 2)+(Math.pow(Math.abs(Y[i]-Y[j]), 2)));
				}
			}
			E = Double.parseDouble(br.readLine());
			
			System.out.println("#"+testCase+" "+Math.round(prim()*E));
		}
		
	}

	private static long prim() {
		PriorityQueue<Data> pq = new PriorityQueue<Data>(new Comparator<Data>() {
			@Override
			public int compare(Data o1, Data o2) {
				return Long.compare(o1.L, o2.L);
			}
		});
		Data dis[] = new Data[N];
		dis[0] = new Data(0, 0);
		pq.add(dis[0]);
		for(int i=1; i<N; i++) {
			dis[i] = new Data(i, Long.MAX_VALUE);
			pq.add(dis[i]);
		}

		long result = 0l;
		
		while(!pq.isEmpty()){
			Data cur = pq.poll();
			result += cur.L;
			
			for(int i=0; i<N; i++) {
				Data next = dis[i];
				if(pq.contains(next)) {
					if(graph[cur.idx][next.idx] < next.L) {
						pq.remove(next);
						next.L = graph[cur.idx][next.idx];
						pq.offer(next);
					}
				}
			}
		}
		
		return result;
	}

}
