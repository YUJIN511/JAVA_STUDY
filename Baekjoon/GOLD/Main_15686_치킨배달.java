import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15686_치킨배달 {
	
	static int N;
	static int M;
	static int result;
	static int numbers[];
	static List<int[]> chicken;
	static List<int[]> home;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		chicken = new ArrayList<int[]>();
		home = new ArrayList<int[]>();
		result=Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int input = Integer.parseInt(st.nextToken());
				if(input==2) {
					chicken.add(new int[] {i,j});
				}
				else if(input==1) {
					home.add(new int[] {i,j});
				}
			}
		}

		numbers = new int [M];
		com(0,0);
		
		System.out.println(result);
	}

	private static void com(int cnt, int start) {
		if(cnt == M) {	// 치킨집을 최대 M개 고름
			int d;
			int min=0; // 도시 치킨 거리 최솟값
			for(int cur[]: home) {
				d=Integer.MAX_VALUE;
				for(int k=0; k<M; k++) {	// 조합으로 고른 치킨집 중 가까운 치킨집
					int index = numbers[k];
					int sum=Math.abs(cur[0]-chicken.get(index)[0])+Math.abs(cur[1]-chicken.get(index)[1]);
					d=Math.min(d, sum);
				}
				min+=d;
			}
			result=Math.min(min, result);	// 도시의 치킨집 거리 최솟값
			return;
		}
		
		for(int i=start; i<chicken.size(); i++) {
			numbers[cnt]=i;
			com(cnt+1, i+1);
		}
		
	}
}
