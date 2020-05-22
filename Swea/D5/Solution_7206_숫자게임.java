import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Solution_7206_숫자게임 {
	static int max;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			String start = br.readLine();
					
			max = 0;

			game(start, 0);
			
			System.out.println("#"+test_case+" "+max);
		}
	}

	private static void game(String number, int turn) {
		int L = number.length()-1;
		
		if(L==0) {	// 숫자를 더이상 쪼갤 수 었을 경우
			max = Math.max(max, turn);
			return;
		}
		
		for(int i=1; i< (1<<L); i++) {
			int mul = 1;
			int num = number.charAt(0)-'0';
			
			for(int j=0; j<L; j++) {
				if((i & 1<<j) == 0) {	// 안쪼갬
					num = num*10 + number.charAt(j+1)-'0';
				} else {				// 쪼갬
					mul *=num;
					num = number.charAt(j+1)-'0';
				}
			}
			
			mul *= num;
			game(mul+"", turn+1);
		}
	}

	
}
