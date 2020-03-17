import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_요리사 {

	static int N;
	static int [][]arr;
	static int []numbers;
	static boolean []selected;
	static int result;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
	
		for(int test_case=1; test_case<=T; test_case++) {
			result = Integer.MAX_VALUE;
			N=Integer.parseInt(br.readLine());
			arr= new int[N][N];
			numbers= new int[N/2];
			selected= new boolean[N];
			
			numbers[0]=0;
			selected[0]=true;
			
			for(int i=0; i<N; i++) { // 배열 입력
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			cook(1,1);
			
			System.out.println("#"+test_case+" "+result);
		}
		
	}
	
	static void cook(int cnt, int start) {
		if(cnt==N/2) {
			int A=0;
			int B=0;
			
			for(int y=0; y<N; y++) {
				if(selected[y]) {
					for (int x=0; x<N; x++) {
						if(x!=y && selected[x])	A+=arr[y][x];
					}
				}
				else {
					for (int x=0; x<N; x++) {
						if(x!=y && !selected[x])	B+=arr[y][x];
					}
				}
			}

			if(result>Math.abs(A-B))	result = Math.abs(A-B);
			return;
		}
		
		for(int i=start; i<N; i++) {
			if(!selected[i]) {			// i 숫자가 사용되지 않은 경우
				numbers[cnt]=i;			// i 숫자를 배정
				selected[i]=true;		// i 숫자를 사용했다고 표시
				cook(cnt+1, i+1);
				selected[i]=false;		// 다른 순열로 생성을 위해 미사용으로 표시
			}
		}
	}

}
