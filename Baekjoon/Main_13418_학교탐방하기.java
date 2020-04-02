import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_13418_학교탐방하기 {
	
	static int root[];
	
	public static void main(String[] args) throws Exception
	{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 건물의 개수
		int M = Integer.parseInt(st.nextToken());	// 도로의 개수

		PriorityQueue<int []> hq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2];
			}
		});
		
		PriorityQueue<int []> lq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[2]-o1[2];
			}
		});
		
		for(int i=0; i<=M; i++) {		// 그래프 입력
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			hq.add(new int[] {A, B, C});
			lq.add(new int[] {A, B, C});
		}
		
		root = new int[N+1];
		for(int i=0; i<=N; i++) {
			root[i]=i;
		}
		int high=0;
		while(!hq.isEmpty()) {
			int v[] = hq.poll();
			
			if(find(v[0]) != find(v[1])) {
				union(v[0], v[1]);
				if(v[2]==0)high++;
			}
		}
		////////////////
		for(int i=0; i<=N; i++) {
			root[i]=i;
		}
		int low=0;
		while(!lq.isEmpty()) {
			int v[] = lq.poll();

			if(find(v[0]) != find(v[1])){
				union(v[0], v[1]);
				if(v[2]==0)low++;
			}
		}
		
		System.out.println(high*high - low*low);
	}

	public static int find(int v) {
		if(root[v] == v)	return v;
		else				return root[v] = find(root[v]);
	}
	
	private static void union(int u, int v) {
		root[find(u)] = find(v);
	}
}
