import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution_1868_파핑파핑지뢰찾기 {
	
	static int N;
	static char map[][];
	static boolean visit[][];
	static int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	static int result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new char [N][N];
			visit = new boolean [N][N];
			
			String str;
			for(int i=0; i<N; i++) {	// 입력
				str = br.readLine();
				map[i]=str.toCharArray();
			}
			
			result=0;	// 클릭 횟수
				
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]!='.')	continue;
					
					if(count(i, j)==0) {
						result++;
						DFS(i, j);
					}
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]=='.')	result++;
				}
			}
			System.out.println("#"+test_case+" "+result);
		}
	}

	private static int count(int r, int c) {
		int mine =0;
		
		for(int d=0; d<8; d++) {
			int nr = r+dir[d][0];
			int nc = c+dir[d][1];
			if(nr>=0 && nr<N && nc>=0 && nc<N && map[nr][nc]=='*')	mine++;
		}
		return mine;
	}

	private static void DFS(int r, int c) {
		visit[r][c]=true;
		int mine = count(r, c);
		
		map[r][c] = (char)(mine+'0');
		
		if(map[r][c]!='0')return;
		
		for(int d=0; d<8; d++) {
			int nr = r+dir[d][0];
			int nc = c+dir[d][1];
			if(nr>=0 && nr<N && nc>=0 && nc<N && map[nr][nc]=='.' && !visit[nr][nc]) {
				DFS(nr, nc);
			}
		}
		
		
	}
}
