import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_11726_2n타일링 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int N = Integer.parseInt(br.readLine());
		int dp[] = new int[N+1];
		dp[0]=1;
		dp[1]=1;
		for(int i=2; i<=N; i++) {
			dp[i] = (dp[i-1]+dp[i-2]) %10007;
		}
		System.out.println(dp[N]%10007);
	}
}
