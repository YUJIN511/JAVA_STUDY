import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_2023_신기한소수 {
	static int N;
	static int numbers[];

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N+1];
		per(0);
	}
	private static void per(int cnt) {
		if(cnt == N) {
			for(int j=0; j<N; j++) {
				System.out.print(numbers[j]);
			}
			System.out.println();
			return;
		}
		for(int i=1; i<10; i++) {
			numbers[cnt]=i;
			if(!check(cnt))	continue;
			per(cnt+1);
		}
	}
	private static boolean check(int cnt) {
		
		if(cnt==0) {	// 첫번째 자리는 2, 3, 5, 7 이어야 함
			if(numbers[0]==2 || numbers[0]==3 || numbers[0]==5 || numbers[0]==7)	return true;
			return false;
		}
		
		int pow=1;
		int num=0;
		for(int i=cnt; i>=0; i--) {
			num += numbers[i]*pow;
			pow*=10;
		}
		
		for(int i=2; i<num; i++) {
			if(num!=i && num%i == 0) return false;
		}
		return true;
	}
}
