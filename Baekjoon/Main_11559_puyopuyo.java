import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Stack;

public class Main_11559_puyopuyo {
	static char arr[][];
	static int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static ArrayDeque<int []> dq;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = new char[12][6];
		
		// 문자 입력
		for(int i=0; i<12; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		// 탐색
		int result =0;
		dq = new ArrayDeque<>();
		for(int i=0; i<12; i++) {
			for(int j=0; j<6; j++) {
				if(arr[i][j] != '.') {
					if(BFS(i, j)) {	// 뿌요가 터질 경우
						result++;
						remove();	// 뿌요 삭제
						move();		// 중력으로 아래로 이동
					}
				}
			}
		}
		
		System.out.println(result);
	}

	private static boolean BFS(int r, int c) {
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();	
		ArrayDeque<int[]> count = new ArrayDeque<>();
		boolean visit[][] = new boolean[12][6];
		
		for(int i=r; i<12; i++) {	// 동시에 터져야 하므로 
			for(int j=0; j<6; j++) {
				if(visit[i][j] || arr[i][j]=='.')	continue;
				
				count.clear();
				
				queue.offer(new int[] {i, j});
				count.offer(queue.peek());
				visit[i][j]=true;
				int cur[];

				while(!queue.isEmpty()) {
					cur = queue.poll();
					r = cur[0];
					c = cur[1];
					
					for(int d=0; d<4; d++) {
						int nr = r + dir[d][0];
						int nc = c + dir[d][1];
						
						// 경계체크 & 같은 색인지 확인
						if(nr<0 || nr>=12 || nc<0 || nc>=6 || arr[nr][nc]!=arr[r][c] || visit[nr][nc])	continue;
						
						queue.offer(new int[] {nr, nc});
						count.offer(queue.peekLast());
						visit[nr][nc]= true;
					}
					
				}
				if(count.size()>=4) {	// 같은 색 뿌요가 4개 이상일 때
					dq.addAll(count);	// 삭제할 뿌요 옮겨주기
				}
			}
		}
		
		if(dq.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	

	private static void remove() {
		
		int cur[];
		while(!dq.isEmpty()) {
			cur = dq.poll();
			arr[cur[0]][cur[1]] = '.';
		}
		
	}

	private static void move() {
		ArrayDeque<Character> q = new ArrayDeque<>();
		for(int j=0; j<6; j++) {
			for(int i=11; i>=0; i--) {	// r은 뿌요가 있는 최소 행 
				if(arr[i][j] != '.') {
					q.offer(arr[i][j]);
					arr[i][j]='.';
				}
			}
			int index=11;
			while(!q.isEmpty()) {		// 아래로 이동
				arr[index--][j] = q.poll();
			}
		}
		
	}
}
