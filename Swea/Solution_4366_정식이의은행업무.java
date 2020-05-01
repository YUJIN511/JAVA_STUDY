import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution_4366_정식이의은행업무 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			String b = br.readLine();
			String t = br.readLine();
			
			int result = 0;
			String bb="";
			String tt="";
			String temp="";
			Top:
			for(int i=0; i<b.length(); i++) {	// 2진수
				if(i!=0) temp = b.substring(0,i);
				temp += (b.charAt(i) == '1')? "0":"1";
				bb = temp.concat(b.substring(i+1,b.length()));
				for(int j=0; j<t.length(); j++) {	// 3진수
					for(char k : new char[] {'0','1','2'}) {
						if(k == t.charAt(j))	continue;
						temp="";
						if(j!=0) temp = t.substring(0,j);
						temp += k;
						tt = temp+t.substring(j+1, t.length());
						if(Integer.parseInt(bb,2) == Integer.parseInt(tt,3)) {
							result = Integer.parseInt(bb, 2);
							break Top;
						}
					}
				}
			}
			
			
			System.out.println("#"+test_case+" "+result);
		}
	}
	
}
