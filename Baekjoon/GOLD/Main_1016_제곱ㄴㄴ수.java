import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1016_제곱ㄴㄴ수 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());
		long count=0;
		boolean arr[] = new boolean[1000001];	
		
		for(long i=2; i<=Math.sqrt(max); i++) {
			long n=i*i;
			if(min/n*n==min)	arr[(int) (min/n*n-min)]=true; // min값도 제곱수일때 체크
			for(long j=(min/n)+1; n*j<=max; j++) {	// 최소부터 (min에서 나올 수 있는) 시작하여 제곱 수 체크
				arr[(int) ((n*j)-min)]=true;
			}
		}
		
		for(int i=0; i<=max-min; i++) {
			if(!arr[i])count++;
		}
		
		System.out.println(count);
	}
}
