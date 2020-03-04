import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_6603_로또 {
	static int R;
	static int arr[];
	static int numbers[];
	static boolean selected[];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while(true) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			
			if(R==0)	return;
			
			arr = new int[R];
			numbers = new int[6];
			selected = new boolean[R];
			
			for(int i=0; i<R; i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
			
			com(0,0);
			System.out.println();
		}
	}

	private static void com(int cnt, int start) {
		if(cnt == 6) {
			for(int j=0; j<6; j++) {
				System.out.print(arr[numbers[j]]+ " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=start; i<R; i++) {
			if(!selected[i]) {
				selected[i]=true;
				numbers[cnt]=i;
				com(cnt+1, i+1);
				selected[i]=false;
			}
		}
	}
}
