import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16986_인싸들의가위바위보 {
	static int N;
	static int K;
	static int result;
	static int A[][];
	static int players[][];
	static boolean selected[];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A= new int [N+1][N+1];
		for(int i=1; i<=N; i++) {	// 상성 정보 입력 (2 : 이김, 1 : 비김, 0 : 짐)
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				A[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		players = new int [3][20];
		for(int i=1; i<=2; i++) {		// 경희, 민호 손동작 번호 입력
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<20; j++) {
				players[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		selected = new boolean[N+1];
		result = 0;
		
		per(0);
		
		System.out.println(result);
	}

	private static boolean per(int cnt) {
		if(cnt==N) {
			if(play()) {
				result=1;
				return true;
			}
			return false;
		}
		
		for(int i=1; i<=N; i++) {
			if(!selected[i]) {
				selected[i]=true;
				players[0][cnt]=i;
				if(per(cnt+1))	return true;
				selected[i]=false;
			}
		}
		return false;
	}

	private static boolean play() {
		int win=0;
		int next=1;
		int count[] = {0, 0, 0};	// 이긴 횟수(지우, 경희, 민호)
		
		int index[] = {0, 0, 0};	//
		while(true) {
			
			int p1 = win;	// 이전 경기 승자
			int p2 = next;	// 이전 경기 미참가자
			
			int r1 = players[p1][index[p1]++];	// 가위바위보 손동작
			int r2 = players[p2][index[p2]++];
			
			switch (A[r1][r2]) {
			case 0:	// 짐
				win=p2;
				break;
			case 1:	// 비김
				win=Math.max(p1, p2);
				break;
			case 2:	// 이김
				win=p1;
				break;

			default:
				break;
			}
			next=3-p1-p2;
			count[win]++;
			
			if(count[0]==K)	return true;
			// 지우가 손동작 다르게 낼 수 있는 횟수넘거나 다른 얘가 먼저 이기면
			if(index[win]>=20 || index[next]>=20)	return false;
			if(index[0]==N || count[1]==K || count[2]==K)	return false;
			
		}
	}
}
