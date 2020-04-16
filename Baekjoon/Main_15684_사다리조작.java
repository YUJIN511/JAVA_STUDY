import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15684_사다리조작 {
	
	static int N;
	static int H;
	static int [][] ladder;
	static int [][] selected;
	static int result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());		// 세로선의 개수
		int M = Integer.parseInt(st.nextToken());	// 가로선의 개수
		H = Integer.parseInt(st.nextToken());		// 세로선마다 가로선을 놓을 수 있는 위치의 개수
		
		ladder = new int[H+1][N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			ladder[r][c] = 1;		//  1 : 사다리 오른쪽 연결
			ladder[r][c+1] = -1;	// -1 : 사다리 왼쪽 연결 
		}
		
		result = 0;	// 추가해야 하는 가로선 개수의 최솟값
		
		while(result<4) {
			
			selected = ladder.clone();
			
			if(per(0))		break;
			else				result++;
		}
		
		if(result<4)	System.out.println(result);
		else			System.out.println(-1);
	}

	private static boolean per(int cnt) {
		if(cnt == result) {
			for(int i=1; i<=N; i++) {
				int r = 1;
				int c = i;
				while(r <= H) {	// 사다리 끝까지 내려갈때까지
					if(selected[r][c]!=0) {
						if(selected[r][c]==-1) {	// 왼쪽
							c--;
						}
						else {	// 오른쪽
							c++;
						}
					}
					r++;
				}
				if(c != i)	return false;
			}
			return true;
		}
		for(int i=1; i<=H; i++) {
			for(int j=1; j<N; j++) {
				if(selected[i][j]==0) {
					if(j+1==N || selected[i][j+1]==0) {	// 가로선 연속되지 않게 체크
						selected[i][j]=1;
						selected[i][j+1]=-1;
						if(per(cnt+1))	return true;
						selected[i][j]=0;
						selected[i][j+1]=0;
					}
				}
			}
		}
		return false;
	}
}
