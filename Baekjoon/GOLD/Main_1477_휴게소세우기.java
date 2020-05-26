import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1477_휴게소세우기 {
	static class data{
		int dis;	// 휴게소 없는 구간의 길이
		int cnt;	// 휴게소가 몇개 세웠는지(cnt-1 개)
		public data(int dis, int cnt) {
			super();
			this.dis = dis;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int restArea [] = new int[N+2];
		PriorityQueue<data> pq = new PriorityQueue<>(new Comparator<data>() {
			@Override
			public int compare(data o1, data o2) {
				int a = o1.dis/o1.cnt;
				int b = o2.dis/o2.cnt;
				
				if(o1.dis % o1.cnt != 0) a++;
				if(o2.dis % o2.cnt != 0) b++;
				
				return Integer.compare(b, a);
			}
		});
		
		st = new StringTokenizer(br.readLine());
		restArea[0]=0;	// 시작점
		for(int i=1; i<=N; i++) {
			restArea[i] = Integer.parseInt(st.nextToken());
		}
		restArea[N+1] = L;	// 고속도로 길이, 끝점
		
		Arrays.sort(restArea);
		
		for(int i=1; i<=N+1; i++) {
			pq.offer(new data(restArea[i]-restArea[i-1],1));	// 휴게소 없는 구간의 길이
		}
		
		while(M-- > 0) {
			data max = pq.poll();
			pq.offer(new data(max.dis, max.cnt+1));
		}
		
		data temp = pq.poll();
		if(temp.dis % temp.cnt !=0 ) {
			System.out.println(temp.dis/temp.cnt+1);
		} else {
			System.out.println(temp.dis/temp.cnt);
		}
		
	}
}
