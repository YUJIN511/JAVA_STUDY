import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_12869_뮤탈리스크 {
	
	static int result;
	static int SCV[];
	static int attack[][]= {{-9,-3,-1},{-3,-9,-1},{-9,-1,-3},{-3,-1,-9},{-1,-3,-9},{-1,-9,-3}};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N= Integer.parseInt(br.readLine());
		SCV = new int[3];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			SCV[i] = Integer.parseInt(st.nextToken());
		}
		
		// 첫번째 -9, 두번째 -3, 세번째 -1
		
		result = Integer.MAX_VALUE;
		if(N==1) {
			result = SCV[0]/9;
			result += SCV[0]%9 == 0? 0 : 1;
		} else {
			BFS();
		}
		
		System.out.println(result);
			
	}
	private static void BFS() {
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {SCV[0],SCV[1],SCV[2],0});
		boolean visit[][][][] = new boolean[61][61][61][16];
		
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			int S[] = new int[] {cur[0],cur[1], cur[2]};
			int cnt = cur[3];
			
			if(S[0]<=0 && S[1]<=0 && S[2]<=0) {	// 다 파괴한 경우
				result = cnt;
				return;
			}
			
			for(int i=0; i<6; i++) {
				int NS1 = (S[0]+attack[i][0]) < 0 ? 0 : S[0]+attack[i][0];
				int NS2 = (S[1]+attack[i][1]) < 0 ? 0 : S[1]+attack[i][1];
				int NS3 = (S[2]+attack[i][2]) < 0 ? 0 : S[2]+attack[i][2];

				if(!visit[NS1][NS2][NS3][cnt+1]) {	// 공격 이력 체크
					visit[NS1][NS2][NS3][cnt+1]=true;
					queue.offer(new int[] {NS1, NS2, NS3, cnt+1});
				}
			}
		}

	}
}
