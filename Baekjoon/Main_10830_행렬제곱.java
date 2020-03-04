import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10830_행렬제곱{
	static int N;
	static long B;
	static long A[][];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		A = new long[N][N];
		long[][] result = new long[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				A[i][j]=Integer.parseInt(st.nextToken());
				result[i][j]=A[i][j];
			}
		}

		result = division(B);
		
		
		// 결과 출력
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(result[i][j]%1000+" ");
			}
			System.out.println();
		}
	}

	private static long[][] division(long n) {	// n:몫    r: 나머지
		if(n==1) 	return A;
		
		if(n%2 == 0)	return pow1(division(n/2));
		else			return pow2(pow1(division(n/2)),A);
	}

	private static long[][] pow1(long x[][]) {
		long sum;
		long temp[][] = new long [N][N];
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				sum=0;
				for(int j=0; j<N; j++) {
					sum+=x[r][j]*x[j][c];
				}
				temp[r][c]=sum%1000;
			}
		}
		
		return temp;
	}
	private static long[][] pow2(long x[][], long y[][]) {
		long sum;
		long temp[][] = new long [N][N];
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				sum=0;
				for(int j=0; j<N; j++) {
					sum+=x[r][j]*y[j][c];
				}
				temp[r][c]=sum%1000;
			}
		}
		
		return temp;
	}
	
}
