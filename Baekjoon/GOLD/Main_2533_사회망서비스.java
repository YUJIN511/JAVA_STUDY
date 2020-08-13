import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2533_사회망서비스 {

	static ArrayList<Integer> tree[];
	static int dp[][];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		tree = new ArrayList [N+1];
		dp = new int[N+1][2];
		
		for(int i=1; i<=N; i++) {
			tree[i] = new ArrayList<>();
			dp[i][0] = Integer.MAX_VALUE;
			dp[i][1] = Integer.MAX_VALUE;
		}
		
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			
			tree[v].add(u);
			tree[u].add(v);
		}
		
		int result = Integer.min(dp(1,0,1), dp(1,0,0));
		
		System.out.println(result);
	}

	private static int dp(int cur, int prev, int check) {
		
		if(dp[cur][check] != Integer.MAX_VALUE)	return dp[cur][check];
		int num = 0;	// 얼리어답터 수
		
		if(check==1) {	// 내가 얼리어답터이면 다음 노드는 얼리어답터이거나 아님
			for(int next : tree[cur]) {
				if(next == prev)	continue;	// 부모인경우 패쓰
				
				int a = dp(next,cur,1);	
				int b = dp(next,cur,0);
				num += Integer.min(a,b);	
			}
			num++; // 자신 포함
		} else {	// 내가 얼리어답터가 아니면 다음 노드는 무조건 얼리어답터
			for(int next : tree[cur]) {
				if(next == prev)	continue;	// 부모인경우 패쓰
				
				num += dp(next,cur,1);
			}
		}
		return dp[cur][check]=num;
	}


}
