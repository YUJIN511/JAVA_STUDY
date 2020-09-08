import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14569_시간표짜기 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());	// 총 과목 수
		long selected[] = new long [N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());	// 과목 수업시간 수
			
			for(int j=0; j<K; j++) {
				selected[i] |= 1L << Integer.parseInt(st.nextToken());
			}
		}
		int M = Integer.parseInt(br.readLine());	// 학생 수
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());	// 비어있는 교시 개수

			int cnt = 0;
			long num=0;
			for(int j=0; j<p; j++) {
				num |= 1L << Integer.parseInt(st.nextToken());
			}
			
			for(int j=0; j<N; j++) {	// 비어있는 시간 확인
				if((selected[j] & num) == selected[j]) cnt++;	
			}
			System.out.println(cnt);
		}
	}
}
