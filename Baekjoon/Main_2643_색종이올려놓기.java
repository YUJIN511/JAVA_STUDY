import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_2643_색종이올려놓기 {
	static class pos{
		int y;
		int x;
		public pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		pos [] paper = new pos[N];
		int [] dp = new int [N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			paper[i] = new pos(Math.max(l, s),Math.min(l, s));
		}
		
		Arrays.sort(paper, new Comparator<pos>() {
			@Override
			public int compare(pos o1, pos o2) {
				if(o1.y == o2.y) {
					return Integer.compare(o2.x, o1.x);
				}
				return Integer.compare(o2.y, o1.y);
			}
		});
	
		int max = 0;
		for(int i=0; i<N; i++) {
			dp[i]=1;
			for(int j=i-1; j>=0; j--) {
				if((paper[j].y >=paper[i].y && paper[j].x >= paper[i].x)
						|| (paper[j].x >= paper[i].y && paper[j].y >= paper[i].x)) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			max = Math.max(dp[i], max);
		}
		
		System.out.println(max);
	}
}
