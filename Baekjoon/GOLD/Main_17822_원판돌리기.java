import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17822_원판돌리기 {
	static int N;
	static int M;
	static int circle[][];
	static int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());	// T번 회전
		
		circle = new int[N+1][M*2+1];
		
		// 원 입력
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				circle[i][j]=Integer.parseInt(st.nextToken());
			}
		}
			
		for(int t=0; t<T; t++) {	// 원판 T번 회전
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());	// 번호가 xi의 배수인 원판
			int d = Integer.parseInt(st.nextToken());	// 회전방향  0:시계방향, 1:반시계방향
			int k = Integer.parseInt(st.nextToken());	// ki칸 회전
			
			for(int i=1; x*i<=N; i++) {	// 회전시킬 원판 번호
				if(d==0) {	// 시계방향
					for(int j=M; j>0; j--) {
						circle[x*i][j+k]=circle[x*i][j];
					}
					for(int j=1; j<=k; j++) {
						circle[x*i][j]=circle[x*i][j+M];
					}
				}
				else {		// 반시계방향
					for(int j=1; j<=k; j++) {
						circle[x*i][j+M]=circle[x*i][j];
					}
					for(int j=1; j<=M; j++) {
						circle[x*i][j]=circle[x*i][j+k];
					}
				}
			}
			
			//인접한 수 지우기
			if(remove()) {
				update();
			}
		}
		int result=0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				result+=circle[i][j];
			}
		}
		
		System.out.println(result);
		
	}

	private static boolean remove() {
		int r;
		int c;
		boolean flag=true;
		boolean[][] rem = new boolean[N+1][M*2+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(circle[i][j]!=0) {
					for(int d=0; d<4; d++) {
						r = i + dir[d][0];
						c = (j + dir[d][1])%M;
						
						if(r<1 || r>N)	continue;
						if(c==0)	c=M;
						
						if(circle[i][j]==circle[r][c]) {
							rem[i][j]=true;
							rem[r][c]=true;
							flag = false;
						}
					}
				}
			}
		}
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(rem[i][j])	circle[i][j]=0;
			}
		}
		return flag;
	}
	

	private static void update() {
		int sum=0;
		int size=0;
		
		//평균 구하기
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				sum+=circle[i][j];
				if(circle[i][j]!=0)size++;
			}
		}
		double avg = (double) sum/size; 
			
		// 값 갱신
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(circle[i][j]!=0) {
					if(circle[i][j]>avg)		circle[i][j]--;
					else if(circle[i][j]<avg)	circle[i][j]++;
				}
			}
		}
	}
}
