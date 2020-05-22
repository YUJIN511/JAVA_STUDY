import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4013_특이한자석 {

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T= Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			int arr[][] = new int[5][8];				// 톱니 바퀴 배열
			int k = Integer.parseInt(br.readLine());	// 자석 회전 횟수
			
			//톱니바퀴 입력
			for(int i=1; i<5; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<8; j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
				}			
			}

			while(k-- > 0) {	// k번 회전
				st = new StringTokenizer(br.readLine(), " ");
				int num=Integer.parseInt(st.nextToken());	// 회전 자석 번호
				int dir=Integer.parseInt(st.nextToken());	// 회전 방향
				
				int temp_num=arr[num][6];
				int temp_dir=dir;
				int temp;
				for(int l=num-1; l>0; l--) { // 왼쪽 확인 
					if(temp_num== arr[l][2])	break;	// 같은 극
					temp_num=arr[l][6];	
					
					temp_dir*=-1;	// 반대방향
					if(temp_dir==1) {	// 시계방향으로 회전
						temp=arr[l][7];
						for(int j=7; j>0; j--) {
							arr[l][j]=arr[l][j-1];
						}
						arr[l][0]=temp;
					}
					else {	// 시계 반대방향으로 회전
						temp=arr[l][0];
						for(int j=0; j<7; j++) {
							arr[l][j]=arr[l][j+1];
						}
						arr[l][7]=temp;
					}
					
				}
				temp_num=arr[num][2];	
				temp_dir=dir;
				for(int r=num+1; r<5; r++) { // 오른쪽 확인
					if(temp_num== arr[r][6])	break;	// 같은 극
					temp_num=arr[r][2];
					
					temp_dir*=-1;	// 반대방향
					if(temp_dir==1) {	// 시계방향으로 회전
						temp=arr[r][7];
						for(int j=7; j>0; j--) {
							arr[r][j]=arr[r][j-1];
						}
						arr[r][0]=temp;
					}
					else {	// 시계 반대방향으로 회전
						temp=arr[r][0];
						for(int j=0; j<7; j++) {
							arr[r][j]=arr[r][j+1];
						}
						arr[r][7]=temp;
					}
				}
				
				// 자신 회전
				if(dir==1) {	// 시계방향으로 회전
					temp=arr[num][7];
					for(int j=7; j>0; j--) {
						arr[num][j]=arr[num][j-1];
					}
					arr[num][0]=temp;
				}
				else {	// 시계 반대방향으로 회전
					temp=arr[num][0];
					for(int j=0; j<7; j++) {
						arr[num][j]=arr[num][j+1];
					}
					arr[num][7]=temp;
				}
				
			}	// while문 끝
			
			int result = 0;
			for(int i=1; i<5; i++) {
				result += (1<<(i-1))*arr[i][0];
			}
			
			System.out.println("#"+test_case+" "+result);
		}
	}

}