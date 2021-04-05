import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1244 {
	static int R;
	static String input;
	static char numbers[];
	static int max;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		for(int testCase=1; testCase<=TC; testCase++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			input = st.nextToken();	// 카드 숫자 정보
			R = Integer.parseInt(st.nextToken());	// 교환 횟수
			
			numbers = new char[input.length()];
			numbers=input.toCharArray();
			max = 0;

			dfs(0, 0);
			
			System.out.println("#"+testCase+" "+max);
		}
	}

	private static void dfs(int s, int cnt) {
		
		if(cnt == R) { // 교환횟수 다 쓴 경우
			StringBuilder str = new StringBuilder();
			for(int k=0; k<numbers.length; k++) {
				str.append(numbers[k]);
			}
			
			int result = Integer.parseInt(str.toString());
			max = Math.max(max, result);
			return;
		}
		
		for(int i=s; i<input.length(); i++) {
			for(int j=i+1; j<input.length(); j++) {
				char temp = numbers[i];
				numbers[i]= numbers[j];
				numbers[j]= temp;
				
				dfs(i, cnt+1);
				temp = numbers[i];
				numbers[i]= numbers[j];
				numbers[j]= temp;
			}
		}
		
	}
}
