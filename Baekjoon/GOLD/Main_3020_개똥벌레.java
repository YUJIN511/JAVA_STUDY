import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

import org.omg.PortableInterceptor.DISCARDING;
/*
 * <입력>
 * N : 동굴의 길이 (짝수)
 * H : 높이
 * -> 첫번째 장애물은 석순, 다음은 종유석과 석순 번갈아가면서 등장
 * 
 * <출력>
 * 개똥벌레가 파괴해야하는 장애물의 최솟값 
 * + 최솟값의 구간 총 몇개인지
 */
public class Main_3020_개똥벌레 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int bottom[] = new int[H+1];
		int top[] = new int[H+1];

		
		for(int i=1; i<=N/2; i++) {	// 입력
			bottom[Integer.parseInt(br.readLine())]++;
			top[Integer.parseInt(br.readLine())]++;
		}
		

		int sum_bottom[] = new int [H+1];
		int sum_top[] = new int [H+1];
		
		// 개똥벌레의 장애물 파괴
		// 석순
		for(int i=H-1; i>0; i--) {
			sum_bottom[i] =	sum_bottom[i+1]+bottom[i]; 
		}
		// 종유석
		for(int j=2; j<=H; j++) {
			sum_top[j] = sum_top[j-1]+top[H-j+1]; 
		}
		
		int min = Integer.MAX_VALUE;
		int result =0;
		
		for(int h=1; h<=H; h++) {
			int sum = sum_top[h]+ sum_bottom[h];
			if(min == sum) {
				result++;
			}
			else if(min>sum){
				result=1;
				min = sum;
			}
			
		}
		System.out.println(min+" "+result+"\n");
		
	}

}
