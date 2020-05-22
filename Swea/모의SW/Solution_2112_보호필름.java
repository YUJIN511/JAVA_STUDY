import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2112_보호필름 {
	static int D;
	static int W;
	static int K;
	static int film[][];
	static int R;
	static int selected;
	static int AB[];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());	// 두께
			W = Integer.parseInt(st.nextToken());	// 가로 크기
			K = Integer.parseInt(st.nextToken());	// 합격 기준
			film = new int[D][W];
			
			for(int i=0; i<D; i++) {	// 피름 정보 입력
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			R=0;	// 약물 투입 홋수
			while(true) {
				selected=0;
				AB = new int[D];
				if(DFS(0, 0))	break;
				R++;
			}
			
			// 최소의 약품 투입 횟수
			System.out.println("#"+test_case+" "+R);
		}
	}

	private static boolean DFS(int cnt, int s) {
		if(cnt == R) {	// 약물 투입할 막의 경우 수 뽑은 경우
			if(check())	return true;
			return false;
		}
		
		for(int i=s; i<D; i++) {
			if((selected & 1<<i) == 0) {
				selected |= 1<<i;
				AB[i]=0;			
				if(DFS(cnt+1, i+1))	return true;	// 특성 A 투입
				AB[i]=1;			
				if(DFS(cnt+1, i+1))	return true;	// 특성 B 투입
				AB[i]=0;	
				selected &= ~(1<<i);
			}
		}
		
		return false;
	}

	private static boolean check() {
		for(int c=0; c<W; c++) {
			int count=1;
			int f;
			if((selected & 1<<0) == 0)	f = film[0][c];
			else 						f = AB[0];
			int check= f;
			for(int r=1; r<D; r++) {
				// 약물 투입한 셀 적용
				if((selected& 1<<r) ==0)	f = film[r][c];
				else 						f = AB[r];
				
				if(check == f)	count++;	
				else {
					check=f;
					count=1;
				}
				
				if(count==K)	break;	// 합격기준에 맞는 열, 다음 열로
			}
			if(count<K)	return false;	// 합격기준에 안 맞으면 실패
		}
		return true;
	}
}
