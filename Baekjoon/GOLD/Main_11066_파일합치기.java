import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11066_파일합치기 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			int K = Integer.parseInt(br.readLine());
			
			int dp [][] = new int[K+1][K+1];
			int file [] = new int[K+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=K; i++) {
				file[i] = file[i-1]+Integer.parseInt(st.nextToken());
			}
			
			for(int l=1; l<=K; l++) {			// 체크할 범위의 길이
				for(int s=1; s+l<=K; s++) {	// 시작 위치
					int e = s+l;				// 끝 위치
					dp[s][e] = Integer.MAX_VALUE;
					for(int k=s; k<e; k++) {
						int size = dp[s][k]+dp[k+1][e] + file[e]-file[s-1];
						dp[s][e] = Math.min(dp[s][e], size);
					}
				}
			}
			
			System.out.println(dp[1][K]);
		}
	}
}
