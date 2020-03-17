import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_1981_배열에서이동 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int arr[][] = new int [n][n];
		int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		boolean[][] visit;
		
		// 중복 데이터 입력 허용x , 저장순서 오른차순
		TreeSet<Integer> tset = new TreeSet<>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j]= Integer.parseInt(st.nextToken());
				tset.add(arr[i][j]);
			}
		}
		// 최소값과 최댓값을 정한 후 그 값으로 (0,0)~(n-1, n-1)까지 갈 수 있는지 확인
		ArrayList<Integer> num = new ArrayList<>(tset);
		int left = 0; // 최소
		int right= 0; // 최대
		int result =200;
		
		while(left<num.size() && right<num.size()){

			ArrayDeque<int[]> queue = new ArrayDeque<>();
			visit = new boolean [n][n];
			int min = num.get(left);
			int max = num.get(right);
			
			// 처음 시작하는 칸의 수가 최소, 최대 경로에 부합하는지 확인
			if(arr[0][0]>=min && arr[0][0]<=max) {
				queue.offer(new int[] {0,0});
				visit[0][0]=true;
			}
			int cur[];
			int r;
			int c;
			while(!queue.isEmpty()) {
				cur = queue.poll();
				r = cur[0];
				c = cur[1];
				
				for(int i=0; i<4; i++) {
					int nr = r + dir[i][0];
					int nc = c + dir[i][1];
					
					if(nr<0 || nr>=n || nc<0 || nc>=n || visit[nr][nc]) 	continue;	// 경계 체크
					
					if(min>arr[nr][nc] || max <arr[nr][nc])		continue; // 다음 경로가 최소, 최대 범위에 안들어가면 패쓰
					queue.offer(new int[] {nr, nc});
					visit[nr][nc]=true;
				}
				
			}
			if(visit[n-1][n-1]) {	// 정한 최소 최대값으로 갈 수 있는 길이면
				result = Math.min(result, max-min);
				left++;
			}
			else {	// 갈 수 없는 길이면
				right++;
			}
			
		}
		
		System.out.println(result);
	}

}
