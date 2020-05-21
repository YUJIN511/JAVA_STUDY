import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15683_감시 {
	
	static int N;
	static int M;
	static int min;
	static int count;
	static int [][] cctv;
	static int [][] com;
	static int [][] temp;
	static int [] num = {0, 4, 2, 4, 4, 1};	// 방향 개수
	static int [] selected;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		com = new int[N][M];	
		cctv = new int[8][3];
		count =0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				com[i][j] = Integer.parseInt(st.nextToken());
				if(com[i][j]>0 && com[i][j]<6) {
					cctv[count][0] = i;
					cctv[count][1] = j;
					cctv[count][2] = com[i][j];
					count++;	// CCTV 개수
				}
			}
		}
		
		min = Integer.MAX_VALUE;
		selected = new int[count];
		cctv(0);
		
		System.out.println(min);
	}

	private static void cctv(int start) {
		if(start == count) {	// 사각지대 구하기
			
			temp = new int[N][M];
			for(int i=0; i<N; i++) {	// 배열 복사
				for(int j=0; j<M; j++) {
					temp[i][j] = com[i][j];
				}
			}
			
			for(int k=0; k<count; k++) {
				int r = cctv[k][0];
				int c = cctv[k][1];
				
				switch(cctv[k][2]) {
					case 1:	
						check(selected[k], r, c);
						break;
					case 2:
						switch (selected[k]) {
							case 1: // 좌우
								check(1,r,c);
								check(3,r,c);
								break;
							case 2:	// 상하
								check(2,r,c);
								check(4,r,c);
								break;
						}
						break;
					case 3:
						switch (selected[k]) {
							case 1: // 상우
								check(4,r,c);
								check(1,r,c);
								break;
							case 2:	// 우하
								check(1,r,c);
								check(2,r,c);
								break;
							case 3:	// 좌하
								check(2,r,c);
								check(3,r,c);
								break;
							case 4:	// 상좌
								check(3,r,c);
								check(4,r,c);
								break;
						}
						break;
					case 4:
						switch (selected[k]) {
						case 1: // 좌상우
							check(3,r,c);
							check(4,r,c);
							check(1,r,c);
							break;
						case 2:	// 상우하
							check(4,r,c);
							check(1,r,c);
							check(2,r,c);
							break;
						case 3:	// 우하좌
							check(1,r,c);
							check(2,r,c);
							check(3,r,c);
							break;
						case 4:	// 상좌하
							check(2,r,c);
							check(3,r,c);
							check(4,r,c);
							break;
					}
						break;
					case 5:
						check(1,r,c);
						check(2,r,c);
						check(3,r,c);
						check(4,r,c);
						break;
					
				}
			}
			int result=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(temp[i][j]==0)	result++;
				}
			}
			min = Math.min(result, min);
			return;
		}
		
		for(int i=start; i<count; i++) {
			for(int j=1; j<=num[cctv[i][2]]; j++) {
				selected[i] = j;
				cctv(i+1);
			}
		}
	}

	private static void check(int n, int r, int c) {
		switch (n) {
			case 1: // 오른쪽
				c++;
				while(c<M) {	
					if(temp[r][c]==6)	break;
					else if(temp[r][c]!=0)	c++;
					else	temp[r][c++]=-1;
				}
				break;
			case 2:	// 아래
				r++;
				while(r<N) {	
					if(temp[r][c]==6)	break;
					else if(temp[r][c]!=0)	r++;
					else	temp[r++][c]=-1;
				}
				break;
			case 3: // 왼쪽
				c--;
				while(c>-1) {	
					if(temp[r][c]==6)	break;
					else if(temp[r][c]!=0)	c--;
					else	temp[r][c--]=-1;
				}
				break;
			case 4:	// 위
				r--;
				while(r>-1) {	
					if(temp[r][c]==6)	break;
					else if(temp[r][c]!=0)	r--;
					else	temp[r--][c]=-1;
				}
				break;
		}
	}
}
