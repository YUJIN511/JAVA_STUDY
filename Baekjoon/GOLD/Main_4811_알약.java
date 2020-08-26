import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_4811_알약 {
	
	static long mem[][];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int N = Integer.parseInt(br.readLine());	// 약의 개수
		mem = new long[31][31];
		
		while(N != 0) {
			
			System.out.println(DFS(N, 0));
			
			N = Integer.parseInt(br.readLine());
		}
		
	}

	private static long DFS(int W, int H) {
		
		if(mem[W][H] != 0) {	// 이미 계산한 적 있으면 값 반환
			return mem[W][H];
		}
		
		if(W==0)	return 1;	// 무조건 알약 반개만 쓰므로 경우의 수 1
		
		mem[W][H] = DFS(W-1, H+1);	// 알약 1개 꺼내기
		
		if(H>0) mem[W][H] += DFS(W,H-1);	// 알약 반개 꺼내기
		
		return mem[W][H];
		
	}

}
