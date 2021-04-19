import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1257 {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // 전깃줄 개수
		ArrayList<Integer> result = new ArrayList<Integer>();// 없애야하는 전깃줄
		
		int arr[][] = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// A 기준 오름차순 정렬
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		
		int lis[] = new int[N];
		int record[] = new int[N];
		lis[0]=arr[0][1];
		
		int idx=0;
		for(int i=1; i<N; i++) {
			if(lis[idx] < arr[i][1]) {
				idx++;
				lis[idx] = arr[i][1];
				record[i]=idx;
			} else {
				int left = 0;
				int right = idx;
				while(left < right) {
					int mid = (left+right)/2;
					
					if(lis[mid] < arr[i][1]) {
						left = mid+1;
					} else {
						right = mid;
					}
				}
				lis[right] = arr[i][1]; 
				record[i]=right;
			}
		}
		
		int answer = N-(idx+1);
		System.out.println(answer);

		boolean visited[] = new boolean[N];
		for(int i=N-1; i>=0; i--){
			if(idx==record[i]) {
				visited[i]=true;
				idx--;
			}
		}
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				bw.write(arr[i][0]+"\n");			
			}
		}
		bw.flush();
	}
}
