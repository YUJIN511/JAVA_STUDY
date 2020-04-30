import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014_활주로건설 {
	static int N;
	static int X;
	static int [][]map;
	static int result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 한 변의 크기
			X = Integer.parseInt(st.nextToken());	// 경사로 길이
			
			map = new int[N+1][N+1];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++){
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = 0;
			
			check();
			
			
			System.out.println("#"+test_case+" "+result);
		}
	}

	private static void check() {

		for(int r=0; r<N; r++) {
			int c = 0;
			while(c<N-1) {
				int count=1;
				int nc=c+1;
				
				while(map[r][c] == map[r][nc]) {	// 다른 높이 나올때까지 반복
					count++;
					nc++;
				}
				if(nc==N &&  map[r][c]==map[r][nc-1]) {		// 모두 같은 높이였을 경우 활주로 O
					result++;
					break;
				}
				
				if(Math.abs(map[r][c]-map[r][nc]) > 1) {	// 높이가 1 차이나면 활주로 X
					break;
				}
				
				if(map[r][c]+1 == map[r][nc]) {	// 왼쪽에 활주로 놓을 경우
					
					if(count<X)	break;	// 활주로 길이보다 작으면 X
					
				} else {	// 오른쪽에 활주로 놓을 경우
					if(nc>=N-1) break;	// 경계 체크
					
					count=1;
					int h = map[r][nc];
					nc++;
					while(nc<N && h == map[r][nc] && count<X) {		// 활주로 놓을, 같은 높이의 길이 체크
						count++;
						nc++;
					}
						
					if(count<X) {	// 활주로 길이보다 작으면 X
						break;
					}
					if(h!=map[r][nc])	nc--;	// 다음 체크할 높이와 지금 높이가 같으면, 다음에 활주로가 중복 될 수 있으므로  --> 높이가 같지 않을때만 한칸 땡겨줌  (ex 4 3 3 3 4 ,활주로 2)
				}
				
				c = nc;
				if(c==N-1)	result++;	// 다 체크 한 경우 활주로 O (아닌경우 다 break)
			}
		}
		
		for(int c=0; c<N; c++) {
			int r = 0;
			while(r<N-1) {
				int count = 1;
				int nr = r+1;
				
				while(map[r][c] == map[nr][c]) {
					count++;
					nr++;
				}
				
				if(nr==N && map[r][c]==map[nr-1][c]) {	// 같은 높이였던 경우
					result++;
					break;
				}
				
				if(Math.abs(map[r][c]-map[nr][c]) > 1) {
					break;
				}
				
				if(map[r][c]+1 == map[nr][c]) {
					
					if(count<X)	break;
					
				} else {
					if(nr>=N-1) break;
					
					count=1;
					int h = map[nr][c];
					nr++;
					while(nr<N && h == map[nr][c] && count<X) {
						count++;
						nr++;
					}
						
					if(count<X) {
						break;
					}
					if(h!=map[nr][c])	nr--;
				}
				r = nr;
				if(r==N-1)	result++;
			}
		}
		
	}
}
