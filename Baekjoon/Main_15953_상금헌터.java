import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15953_상금헌터 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		int A;
		int B;
		int result;
		
		for(int i=0; i<T; i++) {
			result = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			if(A>0) {
				if(A==1) {
					result+=500;
				}
				else if(A<4) {
					result +=300;
				}
				else if(A<7) {
					result+=200;
				}
				else if(A<11) {
					result+=50;
				}
				else if(A<16) {
					result+=30;
				}
				else if(A<22) {
					result+=10;
				}
			}
			if(B>0) {
				if(B==1) {
					result+=512;
				}
				else if(B<4) {
					result +=256;
				}
				else if(B<8) {
					result+=128;
				}
				else if(B<16) {
					result+=64;
				}
				else if(B<32) {
					result+=32;
				}
			}
			System.out.println(result*1000);
		}
		
	}
}
