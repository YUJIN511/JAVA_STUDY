import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution_5658_보물상자비밀번호1 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int rotate = N/4;
			String input = br.readLine();
			
			input = input+input;
			
			Set<String> set = new HashSet<>();
			int[] numArr = new int[N*2];
			int s = N;
			
			for(int i=0; i<rotate; i++) {
				for(int j=0; j<4; j++) {
					String str = input.substring(s+(j*rotate), s+(j*rotate)+rotate);
					if(!set.contains(str)) {	// 중복 확인
						set.add(str);
						int num = Integer.parseInt(str,16);
						numArr[set.size()]=num;
					}
				}
				s--;
			}
			
			Arrays.sort(numArr);
			
			System.out.println("#"+testCase+" "+numArr[numArr.length-K]);
		}
	
	}
}
