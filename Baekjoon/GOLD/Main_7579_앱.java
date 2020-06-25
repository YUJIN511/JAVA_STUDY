import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_7579_앱 {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int memory[] = new int[N];	// 활성화 되어 있는 앱이 사용중인 메모리
		int cost[] = new int[N];	// 비활성화 했을 경우 비용
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++){
			memory[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++){
			cost[i] = Integer.parseInt(st.nextToken());
			sum+=cost[i];
		}
		
		int[] dp = new int[sum+1];
		
		for(int idx=0; idx<N; idx++) {
			for(int j=sum; j >= cost[idx]; j--) {	// dp[j] = idx일 때 최대 메모리
				dp[j] = Math.max(dp[j], dp[j-cost[idx]] + memory[idx]);
			}
//			for(int i=0; i<=sum; i++) {
//				System.out.print(dp[i]+" ");
//			}
//			System.out.println();
		}
		
		for(int i=0; i<sum; i++) {
			if(dp[i] >= M) {
				System.out.println(i);
				break;
			}
		}
	}

}
