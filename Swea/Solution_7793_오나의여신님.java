import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution_7793_오나의여신님 {

	static int N;
	static int M;
	static char map[][];
	static int dir[][]= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static ArrayDeque<int[]> devil;
	static ArrayDeque<int[]> SY;

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char [N][N];
			
			String str;
			devil = new ArrayDeque<>();
			SY = new ArrayDeque<>();

			for(int i=0; i<N; i++) {
				str = br.readLine();
				map[i]=str.toCharArray();
				
				for(int j=0; j<M; j++) {
					if(map[i][j]=='*') {
						devil.offer(new int[] {i,j});
					}
					else if(map[i][j]=='S') {
						SY.offer(new int[] {i,j,0});
					}
				}
			}

			int result=-1;
			
			while(true) {
				if(SY.isEmpty())	break;
				
				bfsDevil();
				
				result = bfsSY();
				if(result!=-1)	break;
			}
			
			if(result==-1) {
				System.out.println("#"+test_case+" GAME OVER");
			}
			else	System.out.println("#"+test_case+" " +result);
		}
	}

	private static int bfsSY() {
		
		int size = SY.size();
		
		for(int i=0; i<size; i++) {
			
			int cur[]=SY.poll();
			int sr=cur[0];
			int sc=cur[1];
			int count = cur[2];
			
			for(int d=0; d<4; d++) {
				int nr = sr + dir[d][0];
				int nc = sc + dir[d][1];
					
				if(nr<0 || nr>=N || nc<0 || nc>=M)	continue;
				
				if(map[nr][nc]=='D') {
					return count+1;
				}else if(map[nr][nc]=='.') {
					map[nr][nc]='S';
					SY.offer(new int[] {nr, nc, count+1});
				}
			}
		}
		return -1;
	}

	private static void bfsDevil() {
		
		// 악마의 손아귀 스킬
		int size = devil.size();
		for(int i=0; i<size; i++) {
			
			int cur[]=devil.poll();
			int dr=cur[0];
			int dc=cur[1];
			
			for(int d=0; d<4; d++) {
				int nr = dr + dir[d][0];
				int nc = dc + dir[d][1];
					
				if(nr>=0 && nr<N && nc>=0 && nc<M ) {
					if(map[nr][nc]=='D' || map[nr][nc]=='X' || map[nr][nc]=='*')	continue;
					
					map[nr][nc]='*';
					devil.offer(new int[] {nr, nc});
				}
					
			}
		}
	}
}

