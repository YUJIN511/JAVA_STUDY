import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15685_드래곤커브 {
	static int N;
	static int dir[][] = {{0,1}, {-1,0}, {0,-1}, {1,0}};	// 우상좌하
	static int check[][] = {{0,1}, {1,0}, {1,1}};
	static boolean map[][];
	static ArrayList<data> dragon;
	
	static class data{
		int y;
		int x;
		int d;
		
		public data(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());	// 드래곤 커브 개수
		map = new boolean[101][101];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());	// 시작 좌표
			int y = Integer.parseInt(st.nextToken());	
			int d = Integer.parseInt(st.nextToken());	// 시작 방향
			int g = Integer.parseInt(st.nextToken());	// 세대
			
			int ny = y + dir[d][0];
			int nx = x + dir[d][1];
			map[y][x] = true;	// 0세대 시작점 체크
			map[ny][nx] =true;	// 0세대 끝점 체크

			dragon = new ArrayList<data>();
			dragon.add(new data(y,x,d));
			
			for(int j=0; j<g; j++) {	// 드래곤 커브 1세대부터~
				curv();
			}
		}		
		
		System.out.println(check());	// 정사각형 개수 출력
	}

	private static void curv() {
		int idx = dragon.size()-1;

		int d = dragon.get(idx).d;
		int y = dragon.get(idx).y + dir[d][0];	// 드래곤 커브 붙일 끝점
		int x = dragon.get(idx).x + dir[d][1];
				
		while(idx > -1){
			d = dragon.get(idx).d;
					
			int nd = (d + 1) % 4;
			int ny = y + dir[nd][0];				// n세대 끝점
			int nx = x + dir[nd][1];
					
			if(ny<0 || ny>100 || nx<0 || nx>100)	break;
			
			map[ny][nx] = true;	// n세대 끝점 체크
			dragon.add(new data(y,x,nd));
			x=nx;	// 끝점 옮기기
			y=ny;
			idx--;
		}
	}
	
	private static int check() {
		int count = 0;
		for(int r=0; r<100; r++) {
			top:
			for(int c=0; c<100; c++) {
				if(map[r][c]) {
					for(int d=0; d<3; d++) {
						int nr = r + check[d][0];
						int nc = c + check[d][1];
						
						if(!map[nr][nc]) continue top;
					}
					count++;
				}
			}
		}
		return count;
	}
}
