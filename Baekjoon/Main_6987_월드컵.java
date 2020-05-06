import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_6987_월드컵 {
	static int[] win;
	static int[] draw;
	static int[] lose;
	static int [] result;
	static int[] state1 = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 };
    static int[] state2 = { 1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5 };
    
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		win = new int[6];
		draw = new int[6];
		lose = new int[6];
		result = new int[4];
		
		for(int i=0; i<4; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<6; j++) {	// 입력
				win[j]= Integer.parseInt(st.nextToken());
				draw[j]= Integer.parseInt(st.nextToken());
				lose[j]= Integer.parseInt(st.nextToken());
			}
			
			DFS(i, 0);
		}
		for(int r : result) {	// 결과 출력
			System.out.print(r+" ");
		}
		System.out.println();
	}
	private static void DFS(int i, int cnt) {
		if(cnt == 15) {
			for(int j=0; j<6; j++) {
				if(win[j]!=0 || draw[j]!=0 || lose[j]!=0)	return;
			}
			result[i]=1;
			return;
		}
		
		// 승 패
		int s1 = state1[cnt];
		int s2 = state2[cnt];
		
		win[s1]--;
		lose[s2]--;
		DFS(i,cnt+1);
		win[s1]++;
		lose[s2]++;
		// 패 승
		lose[s1]--;
		win[s2]--;
		DFS(i,cnt+1);
		lose[s1]++;
		win[s2]++;
		// 무승부
		draw[s1]--;
		draw[s2]--;
		DFS(i,cnt+1);
		draw[s1]++;
		draw[s2]++;
	}
}
