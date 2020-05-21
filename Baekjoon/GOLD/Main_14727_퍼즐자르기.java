import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14727_퍼즐자르기 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		for(int i=0;i<N; i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		
		int h;
		int w;
		long result=0;
		for(int i=0; i<N; i++) {
			h=arr[i];
			w=1;
			// 왼쪽 확인
			for(int l=i-1; l>=0; l--) {
				if(arr[l]>=h)	w++;
				else			break;
			}
			// 오른쪽 확인
			for(int r=i+1; r<N; r++) {
				if(arr[r]>=h)	w++;
				else			break;
			}
			result = Math.max((long)w*h, result);
		}
		
		System.out.println(result);
	}

}
