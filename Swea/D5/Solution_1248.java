import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1248 {
	
	static int parent[];
	static int level[];
	static ArrayList<ArrayList<Integer>> tree;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());	// 정점의 총 수
			int E = Integer.parseInt(st.nextToken());	// 간선의 총 수
			int A = Integer.parseInt(st.nextToken());	// 공통 조상을 찾는
			int B = Integer.parseInt(st.nextToken());	// 정점 번호
			
			tree = new ArrayList<>(V+1);
			for(int i=0; i<=V; i++) {
				tree.add(new ArrayList<Integer>());
			}
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<E; i++) {
				int p = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken()); 
				tree.get(p).add(c);
				tree.get(c).add(p);
			}
			
			parent = new int[V+1];
			level = new int[V+1];
			
			dfs1(1,1,0); // 부모, 레벨 구하기
			
			
			while(parent[A] != parent[B]) {
				if(level[A] > level[B]) {
					A = parent[A];
				} else {
					B = parent[B];
				}
			}
			int root = parent[A];
			
			int cnt = dfs2(root, 0);

			System.out.println("#"+testCase+" "+root+" "+cnt);
		}
		
	}

	private static int dfs2(int cur, int cnt) {
		cnt++;
		for(int next : tree.get(cur)) {
			if(parent[cur] != next) {
				cnt = dfs2(next, cnt);
			}
		}
		return cnt;
	}

	private static void dfs1(int cur, int l, int p) {
		level[cur] = l;
		parent[cur] = p;
		
		for(int next : tree.get(cur)) {
			if(next != p) {
				dfs1(next, l+1, cur);
			}
		}
		
	}

}
