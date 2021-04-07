import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main_3116 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			// 끝
			if(A==0) break;
			
			String N = st.nextToken();
			int B = Integer.parseInt(st.nextToken());
			 
			// A진법 > 10진수
			byte num[] = new BigInteger(N, A).toByteArray();
			
			// 10진수 > B진법
			String result = new BigInteger(num).toString(B);
			
			System.out.println(result.toUpperCase());
		}
		
	}
}
