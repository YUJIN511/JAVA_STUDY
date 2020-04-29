import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution_5658_보물상자비밀번호 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine()); 
			int N = Integer.parseInt(st.nextToken());	// 숫자 개수
			int K = Integer.parseInt(st.nextToken());	// 크기 순서
			
			char arr[] = new char[N+1];
			arr = br.readLine().toCharArray();
			
			int side = N/4;
			
			ArrayList<Integer> result = new ArrayList<Integer>();
			
			String hex="";
			for(int s=N; s>=N-side; s--) {
				for(int e=s; e<N; e++) {
					hex+=arr[e];
				}
				for(int i=0; i<s; i++) {
					hex+=arr[i];
					
					if(hex.length() == side) {
						if(!result.contains(Integer.parseInt(hex, 16))) {	// 중복 안되게
							result.add(Integer.parseInt(hex, 16));
						}
						hex="";
					}
				}
			}
			result.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return Integer.compare(o2, o1);
				}
			});
			
			System.out.println("#"+test_case+" "+result.get(K-1));
			
		}
	
	}
}
