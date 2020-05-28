import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17281_야구 {

	static int N;
	static int result;
	static int arr[][];
	static boolean selected[];
	static int numbers[];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int [N+1][10];
		selected = new boolean [10];
		numbers = new int [10];
		
		// 입력
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=9; j++) {
				arr[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		numbers[4]=1;
		selected[1]=true;
		result=0;
		per(1);
		
		System.out.println(result);
		
	}

	private static void per(int cnt) {
		if(cnt == 10) {
			play();
			return;
		}
		
		for(int i=2; i<=9; i++) {
			if(!selected[i]) {
				selected[i]=true;
				numbers[cnt]=i;
				if(cnt==3)	per(cnt+2);
				else		per(cnt+1);
				selected[i]=false;
			}
		}
		
	}

	private static void play() {
		int index=1;
		int round=1;
		int out=0;
		int score=0;
		boolean base[] = new boolean[4];
		
		while(round<=N) {	// N이닝동안 게임 진행
			int hit;
			int num= numbers[index];
			hit=arr[round][num];
			
			switch (hit) {
			case 0:			// 아웃
				out++;
				break;
			case 1:			// 안타
			case 2:			// 2루타
			case 3:			// 3루타
				for(int i=3; i>0; i--) {
					if(base[i]) {
						base[i]=false;
						if(hit+i>3) {
							score++;
						}
						else {
							base[hit+i]=true;
						}
					}
				}
				base[hit]=true;
				break;
			case 4:			// 홈런
				score++;
				for(int i=1; i<4; i++) {
					if(base[i]) {
						base[i]=false;
						score++;
					}
				}
				break;
			default:
				break;
			}
			
			if(out==3) {	// 3아웃일 때
				out=0;
				round++;
				base = new boolean[4];
			}
			index = (index+1 > 9)? 1 : index+1;
		}
		
		result = Math.max(result, score);
		
	}

}
