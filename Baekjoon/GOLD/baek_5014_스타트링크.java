import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class baek_5014_스타트링크 {
	static int F;
	static int S;
	static int G;
	static int U;
	static int D;
	static boolean visit[];
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		F=Integer.parseInt(st.nextToken());
		S=Integer.parseInt(st.nextToken());
		G=Integer.parseInt(st.nextToken());
		U=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		visit = new boolean[F+1];
		bfs(S, 0);
	}

	private static void bfs(int pos, int count) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {pos, count});
		
		int cur[];
		while(!queue.isEmpty()) {
			cur=queue.poll();
			pos=cur[0];
			count=cur[1];
			
			if(pos == G) {	// 도착한 경우
				System.out.println(count);
				return;
			}
			if(U!=0 && pos+U <=F && !visit[pos+U]) {
				queue.offer(new int[] {pos+U, count+1});
				visit[pos+U]=true;
			}
			if(D!=0 && pos-D > 0 && !visit[pos-D]) {
				queue.offer(new int[] {pos-D, count+1});
				visit[pos-D]=true;
			}
		}
		System.out.println("use the stairs");
	}
}
