import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_5643_키순서 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			boolean graph[][] = new boolean [N+1][N+1];
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());

				graph[num1][num2] = true;
			}
			
			for(int k=1; k<=N; k++) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N; j++) {
						if(k==i || i==j)	continue;
						if(graph[i][k] && graph[k][j])	graph[i][j]=true;
					}
				}
			}
			
			int count;
			int result=0;
			for(int i=1; i<=N; i++) {
				count=0;
				for(int j=1; j<=N; j++) {
					if(i==j)	continue;
					if(graph[i][j] || graph[j][i])	count++;
				}
				
				if(count == N-1)	result++;
			}
			
			System.out.println("#"+test_case+" "+result);
		}
	}
}
