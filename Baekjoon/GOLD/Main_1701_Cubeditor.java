import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_1701_Cubeditor {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		int result = 0;
		for(int i=0; i<str.length(); i++) {
			result = Math.max(result, getPi(str.substring(i,str.length())));
			if(str.length()-i < result)	break;
		}
		
		System.out.println(result);
	}

	private static int getPi(String pattern) {
		
		char p[] = pattern.toCharArray();
		int pi[] = new int[pattern.length()]; 
		int max = 0;
		
		int j=0;
		
		for(int i=1; i<pattern.length(); i++) {
			while(j>0 && p[i]!=p[j]) {
				j = pi[j-1];	// 건너뛰기
			}
			if(p[i]==p[j]) {	// 접미사 접두사 같은지
				pi[i] = ++j;	// 같으면 다음 idx
				max = Math.max(pi[i], max);
			}
		}
		return max;
	}
}
