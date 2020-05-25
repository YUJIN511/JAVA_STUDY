import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_18119_단어암기 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int check = ~(-1<<27);
		int strs[] = new int [N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<str.length(); j++) {
				int idx = str.charAt(j)-'a';
				strs[i] |= 1<<idx;
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken());
			int x = st.nextToken().charAt(0)-'a';
			
			if(o==1) {	// 잊을 경우
				check &= ~(1<<x);
			} else {	// 기억해낼 경우
				check |= 1<<x;
			}
			
			int count =0;
			for(int j=0; j<N; j++) {	// 문자열 체크
				if((strs[j] & check) == strs[j]) {
					count++;
				}
			}
			System.out.println(count);
		}
	}
}
