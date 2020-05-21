import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_16236_아기상어 {
	static int N;
	static int map[][];			// 상 좌 우 하
	static boolean visit[][];
	static int dir[][] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	static int size;
	static int fish;
	static int time;
	static int sf;
	static shark s;
	
	static class shark implements Comparable<shark>{
		int y;
		int x;
		int t;
		
		public shark(int y, int x, int t) {
			super();
			this.y = y;
			this.x = x;
			this.t = t;
		}

		@Override
		public int compareTo(shark o) {
			if(this.t < o.t) {	// t 값 오름차순으로 정렬
				return -1;
			}
			else if(this.t == o.t){
				if(this.y < o.y) {	// y 값 오름차순 정렬
					return -1;
				}
				else if(this.y == o.y && this.x < o.x) {
					return -1;
				}
				return 1;
			}
			return 1;
		}
				
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N]; 
		visit = new boolean[N][N]; 
		
		int sx=0;	// 아기상어 위치
		int sy=0;
		size=2;	// 아기 상어 크기
		fish=-1; // 먹은 물고기 갯수
		
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					sy=i;
					sx=j;
				}
				if(map[i][j]>0) fish++;
			}
		}
		time =0;
		sf=0;
		map[sy][sx]=0;
		move(sy, sx, 1);
		
		System.out.println(time);
	}
	
	private static void move(int sy, int sx, int st) {
		PriorityQueue<shark> queue = new PriorityQueue<>();
		PriorityQueue<shark> eat = new PriorityQueue<>();
		int r;
		int c;
		boolean flag = false;
		shark cur;
		queue.offer(new shark(sy,sx, 1));
		
		while(!queue.isEmpty()) {
			cur=queue.poll();
			sy=cur.y;
			sx=cur.x;
			st=cur.t;
			visit[sy][sx]=true;
			
			for(int i=0; i<4; i++) {// 상어가 이동할 수 있는 좌표
				r=sy+dir[i][0];
				c=sx+dir[i][1];
				
				if(r>-1 && r<N && c>-1 && c<N && map[r][c]<=size && !visit[r][c] ) {
					if(map[r][c]!=0 && map[r][c]<size) {	// 자기보다 작은 물고기가 있는 경우
						eat.offer(new shark (r, c, st));
						
						while(!queue.isEmpty()) { // 같은 거리에서 물고기를 먹을 수 있는지 확인
							cur=queue.poll();
							sy=cur.y;
							sx=cur.x;
							st=cur.t;
							visit[sy][sx]=true;
							for(int j=0; j<4; j++) {
								r=sy+dir[j][0];
								c=sx+dir[j][1];
								
								if(r>-1 && r<N && c>-1 && c<N && map[r][c]<=size && !visit[r][c] ) {
									if(map[r][c]!=0 && map[r][c]<size) {
										eat.offer(new shark(r,c,st));	// 먹을 수 있는 물고기
									}
								}
							}
							
						}
						visit = new boolean[N][N]; 
						cur=eat.poll();	// 가장 가까운 물고기 좌표
						time+=cur.t;
						queue.offer(new shark(cur.y, cur.x, 1));	// 새 좌표 지정
						
						fish--;
						map[cur.y][cur.x]=0;
						sf++;
						if(sf==size) {	// 상어 레벨업
							sf=0;
							size++;
						}

						eat.clear();
						break;
					}
					queue.offer(new shark (r, c, st+1));
					visit[r][c]=true;
				}
			}
			if(fish == 0)	return;	// 물고기를 다 먹었으면 끝
		}
		
	}
}