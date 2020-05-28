import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_3671_산업스파이의편지 {

	static boolean selected[];
	static boolean visited[];
	static int num;
	static String str;
	static int R;
	static int result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int c = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=c; testcase++) {
			
			str = br.readLine();
			selected = new boolean[str.length()];
			visited = new boolean[(int) Math.pow(10, str.length())];
			result=0;
			
			for(int i=1; i<=str.length(); i++) {
				R=i;
				num=0;
				prime(0);
			}
			System.out.println(result);
		}
		

	}

	private static void prime(int cnt) {
		if(cnt == R ) {
			if(visited[num])	return;
			visited[num]=true;
			
			if(R==1) {
				if(num==1 || num==4 || num==6 || num==8 || num==9) {
					return;
				}
			}
			else {
				for(int j=2; j<=Math.sqrt(num); j++) {
					if(num%j == 0 )	return;
				}
			}
			result++;
			return;
		}
		
		for(int i=0; i<str.length(); i++) {
			if(!selected[i]) {
				if(cnt==0 && str.charAt(i)-'0' ==0) continue;
				
				selected[i]=true;
				num += Math.pow(10, R-cnt-1)*(str.charAt(i)-'0');
				prime(cnt+1);
				num -= Math.pow(10, R-cnt-1)*(str.charAt(i)-'0');
				selected[i]=false;
			}
			
		}
		
	}

}
