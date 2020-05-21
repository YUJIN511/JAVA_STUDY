import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_16234_인구이동 {
	
	static int N;
	static int L;
	static int R;
	static int index;
	static int [][] map;
	static int [][] line;
	static int [][] dir = {{-1,0}, {1, 0}, {0, -1}, {0, 1}};
	static ArrayDeque<int[]> mqueue;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	
		L = Integer.parseInt(st.nextToken());	// L <= 인구차이 <= R
		R = Integer.parseInt(st.nextToken());	
		
		map = new int[N][N];
		// 인구 배열 입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int result=0;
		//국경선 열기
		while(open()) {
			result++;
		}
		
		System.out.println(result);
	}

	private static boolean open() {	// 국경선 열기
		index=1;
		boolean flag =false;

		line = new int[N][N];
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		mqueue = new ArrayDeque<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(line[i][j]==0) {
					queue.offer(new int[] {i, j});
					
					int cur[];
					int r;
					int c;
					int sum=map[i][j];
					int size=1;
					while(!queue.isEmpty()) {
						
						cur = queue.poll();
						r=cur[0];
						c=cur[1];
						
						int nr;
						int nc;
						for(int d=0; d<4; d++) {
							nr = r+dir[d][0];
							nc = c+dir[d][1];
							
							// 경계 확인
							if(nr<0 || nr>=N || nc<0 || nc>=N || line[nr][nc]!=0)	continue;
							
							int n = Math.abs(map[r][c]-map[nr][nc]);
							
							if(L<= n && n<=R) {	// 국경선을 열 수 있는 인구차이인지 확인 
								if(line[r][c]==0) {
									line[r][c]=index++;
									mqueue.offer(new int[] {r, c});
								}
								
								queue.offer(new int[] {nr, nc});
								mqueue.offer(new int[] {nr, nc});	// 갱신할 국가들 위치
								line[nr][nc]=line[r][c];
								size++;
								sum+=map[nr][nc];
								flag=true;
							}
						}
					}
					move(sum, size);	// 인구 이동
				}
			}
		}
		
		return flag;
	}
	
	private static void move(int sum, int size) {
		if(size>1) {
			int num = sum/size;
			int cur[];
			
			while(!mqueue.isEmpty()) {
				cur = mqueue.poll();
				map[cur[0]][cur[1]]=num;	// 국가 값 갱신
			}
		}
	}
}
