
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_2146_다리만들기 {
	static int N;
	static int start;
	static int count;
	static int min;
	static int arr[][];
	static boolean visit[][];
	static int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int [N][N];
		
		////배열 입력 : 0은 바다 1은 육지
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		count=0;
		visit = new boolean [N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 육지이고 방문안했을 경우 
				if(arr[i][j]!=0 && !visit[i][j])	BFS(i, j);	// 섬 체크
			}
		}
		
		min=Integer.MAX_VALUE;
		
		for(start=1; start<=count; start++) {
			find(start);
		}
		
		System.out.println(min);
	}

	private static void BFS(int r, int c) {
		
		ArrayDeque<int []> queue = new ArrayDeque<>();
		count++;
		
		queue.offer(new int[] {r, c});
		visit[r][c]=true;
		arr[r][c]=count;
		
		while(!queue.isEmpty()) {
			int cur [] = queue.poll();
			
			r = cur[0];
			c = cur[1];
			
			for(int i=0; i<4; i++) {
				int nr = r+dir[i][0];
				int nc = c+dir[i][1];
				
				if(nr>-1 && nr<N && nc>-1 && nc<N && !visit[nr][nc] && arr[nr][nc]!=0) {
					queue.offer(new int[] {nr, nc});
					visit[nr][nc] =true;
					arr[nr][nc]=count;
				}
				
			}
						
		}
	}
	
	private static void find(int start) {
		
		ArrayDeque<int []> queue = new ArrayDeque<>();
		visit = new boolean [N][N];
		
		for(int i=0; i<N; i++) { // 시작할 섬만 큐에 넣기
			for(int j=0; j<N; j++) {
				if(arr[i][j]==start) {
					visit[i][j]=true;
					queue.offer(new int[] {i, j, 0});
				}
			}
		}
		
		int r, c, d;
		
		while(!queue.isEmpty()) {
			int size=queue.size();
			for(int j=0; j<size; j++) {
				int cur[] = queue.poll();
				r=cur[0];
				c=cur[1];
				d=cur[2];
				
				for(int i=0; i<4; i++) {
					int nr = r+dir[i][0];
					int nc = c+dir[i][1];
						
					if(nr>-1 && nr<N && nc>-1 && nc<N) {
						if(arr[nr][nc]!=start && arr[nr][nc]!=0) {	// 시작한 섬이 아닌 섬에 만나면 거리 체크
							min=Math.min(min, d);
						}
						else if(arr[nr][nc]==0) {	// 바다이면 방문
							if(!visit[nr][nc]) {
								queue.offer(new int [] {nr, nc, d+1});
								visit[nr][nc]=true;
							}
							
						}
					}
				}
			}
		}

	}
}











