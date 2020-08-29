import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class brandi_스마트밴드 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());	// 사용자의 나이
		int arr[] = new int[6];
		int max_num = 220-N;				// 최대 심박수
		
		while(true) {
			String str = br.readLine();
			if(str==null)	break;
			
			int num = Integer.parseInt(str);	// 초당 심박수
			
			double temp = num*100/max_num;
			if(temp <60) 		arr[0]++;
			else if(temp<68)	arr[1]++;
			else if(temp<75)	arr[2]++;
			else if(temp<80)	arr[3]++;
			else if(temp<90)	arr[4]++;
			else				arr[5]++;
		}
		
		for(int i=5; i>=0; i--) {
			System.out.print(arr[i]+" ");
		}
	}
}
