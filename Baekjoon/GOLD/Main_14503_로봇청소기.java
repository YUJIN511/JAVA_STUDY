import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 입력
 * N * M 크기의 맵 ( 각각의 칸은 벽(1) 또는 빈칸(0))
 * 
 * 실행
 * 1. 현재 위치 청소
 * 2. 왼쪽방향부터 탐색
 * 	2-1. 왼쪽방향 == 청소하지 않은 공간 -> 회전 후 전진 -> 1.
 * 	2-2. 왼쪽방향 == 청소한 공간 -> 회전 후 -> 2.
 * 	2-3. 네방향 == 청소한 공간 || 벽 -> 후진 -> 2.
 * 	2-4. 네방향 == (청소한 공간 || 벽 ) && 후진하지 못하면 -> 작동 멈춤 
 * 
 * 출력
 * 청소한 칸 개수
 */
public class Main_14503_로봇청소기 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());	// 로봇 청소기 좌표
		int c = Integer.parseInt(st.nextToken());	//
		int d = Integer.parseInt(st.nextToken());	// 로봇 청소기 방향 (0:북,  1:동,  2:남,  3:서)
		
		int map[][] = new int[N][M];
		for(int i=0; i<N; i++) {	// 장소 상태 (첫 열 && 마지막 열 =벽)
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result = 0; // 청소한 칸 개수
		int dir[][] = {{0,-1}, {-1,0}, {0,1}, {1,0}};	// 서, 북, 동, 남
		int flag=0;
		
		Top:
		while(true) {
			
			map[r][c] = -1;		// 1. 현재 위치 청소
			
			flag=0;
			while(true) {	// 2. 왼쪽부터 차례대로 탐색
				flag++;
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];
				if(map[nr][nc]==0) {	// 2-a. 왼쪽 방향 == 청소할 공간
					d = (d+3)%4; 		// 회전
					r = nr;				// 전진
					c = nc;
					break; // 1.번부터 다시 진행
				}
				else {	// 2-b. 왼쪽방향 != 청소할 공간 
					d = (d+3) % 4;	// 회전
					if(flag!=4)	continue;
				}

				// 2-c. 네 방향 == 청소한 공간 or 벽
				flag=0;
				r += dir[(d+7)%4][0]; 	// 후진
				c += dir[(d+7)%4][1];
				if(map[r][c]==1)	break Top;	// 후진 못하면 작동 멈춤
			}
		}

		for(int i=1; i<N-1; i++) {	// 청소한 공간 개수 
			for(int j=1; j<M-1; j++) {
				if(map[i][j] == -1)	result++;
			}
		}
		System.out.println(result);
	
	}
}
