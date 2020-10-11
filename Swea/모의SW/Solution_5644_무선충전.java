import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_5644_무선충전 {
	
	static int map[][];
	static int dir[][] = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());	// 총 이동시간
			int A = Integer.parseInt(st.nextToken());	// BC 개수

			int userA[] = new int[M+1];
			int userB[]	= new int[M+1];
			// 사용자1 이동정보
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				userA[i] = Integer.parseInt(st.nextToken());
			}
			// 사용자2 이동정보
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				userB[i] = Integer.parseInt(st.nextToken());
			}
			
			int BC[][] = new int[A+1][4];
			map = new int[11][11];
			for(int i=1; i<=A; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<4; j++) {
					BC[i][j] = Integer.parseInt(st.nextToken());
				}
				// BC 영역 체크
				BFS(BC[i][0], BC[i][1], BC[i][2], i);
			}

			int result = 0;
			int AY = 1;
			int AX = 1;
			int BY = 10;
			int BX = 10;
			// 충전 양 구하기
			int idx = 0;

			while(idx <= M) {
				if(map[AY][AX] == 9 && map[BY][BX] == 9) {	// 둘다 겹쳤을 때
					int [] AA = new int[9];
					int [] BB = new int[9];
					int max = 0;
					for(int i=1; i<=A; i++) {
						int y = BC[i][1];
						int x = BC[i][0];
						if(BC[i][2] >= Math.abs(AY-y)+Math.abs(AX-x)) {
							AA[i] = BC[i][3];
						}
						if(BC[i][2] >= Math.abs(BY-y)+Math.abs(BX-x)) {
							BB[i] = BC[i][3];
						}
					}
					for(int i=1; i<=A; i++) {
						for(int j=1; j<=A; j++) {
							if(i==j)	continue;
							max = Math.max(AA[i]+BB[j], max);
						}
					}
					result+=max;
				} else if(map[AY][AX] == 9 || map[BY][BX] == 9) {	// 한쪽만 겹쳤을 때
					
					if(map[AY][AX] == 9) {
						result += BC[map[BY][BX]][3];
						
						int max = 0;
						for(int i=1; i<=A; i++) {
							int y = BC[i][1];
							int x = BC[i][0];
							if(i != map[BY][BX] && BC[i][2] >= Math.abs(AY-y)+Math.abs(AX-x)) {
								max = Math.max(max, BC[i][3]);
							}
						}
						result += max;
					} else {
						result+=BC[map[AY][AX]][3];
						
						int max = 0;
						for(int i=1; i<=A; i++) {
							int y = BC[i][1];
							int x = BC[i][0];
							if(i != map[AY][AX] && BC[i][2] >= Math.abs(BY-y)+Math.abs(BX-x)) {
								max = Math.max(max, BC[i][3]);
							}
						}
						result += max;
					}
				} else {
					if(map[AY][AX]==map[BY][BX]) {	// 같은 BC안에 있는 경우
						result+=BC[map[AY][AX]][3];
					} else {
						result+=BC[map[AY][AX]][3];
						result+=BC[map[BY][BX]][3];						
					}
				}
				
				AY = AY + dir[userA[idx]][0];
				AX = AX + dir[userA[idx]][1];
				BY = BY + dir[userB[idx]][0];
				BX = BX + dir[userB[idx]][1];
				idx++;
			}
			
			System.out.println("#"+testCase+" "+result);
		}
	}

	private static void BFS(int x, int y, int c, int idx) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		if(map[y][x] == 0) {
			map[y][x] = idx;
		} else {
			map[y][x] = 9;
		}
		q.offer(new int[] {y,x,0});
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			y = cur[0];
			x = cur[1];
			int cnt = cur[2];
			
			if(cnt == c)	continue;
			
			for(int d=1; d<=4; d++) {
				int ny = y + dir[d][0];
				int nx = x + dir[d][1];
				
				if(ny>=1 && ny<=10 && nx>=1 && nx<=10 && map[ny][nx]!=idx) {
					if(map[ny][nx]==0) {
						map[ny][nx]=idx;
					} else {
						map[ny][nx]=9;
					}
					q.offer(new int[] {ny, nx, cnt+1});
				}
			}
		}
	}

}
