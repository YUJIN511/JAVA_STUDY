import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2191 {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // a길이
		String a = st.nextToken();
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); // b길이
		String b = st.nextToken();

		int dp[][] = new int[N+1][M+1];
		for(int i=0; i<=N; i++) dp[i][0]=i;
		for(int i=0; i<=M; i++) dp[0][i]=i;
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(a.charAt(i-1) == b.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]))+1; 
				}
			}
		}
		System.out.println(dp[N][M]);
	}
}
