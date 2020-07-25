import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2580_스도쿠 {
	
	static int board[][];
	static boolean rowCheck[][];
	static boolean colCheck[][];
	static boolean squCheck[][];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		board = new int [9][9];
		rowCheck = new boolean [9][10];
		colCheck = new boolean [9][10];
		squCheck = new boolean [9][10];

		int emptyCount=0;
		// 스도쿠 판 입력
		for(int i=0; i<9; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 0) {
					emptyCount++;
				}
				else {
					rowCheck[i][board[i][j]] = true;
					colCheck[j][board[i][j]] = true;
					squCheck[3*(i/3)+(j/3)][board[i][j]]= true;
				}
			}
		}
		
		DFS(emptyCount);
		
	}

	private static void DFS(int emptyCount) {
	
//		스도쿠 완성
		if(emptyCount == 0) {
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					System.out.print(board[i][j]+" ");
				}
				System.out.println();
			}
			System.exit(0);
		}
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(board[i][j] == 0) {	// 빈칸인 경우
					for(int num=1; num<=9; num++) {
						if(!rowCheck[i][num] && !colCheck[j][num] && !squCheck[3*(i/3)+(j/3)][num]) {		// num 숫자를 넣을 수 있는지
							rowCheck[i][num]=true;
							colCheck[j][num]=true;
							squCheck[3*(i/3)+(j/3)][num]=true;
							board[i][j] = num;		// 빈칸 채우기
							DFS(emptyCount-1);
							rowCheck[i][num]=false;
							colCheck[j][num]=false;
							squCheck[3*(i/3)+(j/3)][num]=false;
							board[i][j] = 0;		// 원래대로
						}
					}
					return;	// 빈칸을 못 채운 경우
				}
			}
		}
		
	}

}
