import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_15787_기차가어둠을헤치고은하수를 {
	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int train[] = new int [N+1];
		int ins;	// 명령 종류
		int y;		// y번째 기차번호
		int x;		// x번째 좌석
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			ins = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			switch (ins) {
			case 1:	// 사람 태우기
				x = Integer.parseInt(st.nextToken());
				train[y] |= (1 << 20-x);
				break;
			case 2: // 사람 하차
				x = Integer.parseInt(st.nextToken());
				train[y] &= ~(1 << 20-x);
				break;
			case 3: // 한칸씩 뒤로
				train[y] = train[y] >> 1;
				break;
			case 4: // 한칸씩 앞으로
				train[y] = train[y] << 1;
				train[y] = train[y] &((1<<20)-1);	// 앞자리 0으로
				break;

			default:
				break;
			}
		}
		
		boolean check[] = new boolean[(int)Math.pow(2, 25)];
		// 기차 수 출력
		int result=0;
		for(int i=1; i<=N; i++) {
			if(check[train[i]]==true) continue;
			check[train[i]]=true;
			result++;
		}
		System.out.println(result);
	}
}
