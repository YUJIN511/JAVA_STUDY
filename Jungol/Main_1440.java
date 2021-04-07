import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1440 {
	
	static int parent[];
	static int level[];
	static ArrayList<ArrayList<Integer>> tree;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());	// 정점의 총 수
		
		tree = new ArrayList<>(V+1);
		for(int i=0; i<=V; i++) {
			tree.add(new ArrayList<Integer>());
		}
		
		parent = new int[V+1];
		for(int i=0; i<V-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken()); 
			tree.get(p).add(c);
			tree.get(c).add(p);
			parent[c]=p;
		}
		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());	// 공통 조상을 찾는
		int B = Integer.parseInt(st.nextToken());	// 정점 번호
		
		level = new int[V+1];
		int root = 0;
		
		for(int i=1; i<=V; i++) {
			if(parent[i]==0) {
				root = i;
				break;
			}
		}
		dfs(root,1,0); // 부모, 레벨 구하기
		
		while(A != B) {
			if(level[A] > level[B]) {
				A = parent[A];
			} else {
				B = parent[B];
			}
		}
		int result = A;

		System.out.println(result);
	}


	private static void dfs(int cur, int l, int p) {
		level[cur] = l;
		
		for(int next : tree.get(cur)) {
			if(next != p) {
				dfs(next, l+1, cur);
			}
		}
		
	}

}
