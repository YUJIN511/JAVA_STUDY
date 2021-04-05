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

public class Solution_1225 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case=1; test_case<=10; test_case++) {
			int TC= Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			Queue<Integer> q = new ArrayDeque<Integer>();
			for(int i=0; i<8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			int cnt=1;
			while(true) {
				int num = q.poll();
				if(num-cnt<=0) {
					q.offer(0);
					break;
				}
				else {
					q.offer(num-cnt);
				}
				cnt = (cnt%5)+1;
			}
			
			System.out.print("#"+TC+" ");
			while(!q.isEmpty()) {
				System.out.print(q.poll()+" ");
			}
			System.out.println();
		}
	}
}
