import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1263 {
	static final int INF = 10000000;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int T = Integer.parseInt(br.readLine());
		for(int testCase=1; testCase<=T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			 int dist[][] = new int[N][N];
			 
			 for(int i=0; i<N; i++) {
				 for(int j=0; j<N; j++) {
					 dist[i][j] = Integer.parseInt(st.nextToken());
					 if(dist[i][j] == 0) dist[i][j] = INF;
				 }
			 }
			 
			 for(int k=0; k<N; k++) {
				 for(int s=0; s<N; s++) {
					 if(k==s) continue;
					 for(int e=0; e<N; e++) {
						 if(s==e) continue;
						 if(dist[s][e] > dist[s][k]+dist[k][e]) {
								 dist[s][e] = dist[s][k]+dist[k][e];
						 }
					 }
				 }
			 }
			int result = Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				int sum=0;
				for(int j=0; j<N; j++) {
					if(dist[j][i] != INF) sum+=dist[i][j];
				}
				result = Math.min(result, sum);
			}
			System.out.println("#"+testCase+" "+result);
			
		}
	}
}
