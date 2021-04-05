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

public class Solution_1230 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case=1; test_case<=10; test_case++) {
			int N= Integer.parseInt(br.readLine());	// ��ȣ���� ����

			ArrayList<Integer> list = new ArrayList<Integer>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			int M = Integer.parseInt(br.readLine());	// ��ɾ� ����
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				String m = st.nextToken();	// ���
				
				if(m.equals("I")) {	// ����
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken()); 
					for(int j=0; j<y; j++) {
						list.add(x++, Integer.parseInt(st.nextToken()));
					}
				} else if(m.equals("D")) { // ����
					int x = Integer.parseInt(st.nextToken())-1;
					int y = Integer.parseInt(st.nextToken()); 
					for(int j=0; j<y; j++) {
						list.remove(x);
					}
				} else { // �߰�
					int y = Integer.parseInt(st.nextToken()); 
					for(int j=0; j<y; j++) {
						list.add(Integer.parseInt(st.nextToken()));
					}
				}
			}
			
			System.out.print("#"+test_case+" ");
			for(int i=0; i<10; i++) {
				System.out.print(list.get(i)+" ");
			}
			System.out.println();
		}
	}
}
