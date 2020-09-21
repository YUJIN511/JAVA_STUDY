import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_1946_신입사원 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			int arr[][] = new int[N][2];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				arr[i][0]=a;
				arr[i][1]=b;
			}
			
			Arrays.sort(arr, new Comparator<int[]>() {	// 0열 기준 오름차순 정렬
				@Override
				public int compare(int[] o1, int[] o2) {
					return Integer.compare(o1[0], o2[0]);
				}
			});
			
			int pre = arr[0][1];
			int cnt = 1;
			for(int i=1; i<N; i++) {
				if(pre<arr[i][1]) continue;
				pre = arr[i][1];
				cnt++;
			}
			System.out.println(cnt);
		}
	}
}
