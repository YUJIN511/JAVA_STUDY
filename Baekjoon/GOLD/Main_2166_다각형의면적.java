import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2166_다각형의면적 {
	
	static public class Points{
		int x;
		int y;
		
		public Points(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Points[] p = new Points[N];
		int y;
		int x;
		for(int i=0; i<N; i++) {	// 도형 좌표 입력
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			p[i] = new Points(y, x);
		}
		
		long result=0;
		for(int i=1; i<N-1; i++) {
			result+=ccw(p[0],p[i],p[i+1]);
		}
		// 삼각형 면적 공식 = absin0/2
		if(result%2 == 0) {
			System.out.println(Math.abs(result)/2+".0");
		}
		else {
			System.out.println(Math.abs(result)/2+".5");
		}
	}
	
	// 외적의 크기
	private static long ccw(Points A, Points B, Points C) {
		
		return (long)(B.x-A.x)*(C.y-A.y)-(long)(C.x-A.x)*(B.y-A.y);
	}
}
