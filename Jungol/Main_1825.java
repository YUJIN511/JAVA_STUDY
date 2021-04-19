import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1825 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());	// 투자 금액
		int N = Integer.parseInt(st.nextToken());	// 투자 가능한 기업 개수
		int arr[][] = new int[N+1][M+1];
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<=N; j++) {
				arr[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		int dp[][] = new int[N+1][M+1];
		int path[][] = new int[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				for(int k=0; k<=j; k++) {
					if(dp[i][j] < dp[i-1][j-k] + arr[i][k]) {
						dp[i][j] = dp[i-1][j-k] + arr[i][k];
						path[i][j] = k;
					}
				}
			}
		}
		
		System.out.println(dp[N][M]);
		Stack<Integer> s = new Stack<Integer>();
		for(int i=N; i>0; i--) {
			s.push(path[i][M]);
			M-=path[i][M];
		}
		while(!s.isEmpty()) {
			System.out.print(s.pop()+" ");
		}
		System.out.println();
	}

}
