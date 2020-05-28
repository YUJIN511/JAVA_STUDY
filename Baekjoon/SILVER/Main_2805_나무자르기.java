import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_2805_나무자르기 {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int tree[] = new int [N];
		
		// 나무 높이 입력
		st= new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			tree[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(tree);
		
		int left = 0;
		int right = tree[N-1];
		int mid;
		int result = 0;
		while(left <= right) {
			mid = (left+right)/2;
			
			long sum = 0;
			for(int i=N-1; i>=0; i--) {
				if(tree[i]<mid)	break;
				sum+=tree[i]-mid;
			}
			if(sum == M) {
				result = mid;
				break;
			}
			
			if(sum<M) {
				right = mid-1;
			}
			else {
				left = mid+1;
				result = mid;
			}
		}
		
		System.out.println(result);
	}
}
