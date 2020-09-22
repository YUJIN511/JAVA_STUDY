import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14888_연산자끼워넣기 {
	static int N;
	static int numArr[];
	static int op[];
	static int selected[];
	static int max=Integer.MIN_VALUE;
	static int min=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());	// 수의 개수
		numArr = new int[N];
		op = new int[4];
		
		st = new StringTokenizer(br.readLine()); 
		for(int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		
		selected = new int[N];
		
		DFS(0);
		
		System.out.println(max);
		System.out.println(min);
		
	}

	private static void DFS(int cnt) {
		if(cnt == N-1) {
			
			int num = cal();	// 뽑은 연산자로 계산
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(op[i]!=0) {
				op[i]--;
				selected[cnt]=i;	// 연산자 뽑기
				DFS(cnt+1);
				op[i]++;
			}
		}
		
	}

	private static int cal() {
		int result = numArr[0];
		
		for(int i=1; i<N; i++) {
			switch (selected[i-1]) {
			case 0:		//'+'
				result +=numArr[i];
				break;
			case 1:		//'-'
				result -=numArr[i];
				break;
			case 2:		//'*'
				result *=numArr[i];
				break;
			case 3:		//'/'
				result/=numArr[i];
				break;
			}
		}
		return result;
	}
}
