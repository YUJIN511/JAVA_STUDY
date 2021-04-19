import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1352 {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 추의 개수
		int weight[] = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		boolean dp[][] = new boolean[N+1][40001];
		dp[0][0] = true;
		for(int i=1; i<=N; i++) {
			for(int j=0; j<=40000; j++) {
				if(dp[i-1][j]) {
					dp[i][j] = true;
					dp[i][j+weight[i]]=true;
					dp[i][Math.abs(j-weight[i])]=true;
				}
			}
		}
		
		int M = Integer.parseInt(br.readLine()); // 구슬의 개수
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int temp = Integer.parseInt(st.nextToken());
			if(dp[N][temp]) {
				System.out.print("Y ");
			} else {
				System.out.print("N ");
			}
		}
		
	}
}
