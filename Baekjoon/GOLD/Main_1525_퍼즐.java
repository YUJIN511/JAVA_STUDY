import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_1525_퍼즐 {
	
	static int origin[][] = {{0, 1, 2},{3, 4, 5},{6, 7, 8}};
	static int dir[][] = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String puzzle = "";
		
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n==0) {
					n=9;
				} 
				puzzle += n;
			}
		}
		
		System.out.println(BFS(Integer.parseInt(puzzle),0));
		
	}
	private static int BFS(int puz, int time) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		Set<Integer> set = new HashSet<>();
		queue.offer(new int[] {puz, time});
		set.add(puz);
		
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			puz = cur[0];
			time = cur[1];
			
			int idx = (puz+"").indexOf("9", 0);
			int r = idx/3;
			int c = idx%3;
			
			if(puz == 123456789) {
				return time;
			}
			
			for(int i=0; i<4; i++) {
				int nr = r + dir[i][0]; 
				int nc = c + dir[i][1];
				
				if(nr<0 || nr>=3 || nc<0 || nc>=3)	continue;	// 경계체크
				StringBuilder str =  new StringBuilder();		// 빈칸 인접한 4방향 숫자하고 자리 바꾸기
				str.append(puz);
				str.replace(origin[r][c], origin[r][c]+1, str.charAt(origin[nr][nc])+"");
				str.replace(origin[nr][nc], origin[nr][nc]+1, "9");
				String nstr = str+"";	
				
				int npuz = Integer.parseInt(nstr);
				
				if(set.contains(npuz))	continue;		// 방문체크
				
				queue.offer(new int[] {npuz,time+1});
				set.add(npuz);
			}
			
		}
		return -1;
	}

}
