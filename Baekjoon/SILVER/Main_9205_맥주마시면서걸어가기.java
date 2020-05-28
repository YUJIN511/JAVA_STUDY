import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
/*
 * <입력>
 * n : 편의점 개수 (0 <= n <= 100)
 * -32768<= x, y <= 32768
 * 
 * 1. 집에서 페스티벌까지 까지 맥주 한박스로 가능하면 끝
 * 1-2. 불가능하면 집에서 처음에 갈 수 있는 편의점 offer
 * 2. 편의점에서 페스티벌갈 수 있으면 끝
 * 2-2. 불가능하면 다음 편의점 offer
 * 
 */
public class Main_9205_맥주마시면서걸어가기 {
	static ArrayDeque<int[]> queue;
	static boolean visited[];
	static int pos[][];
	static int x;
	static int y;
	static int n;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		int t = Integer.parseInt(br.readLine());

		for(int test_case=1; test_case<=t; test_case++) {

			queue = new ArrayDeque<>();
			visited = new boolean[101];
			pos = new int[101][101];	
			
			n = Integer.parseInt(br.readLine());		// 편의점 개수
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			y= Integer.parseInt(st.nextToken());	// 상근이네 집 좌표
			x = Integer.parseInt(st.nextToken());
			
			int r;
			int c;
			for(int i=0; i<n; i++) {	// 편의점 입력
				st = new StringTokenizer(br.readLine());
				r=Integer.parseInt(st.nextToken());
				c=Integer.parseInt(st.nextToken());
				
				pos[i][0]=r;
				pos[i][1]=c;

				// 갈 수 있는 편의점
				if(Math.abs(y-r)+Math.abs(x-c)<=1000) {	
					queue.offer(new int[] {r,c});
					visited[i]=true;
				}
			}
			
			st = new StringTokenizer(br.readLine());	// 페스티벌 좌표
			r=Integer.parseInt(st.nextToken());
			c=Integer.parseInt(st.nextToken());
			
			// 페스티벌 바로 갈 수 있으면 끝
			if(Math.abs(y-r)+Math.abs(x-c)<=1000) {
				System.out.println("happy");
				continue;
			}
			pos[n][0]=r;
			pos[n][1]=c;
			
			if(BFS()) {	
				System.out.println("happy");
			}
			else {
				System.out.println("sad");
			}
		}
	}

	private static boolean BFS() {
		
		while(!queue.isEmpty()) {
			
			int cur[] = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			if(Math.abs(r-pos[n][0])+Math.abs(c-pos[n][1])<=1000) {	// 페스티벌 갈 수 있는지
				return true;
			}
			else {
				for(int i=0; i<n; i++) {
					if(!visited[i]) {
						int nr = pos[i][0];
						int nc = pos[i][1];
						if(Math.abs(r-nr)+Math.abs(c-nc)<=1000) {	// 다음 갈 수 있는 편의점
							queue.offer(new int[] {nr, nc});
							visited[i]=true;
						}
					}
				}
			}
		}
		
		return false;
		
	}
}
