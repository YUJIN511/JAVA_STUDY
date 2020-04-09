import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_9659_다항식계산 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			long f[] = new long[N+1];
			
			int t[] = new int[N+1];
			int a[] = new int[N+1];
			int b[] = new int[N+1];
			
			for(int i=2; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				t[i] = Integer.parseInt(st.nextToken());
				a[i] = Integer.parseInt(st.nextToken());
				b[i] = Integer.parseInt(st.nextToken());
			}
			
			int M = Integer.parseInt(br.readLine());
            long result[] = new long[M+1];
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<M; i++) {
				int x = Integer.parseInt(st.nextToken());
				f[0] = 1;
				f[1] = x;

				for(int j=2; j<=N; j++) {
					if(t[j]==1) {
						f[j] = (f[a[j]] + f[b[j]]) % 998244353;
					}
					else if(t[j]==2) {
						f[j] = (a[j] * f[b[j]]) % 998244353;
					}
					else {
						f[j] = (f[a[j]] * f[b[j]]) % 998244353;
					}
				}
                result[i] = f[N];
			}
			System.out.print("#"+test_case+" ");
            for(int i=0; i<M; i++){
                System.out.print(result[i]+" ");
            }
            System.out.println();
		}
	}
}
