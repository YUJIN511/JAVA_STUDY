import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1613_역사 {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());	// 사건의 개수
		int m = Integer.parseInt(st.nextToken());	// 사건 전후 관계의 개수
		
		boolean history[][] = new boolean[n+1][n+1];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			history[a][b]=true;
		}
		
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(history[i][k] && history[k][j])	history[i][j]=true;
				}
			}
		}
		
		int s = Integer.parseInt(br.readLine());
		for(int i=0; i<s; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(history[a][b])		System.out.println(-1);
			else if(history[b][a])	System.out.println(1);
			else					System.out.println(0);
		}
	}
}
