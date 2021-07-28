import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_1858 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str1 = 0+br.readLine();
		
		int M = Integer.parseInt(br.readLine());
		String str2 = 0+br.readLine();
		
		int dp[][] = new int[N+1][M+1];
		int path[][][] = new int[N+1][M+1][2];
		int max=0;
		int r=0;
		int c=0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(str1.charAt(i) == str2.charAt(j)) {
					dp[i][j] = 3;
					path[i][j][0]=i;
					path[i][j][1]=j;
				} 
				
				int temp = dp[i-1][j-1] + (str1.charAt(i)!=str2.charAt(j)?-2:3);
				
				if(dp[i][j] < temp) {
					dp[i][j] = temp;
					path[i][j][0] = path[i-1][j-1][0];
					path[i][j][1] = path[i-1][j-1][1];
				}
				if(dp[i][j]<dp[i-1][j]-2) {
					dp[i][j] = dp[i-1][j]-2;
					path[i][j][0] = path[i-1][j][0];
					path[i][j][1] = path[i-1][j][1];
				}
				if(dp[i][j]<dp[i][j-1]-2) {
					dp[i][j] = dp[i][j-1]-2;
					path[i][j][0] = path[i][j-1][0];
					path[i][j][1] = path[i][j-1][1];
				}
				
				if(max<dp[i][j]) {
					max = dp[i][j];
					r=i;
					c=j;
				}
			}
		}
		
		System.out.println(max);
		System.out.println(str1.substring(path[r][c][0], r+1));
		System.out.println(str2.substring(path[r][c][1], c+1));
	
	}
}
