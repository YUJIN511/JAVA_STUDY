import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_13459_구슬탈출 {
	static int N;
	static int M;
	static char[][] board;
	static char[][] temp;
	static int[] selected;
	static marble red;
	static marble t_red;
	static marble blue;
	static marble t_blue;
	
	static class marble{
		int r;
		int c;
		
		public marble(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		temp = new char[N][M];
		
		for(int i=0; i<N; i++) {	// 배열 입력
			String str = br.readLine();
			board[i] = str.toCharArray();
			
			for(int j=0; j<M; j++) {
				if(board[i][j] == 'R')		red = new marble(i, j);
				else if(board[i][j] == 'B')	blue = new marble(i, j);
			}
		}
		
		selected = new int[10];
		for(int i=1; i<=10; i++) {
			if(per(0, i)) {
				System.out.println(1);	// 구술을 빼낼 수 있을 경우
				return;
			};
		}
		System.out.println(0);	// 구슬을 빼낼 수 없을 경우
	}

	private static boolean per(int cnt, int R) {	// true: 성공		false: 실패
		if(cnt == R) {
			for(int i=0; i<N; i++) {
				System.arraycopy(board[i], 0, temp[i], 0, board[i].length);
			}

			t_red = new marble(red.r, red.c);
			t_blue = new marble(blue.r, blue.c); 
			
			for(int j=0; j<R; j++) {
				int flag = move(selected[j]);
				
				if(flag == 1) 		return true;
				else if(flag == 2)	return false;
			}
			return false;
		}
		for(int i=1; i<5; i++) {
			selected[cnt]=i;
			if(per(cnt+1, R))	return true;
		}
		
		return false;
	}

	private static int move(int d) { // 1: 성공	2:실패,움직임 그만	3: 계속
		Queue<Character> queue = new ArrayDeque<>();

		int flag = 2;
		boolean pre = false;
		int rr, rc;
		int br, bc;
		
		switch (d) {
			case 1:	// 상
					rr = t_red.r - 1;
					br = t_blue.r - 1;
					
					// blue 구슬 이동
					while(br>0 && temp[br][t_blue.c] !='#') {
						if(temp[br][t_blue.c] == 'O')			return 2;
						else if(temp [br][t_blue.c] == 'R') 	pre = true;
						br--;
					}
					
					if(pre && br+2 != t_blue.r) {	// 이동중에 빨간구슬이 있는 경우
						temp[t_blue.r][t_blue.c] = '.';
						temp[t_red.r][t_red.c] = '.';

						t_blue.r = br+2;
						t_red.r = br+1;
						temp[t_blue.r][t_blue.c] = 'B';
						temp[t_red.r][t_red.c] = 'R';
						return 3;
					}
					else if(!pre && br+1!= t_blue.r){	// 움직임 있는지 체크
						temp[t_blue.r][t_blue.c] = '.';
						
						t_blue.r = br+1;
						temp[t_blue.r][t_blue.c] = 'B';
						flag = 3;
					}
					
					// red 구슬 이동
					while(rr>0 && temp[rr][t_red.c] !='#' && temp[rr][t_red.c] != 'B') {
						if(temp[rr][t_red.c] == 'O')			return 1;
						rr--;
					}
					if(rr+1 != t_red.r) {	// 움직임 있는지 체크
						temp[t_red.r][t_red.c] = '.';
						t_red.r = rr+1;
						temp[t_red.r][t_red.c] = 'R';
						flag = 3;
					}
			break;
			case 2:	// 하
				rr = t_red.r + 1;
				br = t_blue.r + 1;
				
				// blue 구슬 이동
				while(br<N-1 && temp[br][t_blue.c] !='#') {
					if(temp[br][t_blue.c] == 'O')			return 2;
					else if(temp [br][t_blue.c] == 'R') 	pre = true;
					br++;
				}
				
				if(pre && br-2 != t_blue.r) {	// 이동중에 빨간구슬이 있는 경우
					temp[t_blue.r][t_blue.c] = '.';
					temp[t_red.r][t_red.c] = '.';
					
					t_blue.r = br-2;
					t_red.r = br-1;
					temp[t_blue.r][t_blue.c] = 'B';
					temp[t_red.r][t_red.c] = 'R';
					return 3;
				}
				else if(!pre && br-1 != t_blue.r){	// 움직임 있는지 체크
					temp[t_blue.r][t_blue.c] = '.';
					t_blue.r = br-1;
					temp[t_blue.r][t_blue.c] = 'B';
					flag = 3;
				}
				
				// red 구슬 이동
				while(rr<N-1 && temp[rr][t_red.c] !='#' && temp[rr][t_red.c] != 'B') {
					if(temp[rr][t_red.c] == 'O')			return 1;
					rr++;
				}
				if(rr-1 != t_red.r) {	// 움직임 있는지 체크
					temp[t_red.r][t_red.c] = '.';

					t_red.r = rr-1;
					temp[t_red.r][t_red.c] = 'R';
					flag = 3;
				}
				break;
			case 3:	// 좌
				rc = t_red.c - 1;
				bc = t_blue.c - 1;
				
				// blue 구슬 이동
				while(bc>0 && temp[t_blue.r][bc] !='#') {
					if(temp[t_blue.r][bc] == 'O')			return 2;
					else if(temp [t_blue.r][bc] == 'R') 	pre = true;
					bc--;
				}
				
				if(pre && bc+2 != t_blue.c) {	// 이동중에 빨간구슬이 있는 경우
					temp[t_blue.r][t_blue.c] = '.';
					temp[t_red.r][t_red.c] = '.';
					
					t_blue.c = bc+2;
					t_red.c = bc+1;
					temp[t_blue.r][t_blue.c] = 'B';
					temp[t_red.r][t_red.c] = 'R';
					return 3;
				}
				else if(!pre && bc+1 != t_blue.c){	// 움직임 있는지 체크
					temp[t_blue.r][t_blue.c] = '.';
					t_blue.c = bc+1;
					temp[t_blue.r][t_blue.c] = 'B';
					flag = 3;
				}
				
				// red 구슬 이동
				while(rc>0 && temp[t_red.r][rc] !='#' && temp[t_red.r][rc] != 'B') {
					if(temp[t_red.r][rc] == 'O')			return 1;
					rc--;
				}
				if(rc+1 != t_red.c) {	// 움직임 있는지 체크
					temp[t_red.r][t_red.c] = '.';

					t_red.c = rc+1;
					temp[t_red.r][t_red.c] = 'R';
					flag = 3;
				}
				break;
			case 4:	// 우
				rc = t_red.c + 1;
				bc = t_blue.c + 1;
				
				// blue 구슬 이동
				while(bc < M-1 && temp[t_blue.r][bc] !='#') {
					if(temp[t_blue.r][bc] == 'O')			return 2;
					else if(temp [t_blue.r][bc] == 'R') 	pre = true;
					bc++;
				}
				
				if(pre && bc-2 != t_blue.c) {	// 이동중에 빨간구슬이 있는 경우
					temp[t_blue.r][t_blue.c] = '.';
					temp[t_red.r][t_red.c] = '.';
					t_blue.c = bc-2;
					t_red.c = bc-1;
					temp[t_blue.r][t_blue.c] = 'B';
					temp[t_red.r][t_red.c] = 'R';
					return 3;
				}
				else if(!pre && bc-1 != t_blue.c){	// 움직임 있는지 체크
					temp[t_blue.r][t_blue.c] = '.';
					t_blue.c = bc-1;
					temp[t_blue.r][t_blue.c] = 'B';
					flag = 3;
				}
				
				// red 구슬 이동
				while(rc < M-1 && temp[t_red.r][rc] !='#' && temp[t_red.r][rc] != 'B') {
					if(temp[t_red.r][rc] == 'O')			return 1;
					rc++;
				}
				if(rc-1 != t_red.c) {	// 움직임 있는지 체크
					temp[t_red.r][t_red.c] = '.';

					t_red.c = rc-1;
					temp[t_red.r][t_red.c] = 'R';
					flag = 3;
				}
				break;
		}
		
		return flag;
	}
}
