import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution_1257 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			int K = Integer.parseInt(br.readLine());
			String str = br.readLine();
			String strs[] = new String[str.length()];
			
			for(int i=0; i<str.length(); i++) {
				strs[i] = str.substring(i);
			}
			Arrays.sort(strs);
			
			ArrayList<String> list = new ArrayList<String>();
			
			Top:
			for(int i=0; i<strs.length; i++) {
				for(int j=1; j<=strs[i].length(); j++) {
					String temp = strs[i].substring(0, j);
					if(!list.contains(temp)) {
						list.add(temp);		
					}
					if(list.size()==K) break Top;
				}
			}
			
			if(list.size()<K) {
				System.out.println("#"+test_case+" none");
			} else {
				System.out.println("#"+test_case+" "+list.get(K-1));				
			}
		}
	}
}
