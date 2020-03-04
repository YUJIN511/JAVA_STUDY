import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class baek_9251_LCS {
	static int arr[][];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		arr = new int[str1.length()+1][str2.length()+1];
		
		lcs(str1, str2);
		
		System.out.println(arr[str1.length()][str2.length()]);
	}

	private static void lcs(String A, String B) {
		for(int i=1; i<=A.length(); i++) {
			for(int j=1; j<=B.length(); j++) {
				if(A.charAt(i-1)==B.charAt(j-1)) {	// 같은 문자인 경우
					arr[i][j]=arr[i-1][j-1]+1;
				}
				else {	// 다른 문자인 경우
					arr[i][j]=Math.max(arr[i-1][j], arr[i][j-1]);
				}
				
			}
		}
		
	}
}
