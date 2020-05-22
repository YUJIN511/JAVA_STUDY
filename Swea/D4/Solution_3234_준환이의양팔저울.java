import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3234_준환이의양팔저울 {
	static int N;
	static int weight[];
	static int selected;
	static int selectedRight;
	static int idx[];
	static int result;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			weight = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				weight[i]=Integer.parseInt(st.nextToken());
			}
			
			selected=0;
			idx = new int[N];
			result=0;
			
			per(0);	// 순서 세우기
			
			System.out.println("#"+test_case+" "+result);
		}
	}

	private static void per(int cnt) {
		if(cnt == N) {
			dfs(0,0,0);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if((selected & 1<<i) == 0) {	// 선택안된 경우
				selected |= 1<<i;
				idx[cnt] = i;
				per(cnt+1);
				selected &= ~(1<<i);
			}
		}
	}

	private static void dfs(int cnt, int left, int right) {
		if(cnt == N) {
			result++;
			return;
		}
		
		dfs(cnt+1, left+weight[idx[cnt]], right);
		if(left>= right+weight[idx[cnt]]) {
			dfs(cnt+1, left, right+weight[idx[cnt]]);
		}
		
	}
}	
