import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1620 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String phone = st.nextToken();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		if(phone.charAt(phone.length()-1)=='-') phone = phone+"0";
		st = new StringTokenizer(phone, "-");
		
		String result = "INPUT ERROR!";
		int idx = 1;
		
		while(st.hasMoreTokens()) {
			String str = st.nextToken();
			
			// 숫자더미가 4자리 넘는 경우
			if(str.length()>4) {
				result = "INPUT ERROR!";
				break;
			}
			
			if(idx++ == m) {
				StringBuilder newStr = new StringBuilder(str);
				while(newStr.length()<4) {
					newStr.insert(0, 0);
				}
				str = newStr.toString();
				newStr = new StringBuilder();
				
				for(int i=0; i<str.length(); i++) {
					int temp = Integer.parseInt(str.charAt(i)+"")+n;
					newStr.append(temp%10);
				}
				
				result = newStr.toString();
			}
		}
		
		System.out.println(result);
	}
}
