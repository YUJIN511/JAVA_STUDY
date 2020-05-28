import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13398_연속합2 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int [] arr = new int [n+1];
		int [] dp = new int[n+1];
		int [] ddp = new int[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int result = arr[1];

		dp[1] = arr[1];
		ddp[1] = arr[1];
		
		for(int i=2; i<=n; i++) {
			dp[i] = Math.max(dp[i-1]+arr[i], arr[i]);
			ddp[i] = Math.max(ddp[i-1]+arr[i], dp[i-1]);
			result = Math.max(result, Math.max(dp[i], ddp[i]));
		}
		
		System.out.println(result);
	}

}
