import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 입력
 * N : 도시 개수
 * 모든 도시 쌍에 대한 최소 이동 시간 배열
 * 
 * 출력 
 * - 도로 개수가 최소일 때 모든 도로 시간의 합
 * - 불가능할 경우 -1
 */
public class Main_1507_궁금한민호 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());	// N개의 도시
		int city[][] = new int[N][N];
		int result =0;
		int sum=0;
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int []>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]- o2[2];
			}
		});
		
		for(int i=0; i<N; i++) {	// 입력
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if(i!=j)pq.add(new int[] {i, j, city[i][j]});
				sum+=city[i][j];
			}
		}
		boolean edge[][] = new boolean[N][N];	// 노드가 연결되어 있는지 체크
		
		int cur[];
		Top:
		while(!pq.isEmpty()) {
			cur = pq.poll();
			int r = cur[0];
			int c = cur[1];
			
			if(edge[r][c])	continue;	// 이미 연결되어 있으면
			
			boolean flag = true;
			for(int k=0; k<N; k++) {
				if(!edge[r][k] && !edge[k][c])	continue;
				if(city[r][c] > city[r][k]+city[k][c]) {	// 더 작은 최소 거리가 존재할 경우
					result=-1;								// 문제의 입력대로 연결 될 수 없으므로
					break Top;
				}
				if(city[r][k]+city[k][c] == city[r][c]) {	// 이미 최소 거리가 연결되어 있으면 false
					flag = false;
				}
			}
			if(flag) {	// 연결이 되어 있지 않은 경우
				edge[r][c] = true;
				edge[c][r] = true;
				result+=city[r][c];
			}
		}
		
		System.out.println(result);
	}
}
