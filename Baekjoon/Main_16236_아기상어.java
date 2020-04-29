import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16236_아기상어 {
	static int N;
	static int map[][];			// 상 좌 우 하
	static boolean visit[][];
	static int dir[][] = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
	static int size;
	static int fish;
	static int time;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N]; 
		visit = new boolean[N][N]; 
		
		int sx=0;	// 아기상어 위치
		int sy=0;
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					sy=i;
					sx=j;
				}
			}
		}
		
		size=2;	// 아기 상어 크기
		fish=0; // 먹은 물고기 갯수
		time =0;
		move(sy, sx);
		
		System.out.println(time);
	}
	private static void move(int sy, int sx) {
		if(map[sy][sx]>size)	return;	// 큰 물고기
		time++;
		if(map[sy][sx]!=0 && map[sy][sx]<size) {	// 작은 물고기
			map[sy][sx]=0;
			fish++;
			if(fish==size) {	// 아기 상어 레벨업
				fish=0;
				size++;
			}
		}
		int r;
		int c;
		for(int i=0; i<4; i++) {
			r=sy+dir[i][0];
			c=sx+dir[i][1];
			
			if(r>-1 && r<N && c>-1 && c<N && !visit[r][c]) {
				visit[r][c]=true;
				move(r, c);
				visit[r][c]=false;
			}
		}
	}
}
