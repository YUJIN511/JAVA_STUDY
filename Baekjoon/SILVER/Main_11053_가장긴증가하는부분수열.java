import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11053_가장긴증가하는부분수열 {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int [N];
		int num[] = new int [N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i]= Integer.parseInt(st.nextToken());
		}
		
		int max=1;
		num[0]=1;
		for(int i=1; i<N; i++) {
			num[i]=1;
			for(int j=0; j<i; j++) {
				if(arr[j]<arr[i])	num[i]=Math.max(num[i], num[j]+1);
			}
			max = Math.max(max, num[i]);
			
		}
		System.out.println(max);
	}
}
