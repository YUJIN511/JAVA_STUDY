import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1062_가르침 {
	static int N;
	static int K;
	static int max;
	static ArrayList<Integer> word;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 단어의 개수
		K = Integer.parseInt(st.nextToken());	// 가르칠 글자
		word = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			int temp = 1 << (int)(str.charAt(0)-'a');
			for(int j=1; j<str.length(); j++) {
				temp |= 1 << (int)(str.charAt(j)-'a');
			}
			word.add(temp);
		}
		
		int check = 1;	//'a'
		check |= 1<<(int)('n'-'a');
		check |= 1<<(int)('t'-'a');
		check |= 1<<(int)('i'-'a');
		check |= 1<<(int)('c'-'a');

		int count = 5;
		
		if(count<=K) {
			DFS(check, count, 1);
		}
		System.out.println(max);
	}

	private static void DFS(int check, int count, int s) {
		if(count == K) {
			int temp=0;
			for(int w : word) {
				if( (w & check) == w)	temp++;	// 단어 읽을 수 있는지 체크
			}
			
			max = Math.max(max, temp);
			return;
		}
		for(int i=s; i<=25; i++) {
			if((check & 1<<i) == 0) {	// 선택안한 경우
				DFS(check|(1<<i), count+1, i+1);
			}
		}
		
	}
}
