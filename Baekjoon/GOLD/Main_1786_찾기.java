import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_1786_찾기 {
	static List<Integer> result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 입력
		String text = br.readLine();
		String pattern = br.readLine();
		
		result = new ArrayList<>();
		int num = KMP(text, pattern);
		
		// 결과 출력
		System.out.println(num);
		for(int i=0; i<num; i++) {
			System.out.print(result.get(i)+" ");
		}
		System.out.println();
	}

	private static int[] getPi(String pattern) {
		
		int[] pi = new int [pattern.length()];
		int j = 0;
		
		for(int i=1; i<pattern.length(); i++) {
			
			while(j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
				j = pi[j-1];
			}
			
			if(pattern.charAt(j) == pattern.charAt(i)) {
				pi[i] = ++j; 
			}
		}
		return pi;
	}
	
	private static int KMP(String text, String pattern) {
		int [] jump = getPi(pattern);
		
		int j = 0;
		int check = 0;
		
		for(int i=0; i< text.length(); i++) {
			while(j > 0 && text.charAt(i) != pattern.charAt(j)) {
				j = jump[j-1];
			}
			if(text.charAt(i) == pattern.charAt(j)) {
				if(j == pattern.length()-1) {
					result.add(i - j +1 );
					check++;
					j = jump[j];
				}
				else {
					j++;
				}
			}
		}
		
		return check;
	}


}
