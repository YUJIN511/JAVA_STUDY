import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_1562_계단수 {
	
	static int N;
	static long dp[][][];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long result=0;
		N = Integer.parseInt(br.readLine());
		dp = new long[10][N+1][1<<10];
		
		for(int s=1; s<10; s++) {
			result += DFS(s, 1, 1<<s);
			result %= 1000000000;
		}

		System.out.println(result);
	}

	private static long DFS(int pre, int cnt, int check) {
		
		if(dp[pre][cnt][check] != 0) {
			return dp[pre][cnt][check];
		}
		
		if(cnt == N) {
			if(check == 1023)	return 1;	// 0~9 숫자 다 골랐는지 확인
			else				return 0;
		}
		
		long sum=0;
		if(pre-1 >= 0)	sum += DFS(pre-1, cnt+1, check | 1<<(pre-1));
		if(pre+1 < 10)	sum += DFS(pre+1, cnt+1, check | 1<<(pre+1));
		sum %= 1000000000;
		
		return dp[pre][cnt][check]=sum;
		
	}
}
