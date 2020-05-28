import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_17143_낚시왕 {
	static int R;	// 격자판 크기
	static int C;
	static int M;	// 상어의 수
	static HashMap<Integer, shark> sharkmap;
	static int map[][];
	static int dir[][] = {{},{-1,0},{1,0},{0,1},{0,-1}};

	static class shark{
		int r;	// 위치
		int c;
		int s;	// 속도
		int d;	// 이동방향(1:위 2:아래 3:오른쪽 4:왼쪽)
		int z;	// 크기
		
		public shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[R+1][C+1];
		sharkmap = new HashMap<>();
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			sharkmap.put(i, new shark(r,c,s,d,z));
			map[r][c]=i;
		}
		int result=0;
		// 낚시왕 오른쪽으로 한 칸 이동
		for(int c=1; c<=C; c++) {
			int r=1;
			
			while(r<=R && map[r][c]==0)	r++;	// 낚시왕이 있는 열에서 제일 가까운 상어 찾음 
			
			if(r<=R) {
				result += sharkmap.get(map[r][c]).z;	// 잡은 상어의 크기 +
				sharkmap.remove(map[r][c]);				// 잡은 상어 없앰
				map[r][c]=0;
			}
			
			// 상어 이동 ( 같은 칸이면 크기가 큰 상어가 다 잡아먹음 )
			sharkmove();
		}
		
		System.out.println(result);	
	}
	private static void sharkmove() {
		Set<Integer> keyset = sharkmap.keySet();
		ArrayList<Integer> deletekey = new ArrayList<>();
		
		for(int key : keyset) {
			shark curshark = sharkmap.get(key);
			int r = curshark.r;
			int c = curshark.c;
			int s = curshark.s;
			int d = curshark.d;
			if(map[r][c] == key)	map[r][c]=0;	// 다른상어가 이동해와있지 않으면 비워줌
			
			switch (curshark.d) {
			case 1:	//상
			case 2:	//하
				int nr = r +(s * dir[d][0]);
				while((nr<1 || nr>R) && s>0) {
					if(nr>R) {
						s -= R-r+1;
						r = R-1;
						d--;
					}
					else {
						s -= r;
						r = 2;
						d++;
					}
					nr = r +(s * dir[d][0]);
				}
				r += s* dir[d][0];
				
				if(map[r][c]==0 || key < map[r][c]) {	// 이동하려는 자리에 이동해온 상어가 없는 경우
					map[r][c] = key;
				} else {
					shark nextshark = sharkmap.get(map[r][c]);
					if(curshark.z < nextshark.z) {	// 이동하려는 자리에 큰 상어
						deletekey.add(key);
					} else {	// 이동하려는 자리에 작은 상어
						deletekey.add(map[r][c]);
						map[r][c] = key;
					}
				}
				break;
			case 3:	//우
			case 4:	//좌
				int nc = c +(s * dir[d][1]);
				while((nc<1 || nc>C) && s>0) {
					if(nc>C) {
						s -= C-c+1;
						c = C-1;
						d++;
					}
					else {
						s -= c;
						c = 2;
						d--;
					}
					nc = c +(s * dir[d][1]);
				}
				c += s* dir[d][1];
				
				if(map[r][c]==0 || key < map[r][c]) {	// 이동하려는 자리에 이동해온 상어가 없는 경우
					map[r][c] = key;
				} else {
					shark nextshark = sharkmap.get(map[r][c]);
					if(curshark.z < nextshark.z) {	// 이동하려는 자리에 큰 상어
						deletekey.add(key);
					} else {	// 이동하려는 자리에 작은 상어
						deletekey.add(map[r][c]);
						map[r][c] = key;
					}
				}
				break;
			}
			sharkmap.replace(key, new shark(r,c,curshark.s,d,curshark.z));
		}
		
		for(int key : deletekey) {
			sharkmap.remove(key);
		}
	}
}
