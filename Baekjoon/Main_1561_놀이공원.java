import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1561_놀이공원 {
	
	static int N;
	static int M;
	static int arr[];

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// N명
		M = Integer.parseInt(st.nextToken());		// 놀이기구
		
		arr = new int [M];
		long left=0;
		long right=(long)200000000*300;
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<M; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		if(N<=M) {
			System.out.println(N);
			return;
		}
		
		long mid=0;
		long t=0;
		long num;
		while(left<=right) {

			mid=(right+left)/2;
			
			num = time(mid);
			if(num>=N) {			// 사람 숫자가 많다, 시간이 넘는다
				right = mid-1;
				t=mid;	
			}
			else {					// 시간이 부족할 때
				left= mid+1;
			}
		}
		int result=0 ;
		num=time(t-1);			// 마지막으로 탄 사람 시간 이전에 몇명 탔는지
		
		for(int i=0; i<M; i++) {	// 몇번째에 마지막 사람이 탔는지
			if(t%arr[i]==0) {
				num++;
				if(num>=N) {
					result=i+1;
					break;
				}
			}
			
		}
		System.out.println(result);
	}
	private static long time(long t) {
		long num=M;
		
		for(int i=0; i<M; i++) {	// t시간이 끝났을때 놀이기구 탄 사람
			num+=(t/arr[i]);
		}
		
		return num;
	}
}
