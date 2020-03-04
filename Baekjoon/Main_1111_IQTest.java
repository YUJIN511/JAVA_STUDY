import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1111_IQTest {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int [N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		boolean check=false;
		
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		if(N==1) {		// 입력받은 수가 하나인 경우
			System.out.println("A");	// 경우의 수가 많다
		}
		else if(N==2) {	// 입력받은 수가 두개이고
			if(arr[0]==arr[1])	System.out.println(arr[0]);	// 같은 수이면 다음수 하나
			else				System.out.println("A");	// 다른 수이면 경우의 수가 많다
		}
		else {		// 그 외
			int a=0;
			int b=0;
			
			if(arr[1]-arr[0]!=0) {	// 0을 나누면 안되므로
				a = (arr[2]-arr[1])/(arr[1]-arr[0]);	
			}

			b = arr[1]-arr[0]*a;
			for(int i=0; i<N-1; i++) {	// 뒤의 순열이 규칙을 갖는지 확인
				if(arr[i]*a+b!=arr[i+1])	{
					check=true;
				}
			}
			
			// 다음 수가 없는 경우
			if(check) 	System.out.println("B");
			else		System.out.println(arr[N-1]*a+b);
		}
	}
}
