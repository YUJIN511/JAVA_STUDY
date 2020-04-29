import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_6109_추억의2048게임 {
	static int arr[][];
	static int N;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 배열 크기
			String dir = st.nextToken();				// 방향
			arr = new int [N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		
			game(dir);
			
			System.out.println("#"+test_case);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
		}
	}

	private static void game(String dir) {
		switch (dir) {
		case "up":
			for(int c=0; c<N; c++) {
				int r=0;
				while(r<N-1) {
					int nr = r+1;
					if(arr[r][c] == 0) {
						while(nr<N-1 && arr[nr][c]==0)	nr++;
						arr[r][c]=arr[nr][c];
						arr[nr][c]=0;
					}
					nr = r+1;
					while(nr<N && arr[nr][c]==0)	nr++;
					
					if(nr==N) break;	// 범위 끝
					
					if(arr[r][c]==arr[nr][c]) {
						arr[r][c]*=2;
						arr[nr][c]=0;
					}
					else {
						arr[r+1][c]=arr[nr][c];
						if(r+1!=nr)arr[nr][c]=0;
					}
					r++;
				}
			}
			break;
		case "down":
			for(int c=0; c<N; c++) {
				int r=N-1;
				while(r>0) {
					int nr = r-1;
					if(arr[r][c] == 0) {
						while(nr>0 && arr[nr][c]==0)	nr--;
						arr[r][c]=arr[nr][c];
						arr[nr][c]=0;
					}
					nr = r-1;
					while(nr>-1 && arr[nr][c]==0)	nr--;
					
					if(nr==-1) break;	// 범위 끝
					
					if(arr[r][c]==arr[nr][c]) {
						arr[r][c]*=2;
						arr[nr][c]=0;
					}
					else {
						arr[r-1][c]=arr[nr][c];
						if(r-1!=nr)arr[nr][c]=0;
					}
					r--;
				}
			}
			break;
		case "left":
			for(int r=0; r<N; r++) {
				int c=0;
				while(c<N-1) {
					int nc = c+1;
					if(arr[r][c] == 0) {
						while(nc<N-1 && arr[r][nc]==0)	nc++;
						arr[r][c]=arr[r][nc];
						arr[r][nc]=0;
					}
					nc = c+1;
					while(nc<N-1 && arr[r][nc]==0)	nc++;
					
					if(nc==N) break;	// 범위 끝
					
					if(arr[r][c]==arr[r][nc]) {
						arr[r][c]*=2;
						arr[r][nc]=0;
					}
					else {
						arr[r][c+1]=arr[r][nc];
						if(c+1!=nc)arr[r][nc]=0;
					}
					c++;
				}
			}
			break;
		case "right":
			for(int r=0; r<N; r++) {
				int c=N-1;
				while(c>0) {
					int nc = c-1;
					if(arr[r][c] == 0) {
						while(nc>0 && arr[r][nc]==0)	nc--;
						arr[r][c]=arr[r][nc];
						arr[r][nc]=0;
					}
					nc = c-1;
					while(nc>-1 && arr[r][nc]==0)	nc--;
					
					if(nc==-1) break;	// 범위 끝
					
					if(arr[r][c]==arr[r][nc]) {
						arr[r][c]*=2;
						arr[r][nc]=0;
					}
					else {
						arr[r][c-1]=arr[r][nc];
						if(c-1!=nc)arr[r][nc]=0;
					}
					c--;
				}
			}
			break;
		}
	}
}
