import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution_1247 {

	static int N;
	static int home[];
	static int customer[][];
	static boolean visited[];
	static int min;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=TC; testCase++) {
			N = Integer.parseInt(br.readLine());
			int office[] = new int[2];
			home = new int[2];
			customer = new int[N][2];
			visited = new boolean[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			office[0]=Integer.parseInt(st.nextToken());
			office[1]=Integer.parseInt(st.nextToken());
			home[0]=Integer.parseInt(st.nextToken());
			home[1]=Integer.parseInt(st.nextToken());

			for(int i=0; i<N; i++) {
				customer[i][0] = Integer.parseInt(st.nextToken());
				customer[i][1] = Integer.parseInt(st.nextToken());
			}
			
			min=Integer.MAX_VALUE;
			per(0, office[0], office[1], 0);
			
			System.out.println("#"+testCase+" "+min);
		}

	}

	private static void per(int cnt, int x, int y, int d) {
		if(d>min)	return;
		
		if(cnt == N) {
			d += Math.abs(x-home[0])+ Math.abs(y-home[1]);
			min = Math.min(min, d);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i]=true;
				int nx = customer[i][0];
				int ny = customer[i][1];
				per(cnt+1, nx, ny, d+Math.abs(x-nx)+ Math.abs(y-ny));
				visited[i]=false;
			}
		}
	}

}
