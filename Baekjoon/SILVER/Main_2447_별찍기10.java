import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_2447_별찍기10 {
	static int N;
	static char[][] map;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[2188][2188];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j]=' ';
			}
		}
		star(N, 0, 0, false);
		
		// 결과출력
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int i=0; i<N; i++) {
			bw.write(map[i], 0, N);
			bw.write("\n");
			bw.flush();
		}
	}

	private static void star(int n, int r, int c, boolean flag) {
		if(n==1) {
			map[r][c]='*';
			return;
		}
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(i==1 && j==1)continue;
				
				star(n/3, r+(n/3*i), c+(n/3*j), false);
			}
		}
	}
}
