import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_1300_K번째수 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	//  배열의 크기
		int K = Integer.parseInt(br.readLine());	//	인덱스
		
		int left = 1;
		int right = K;
		int result = 0;
		while(left<=right) {
			int mid = (left+right)/2;
			int count = 0;
			for(int i=1; i<=N; i++ ) {
				count += Math.min(mid/i, N);	// i행에 mid보다 작은 숫자들의 개수
			}
			if(count < K) {
				left = mid+1;
			}
			else {
				result = mid;
				right = mid-1;
			}
		}
		
		System.out.println(result);
	}
}
