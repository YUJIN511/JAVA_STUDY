import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1871 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());	// 장소의 수
		int arr[] = new int[N];
		int dp[] = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		dp[0]=1;
		
		int result = 0;
		for(int i=1; i<N; i++) {
			dp[i]=1;
			for(int j=0; j<i; j++) {
				if(arr[j] < arr[i] && dp[j]+1 > dp[i]) {
					dp[i] = dp[j]+1;
				}
			}
			result = Math.max(result, dp[i]);
		}
		System.out.println(N-result);
	}
}
