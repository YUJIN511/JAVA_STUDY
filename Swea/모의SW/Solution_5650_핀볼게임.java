import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5650_핀볼게임 {
	static int map[][];
	static int worm[][];  // 0     1     2      3
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};	// 상하좌우
	static int block[][] = {{},
							{1,3,0,2},	//1
							{3,0,1,2},	//2
							{2,0,3,1}, 	//3
							{1,2,3,0},	//4
							{1,0,3,2}};	//5
	static int N;
	static int result;
	static int sr, sc;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for(int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N+2][N+2];
			worm = new int[11][4];
			
			for(int i=1; i<=N; i++) {	// 데이터 입력
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
					
					if(num>=6 && num<=10) {	// 웜홀 체크
						if(worm[num][0]==0) {
							worm[num][0]=i;
							worm[num][1]=j;
						}
						else {
							worm[num][2]=i;
							worm[num][3]=j;
						}
					}
				}
			}
			
			int max = 0;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(map[i][j]==0) {
						for(int d=0; d<4; d++) {
							result = 0;
							sr=i; sc=j;
							game(i, j, d);	// 빈칸에서 시작
							max = Math.max(max, result);
						}
					}
				}
			}
			
			System.out.println("#"+test_case+" "+max);

		}
	}

	private static void game(int r, int c, int d) {

		r += dir[d][0];
		c += dir[d][1];
		
		while(r>=1 && r<=N && c>=1 && c<=N && map[r][c] == 0 ) {	// 빈칸 아닐때까지 이동
			if(r==sr && c==sc)	return;
			r += dir[d][0]; 
			c += dir[d][1]; 
		}
		
		if(r<1 || r>N || c<1 || c>N) {	// 벽에 부딪히는 경우
			result++;
			d = block[5][d];
			game(r, c, d);
		}
		
		int num = map[r][c];
		
		if(num == -1) {	// 블랙홀
			
			return;	
			
		} else if(num >=6 && num<=10) {	// 웜홀
			if(worm[num][0]==r && worm[num][1]==c) {
				game(worm[num][2], worm[num][3],d);
			}
			else {
				game(worm[num][0], worm[num][1],d);
			}
			
		} else if(num>=1 && num<=5) {	// 블록
			result++;
			game(r, c, block[num][d]);	
		}
		
		return;
	}
}
