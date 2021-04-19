import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2112 {
	
	static int dp[];
	static int N;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // За
		if(N%2 != 0) {
			System.out.println(0);
		}
		else {
			dp = new int[N+1];
			System.out.println(dfs(N));
		}
		
	}

	private static int dfs(int n) {
		
		if(n==0) return 1;
		if(n==1) return 0;
		if(n==2) return 3;
		

		dp[n] = 3*dfs(n-2);
		for(int i=4; i<=n; i+=2) {
			dp[n]+=2*dfs(n-i);
		}
		return dp[n];
		
		
	}
}
