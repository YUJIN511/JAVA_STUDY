import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_16916_부분문자열 {
	
	private static int[] getPi(String pattern) {
		int[] pi = new int[pattern.length()];
		int j = 0;
		
		for(int i=1; i<pattern.length(); i++) {
			while(j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			if(pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}
	
	private static int KMP(String parent, String pattern) {
		int[] jump = getPi(pattern);
		
		int j = 0;
		
		for(int i=0; i<parent.length(); i++) {
			while(j>0 && parent.charAt(i) != pattern.charAt(j)) {
				j = jump[j-1];
			}
			
			if(parent.charAt(i) == pattern.charAt(j)) {
				if(j == pattern.length()-1)	return 1;
				else 						j++;
			}
			
		}
		return 0;
	}

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String parent = br.readLine();
		String pattern = br.readLine();
		
		System.out.println(KMP(parent, pattern));
	}

}
