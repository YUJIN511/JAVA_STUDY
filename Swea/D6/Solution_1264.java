import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1264 {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase=1; testCase<=T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// 코드열 크기
			String X = 0+br.readLine();
			String Y = 0+br.readLine();
			
			int dp [][] = new int[N+1][N+1];
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(X.charAt(i) == Y.charAt(j)) {
						dp[i][j] = dp[i-1][j-1]+1;
					} else {
						dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
					}
				}
			}
			double result = (double) dp[N][N]/N*100;
			
			System.out.printf("#"+testCase+" %.2f\n", result);
		}
		
	}

}
