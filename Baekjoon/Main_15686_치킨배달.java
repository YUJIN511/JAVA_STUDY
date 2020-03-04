import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15686_치킨배달 {
	
	static int N;
	static int M;
	static int size;
	static int result;
	static boolean selected[];
	static int numbers[];
	static int city[][];
	static int chicken[][];
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		city = new int [N][N];
		chicken = new int [13][2];
		size=0;
		result=Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				city[i][j]=Integer.parseInt(st.nextToken());
				if(city[i][j]==2) {
					chicken[size][0]=i;
					chicken[size++][1]=j;
				}
			}
		}

		numbers = new int [M];
		selected = new boolean [size];
		com(0,0);
		
		System.out.println(result);
	}

	private static void com(int cnt, int start) {
		if(cnt == M) {	// 치킨집을 최대 M개 고름
			distance();	// 도시 치킨 거리 최솟값
			return;
		}
		
		for(int i=start; i<size; i++) {
			if(!selected[i]) {
				selected[i]=true;
				numbers[cnt]=i;
				com(cnt+1, i+1);
				selected[i]=false;
			}
		}
		
	}

	private static void distance() {
		int d;
		int min=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(city[i][j]==1) {	// 집일 경우
					d=Integer.MAX_VALUE;
					for(int k=0; k<M; k++) {	// 조합으로 고른 치킨집 중 가까운 치킨집
						int index = numbers[k];
						int sum=Math.abs(i-chicken[index][0])+Math.abs(j-chicken[index][1]);
						d=Math.min(d, sum);
					}
					min+=d;
				}
			}
		}
		result=Math.min(min, result);	// 도시의 치킨집 거리 최솟값
	}
}
