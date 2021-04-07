import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1822 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());	// 성체가 되는 날
		int b = Integer.parseInt(st.nextToken());	// 개체 생성 그만
		int d = Integer.parseInt(st.nextToken());	// 죽는 날
		int N = Integer.parseInt(st.nextToken());	// 마지막 날
		
		int dp[] = new int[N+1];	// 사람 좌표

		dp[0]=1;
		for(int i=1; i<=N; i++) {
			if(i<a) { // 성체 될때까지
				dp[i]=dp[i-1];
			}else if(i<b) { // 전날 + 태어남
				dp[i] = dp[i-1] + dp[i-a];
			}else {	// 전날 + 테어남 - 번식못함
				dp[i] = dp[i-1] + dp[i-a] - dp[i-b];
			}
			dp[i]=(dp[i]+1000)%1000;
		}
		
		if(d<=N) dp[N] -= dp[N-d];
		
		System.out.println((dp[N]+1000)%1000);
	}
}
