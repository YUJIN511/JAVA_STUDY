import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1701 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String DNA = br.readLine();	// DNA ¼­¿­
		int L = DNA.length();
		// at, gc 
		int dp[][] = new int[L][L];
		for(int e=1; e<L; e++) {
			for(int s=e-1; s>=0; s--) {
				if((DNA.charAt(s)=='a' && DNA.charAt(e)=='t')
					||(DNA.charAt(s)=='g' && DNA.charAt(e)=='c')) {
					dp[s][e] = dp[s+1][e-1]+2;
				}
				for(int k=0; k<e; k++) {
					if(dp[s][e] < dp[s][k] + dp[k+1][e]) {
						dp[s][e] = dp[s][k] + dp[k+1][e];
					}
				}
			}
		}
		
		System.out.println(dp[0][L-1]);
	}
}
