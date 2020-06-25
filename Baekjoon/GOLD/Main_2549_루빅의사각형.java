import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2549_루빅의사각형 {
	static int arr[][];
	static int list[][];
	static int resultCnt;
	static int resultList[][];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		arr = new int[4][4];
		list = new int[7][3];
		resultList = new int[7][3];

		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		resultCnt = 8;
		dfs(0);
		
		System.out.println(resultCnt);
		for(int i=0; i<resultCnt; i++) {
			System.out.println(resultList[i][0]+" "+resultList[i][1]+" "+resultList[i][2]);
		}
	}

	private static void dfs(int cnt) {
		
		// 최대 7번 이동 가능
		if(cnt == resultCnt)	return;
		
		// 배열 원래 배열과 같은지 확인
		
		int check = count();
		
		if(check==0) {
			resultCnt = cnt;
			for(int i=0; i<resultCnt; i++) {
				System.arraycopy(list[i], 0, resultList[i], 0, list[i].length);
			}
			return;
		}
		
		if(check % 4 == 0 && (check/4 + cnt) >= resultCnt)	return;
		if(check % 4 != 0 && (check/4 + cnt + 1) >= resultCnt)	return;
		
		// 행 이동(1), 열 이동(2)
		for(int rc=1; rc<3; rc++) {
			for(int idx=0; idx<4; idx++) {
				for(int m=1; m<4; m++) {
					list[cnt][0]=rc;
					list[cnt][1]=idx+1;
					list[cnt][2]=m;						
					moveArr(rc,idx,m);
					dfs(cnt+1);
					moveArr(rc, idx,(4-m));
				}
			}				
		}
	}

	private static void moveArr(int flag, int idx, int m) {
		int temp[] = new int[4];
		
		if(flag == 1) {	// 행 이동
			// 복사
			for(int i=0; i<4; i++)	temp[i] = arr[idx][i];
			// 이동
			for(int i=0; i<4; i++) {
				arr[idx][(i+m)%4] = temp[i];
			}
		} else {	// 열 이동
			// 복사
			for(int i=0; i<4; i++)	temp[i] = arr[i][idx];
			// 이동
			for(int i=0; i<4; i++) {
				arr[(i+m)%4][idx] = temp[i];
			}
		}
	}

	private static int count() {
		int count=0;
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(arr[i][j] != (i*4)+(j+1))	count++;
			}
		}
		return count;
	}
}
