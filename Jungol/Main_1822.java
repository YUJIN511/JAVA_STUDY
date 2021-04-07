import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1822 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());	// ��ü�� �Ǵ� ��
		int b = Integer.parseInt(st.nextToken());	// ��ü ���� �׸�
		int d = Integer.parseInt(st.nextToken());	// �״� ��
		int N = Integer.parseInt(st.nextToken());	// ������ ��
		
		int dp[] = new int[N+1];	// ��� ��ǥ

		dp[0]=1;
		for(int i=1; i<=N; i++) {
			if(i<a) { // ��ü �ɶ�����
				dp[i]=dp[i-1];
			}else if(i<b) { // ���� + �¾
				dp[i] = dp[i-1] + dp[i-a];
			}else {	// ���� + �׾ - ���ĸ���
				dp[i] = dp[i-1] + dp[i-a] - dp[i-b];
			}
			dp[i]=(dp[i]+1000)%1000;
		}
		
		if(d<=N) dp[N] -= dp[N-d];
		
		System.out.println((dp[N]+1000)%1000);
	}
}
