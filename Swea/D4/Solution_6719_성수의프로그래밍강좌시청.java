import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_6719_성수의프로그래밍강좌시청 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			double M[] = new double[N];
			for(int i=0; i<N; i++) {
				M[i] = Double.parseDouble(st.nextToken());
			}
			Arrays.sort(M);
			double max = 0;
			for(int i=N-K; i<N; i++) {
				max = (max+M[i])/2;
			}
			
			System.out.print("#" + test_case+" ");
			System.out.printf("%.6f\n",max);
			
		}
		
		
	}
}
