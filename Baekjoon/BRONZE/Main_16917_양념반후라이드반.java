import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16917_양념반후라이드반 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());	// 양념치킨 가격
		int B = Integer.parseInt(st.nextToken());	// 후라이드치킨 가격
		int C = Integer.parseInt(st.nextToken());	// 반반치킨 가격
		int X = Integer.parseInt(st.nextToken());	// 양념치킨 최소
		int Y = Integer.parseInt(st.nextToken());	// 후라이드 최소
		
		int result=0;
		if(C*2 < A+B) {	// 반반치킨이 더 이득일 경우
			int half = Integer.min(X, Y);
			result = C*2*half;
			X-=half;
			Y-=half;
		} 
		
		result += Integer.min(A, C*2)*X;
		result += Integer.min(B, C*2)*Y;
		
		System.out.println(result);
	}
}
