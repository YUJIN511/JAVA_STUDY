import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1260_DFS와BFS {

	static int N;
	static boolean arr[][];
	static boolean visit[];
		
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		arr = new boolean [N+1][N+1];
		visit = new boolean [N+1];
		
		int temp1;
		int temp2;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			temp1=Integer.parseInt(st.nextToken());
			temp2=Integer.parseInt(st.nextToken());
			arr[temp1][temp2]=true;
			arr[temp2][temp1]=true;
		}
		
		DFS(V);
		System.out.println();
		visit = new boolean [N+1];
		BFS(V);
	}

	private static void DFS(int v) {
		Stack<Integer> stack = new Stack<>();
		stack.push(v);
		int cur;
		while(!stack.isEmpty()) {
			cur = stack.pop();
			if(!visit[cur]) {
				visit[cur]=true;
				System.out.print(cur+" ");
				
				for(int i=N; i>0; i--) {
					if(arr[cur][i] && !visit[i]) {
						stack.push(i);
					}
				}
			}
			
		}
		
	}

	private static void BFS(int v) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		queue.offer(v);
		visit[v]=true;
		System.out.print(v+" ");
		int cur;
		while(!queue.isEmpty()) {
			cur=queue.poll();
			
			for(int i=1; i<=N; i++) {
				if(arr[cur][i] && !visit[i]) {
					queue.offer(i);
					System.out.print(i+" ");
					visit[i]=true;
				}
			}
			
		}
	}
}
