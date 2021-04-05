import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_1234 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case=1; test_case<=10; test_case++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			String input = st.nextToken();
			
			Stack<Integer>s = new Stack<Integer>();
			int idx = 0;
			
			loop:
			while(idx<N) {
				int num =input.charAt(idx)-'0';
				if(!s.isEmpty() && s.peek()==num) {
					while(!s.isEmpty() && s.peek()==num) {
						s.pop();
						if(idx+1==N) break loop;
						num = input.charAt(++idx)-'0';
					}
				} 
				s.push(num);
				
				idx++;
			}
			
			System.out.print("#"+test_case+" ");
			StringBuilder result = new StringBuilder();
			while(!s.isEmpty()) {
				result.insert(0,s.pop());
			}
			System.out.println(result);
		}
	}
}
