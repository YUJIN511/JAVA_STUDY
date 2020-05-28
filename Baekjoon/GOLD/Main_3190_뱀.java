import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_3190_뱀 {
	
	static int N ;
	static int time;
	static int map[][];		// 공백 상 우 하 좌
	static int dir[][] = {{}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static ArrayDeque<int []> queue;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
	
		int x;
		int y;
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			y= Integer.parseInt(st.nextToken());
			x= Integer.parseInt(st.nextToken());
			map[y][x] = -1;	// 사과가 있으면 -1;
		}
		
		int L = Integer.parseInt(br.readLine());
		queue = new ArrayDeque<>();

		int t;
		int d; // 0 = 왼쪽(L) ,  1 = 오른쪽(D)
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			t=Integer.parseInt(st.nextToken());
			if(st.nextToken().equals("D")) {
				d=1;
			}
			else {
				d=0;
			}
			queue.offer(new int[] {t, d});
		}
		
		game();
		
		System.out.println(time);
	}

	private static void game() {
		
		time=1;
		int d = 2; 			//처음에는 오른쪽
		int hr=1;	//뱀 머리
		int hc=1;
		int tr=1;	//뱀 꼬리
		int tc=1;
		map[1][1]=d;	//뱀 체크
		
		int cur[]=queue.poll();
		int stime=cur[0];
		int sdir=cur[1];
		
		while(true) {
			
			// 뱀의 다음 위치
			hr=hr+dir[d][0];
			hc=hc+dir[d][1];
			
			// 경계 밖이거나 자기 몸에 부딪히면 게임 끝
			if(hr<1 || hr>N || hc<1 || hc>N || map[hr][hc]>0) return;	
			
			
			if(map[hr][hc]!=-1) {			// 사과 못먹은 경우
				int r = tr;
				int c = tc;
				
				tr+=dir[map[r][c]][0];
				tc+=dir[map[r][c]][1];

				map[r][c]=0;	// 꼬리 이동
			}
			
			if(stime==time) {	// 시간 되면 방향 이동
				
				if(sdir==0) {	// 왼쪽
					if(d==1)	d=4;
					else		d-=1;
				}
				else {	// 오른쪽
					if(d==4)	d=1;
					else		d+=1;
				}
				if(!queue.isEmpty()) {
					cur=queue.poll();
					
					stime=cur[0];
					sdir=cur[1];
				}
			}

			map[hr][hc]=d;	// 머리 이동
			time++;
		}
		
	}
}
