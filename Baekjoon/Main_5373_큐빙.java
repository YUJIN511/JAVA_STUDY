import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_5373_큐빙 {
	
	static char cube [][][];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			int n = Integer.parseInt(br.readLine());	// 큐브 돌린 횟수
			cube = new char [6][3][3];
			char color[] = {'w','g','r','b','o','y'};
			for(int i=0; i<6; i++) {	// 큐브 초기화
				for(int j=0; j<3; j++){
					for(int k=0; k<3; k++) {
						cube[i][j][k]=color[i];
					}
				}
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				String str = st.nextToken();
				char side = str.charAt(0);	// 큐브를 돌릴 면
				char dir = str.charAt(1);	// 큐브를 돌린 방향
				
				rotate(side, dir);
			}

			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					System.out.print(cube[0][i][j]);
				}
				System.out.println();
			}
		}
	}

	private static void rotate(char side, char dir) {
		if(side == 'U') {			// 윗 면
			if(dir=='+') {	// 시계
				char temp1[] = {cube[1][0][0],cube[1][0][1],cube[1][0][2]};
				for(int i=1; i<4; i++) {
					for(int j=0; j<3; j++) {
						cube[i][0][j] = cube[i+1][0][j];
					}
				}
				for(int i=0; i<3; i++) {			
					cube[4][0][i]=temp1[i];
				}
				
				//윗면 돌리기
				char temp2[][] = new char[3][3];
				temp2[0] = cube[0][0].clone();
				temp2[1] = cube[0][1].clone();
				temp2[2] = cube[0][2].clone();
				
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						cube[0][i][j] = temp2[2-j][i];
					}
				}
			} else {		// 반시계
				char temp[] = {cube[4][0][0],cube[4][0][1],cube[4][0][2]};
				for(int i=4; i>1; i--) {
					for(int j=0; j<3; j++) {
						cube[i][0][j] = cube[i-1][0][j];
					}
				}
							
				for(int i=0; i<3; i++) {
					cube[1][0][i]=temp[i];
				}
				
				//윗면 돌리기
				char temp2[][] = new char[3][3];
				temp2[0] = cube[0][0].clone();
				temp2[1] = cube[0][1].clone();
				temp2[2] = cube[0][2].clone();
				
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						cube[0][i][j] = temp2[j][2-i];
					}
				}
			}
		} else if(side == 'D') {	// 아랫 면
			if(dir=='+') {	// 시계
				char temp[] = {cube[4][2][0],cube[4][2][1],cube[4][2][2]};
				for(int i=4; i>1; i--) {
					for(int j=0; j<3; j++) {
						cube[i][2][j] = cube[i-1][2][j];
					}
				}
				for(int i=0; i<3; i++) {
					cube[1][2][i]=temp[i];
				}
				//아랫면 돌리기
				char temp2[][] = new char[3][3];
				temp2[0] = cube[5][0].clone();
				temp2[1] = cube[5][1].clone();
				temp2[2] = cube[5][2].clone();
				
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						cube[5][i][j] = temp2[2-j][i];
					}
				}
			} else {		// 반시계
				char temp[] = {cube[1][2][0],cube[1][2][1],cube[1][2][2]};
				for(int i=1; i<4; i++) {
					for(int j=0; j<3; j++) {
						cube[i][2][j] = cube[i+1][2][j];
					}
				}
				for(int i=0; i<3; i++) {
					cube[4][2][i]=temp[i];
				}
				//아랫면 돌리기
				char temp2[][] = new char[3][3];
				temp2[0] = cube[5][0].clone();
				temp2[1] = cube[5][1].clone();
				temp2[2] = cube[5][2].clone();
				
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						cube[5][i][j] = temp2[j][2-i];
					}
				}
			}
		} else if(side == 'F') {	// 앞 면
			if(dir=='+') {	// 시계
				char temp[] = {cube[1][0][2], cube[1][1][2], cube[1][2][2]};
				
				for(int i=0; i<3; i++) {
					cube[1][i][2] = cube[5][0][i];
					cube[5][0][i] = cube[3][2-i][0];
					cube[3][2-i][0] = cube[0][2][2-i];
					cube[0][2][2-i] = temp[i];
				}
				//앞면 돌리기
				char temp2[][] = new char[3][3];
				temp2[0] = cube[2][0].clone();
				temp2[1] = cube[2][1].clone();
				temp2[2] = cube[2][2].clone();
				
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						cube[2][i][j] = temp2[2-j][i];
					}
				}
				
			} else {		// 반시계
				char temp[] = {cube[0][2][0], cube[0][2][1], cube[0][2][2]};
				
				for(int i=0; i<3; i++) {
					cube[0][2][i] = cube[3][i][0];
					cube[3][i][0] = cube[5][0][2-i];
					cube[5][0][2-i] = cube[1][2-i][2];
					cube[1][2-i][2] = temp[i];
				}
				//앞면 돌리기
				char temp2[][] = new char[3][3];
				temp2[0] = cube[2][0].clone();
				temp2[1] = cube[2][1].clone();
				temp2[2] = cube[2][2].clone();
				
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						cube[2][i][j] = temp2[j][2-i];
					}
				}
			}
		} else if(side == 'B') {	// 뒷 면
			if(dir=='+') {	// 시계
				char temp[] = {cube[0][0][0], cube[0][0][1], cube[0][0][2]};
				
				for(int i=0; i<3; i++) {
					cube[0][0][i] = cube[3][i][2];
					cube[3][i][2] = cube[5][2][2-i];
					cube[5][2][2-i] = cube[1][2-i][0];
					cube[1][2-i][0] = temp[i];
				}
				//뒷면 돌리기
				char temp2[][] = new char[3][3];
				temp2[0] = cube[4][0].clone();
				temp2[1] = cube[4][1].clone();
				temp2[2] = cube[4][2].clone();
				
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						cube[4][i][j] = temp2[2-j][i];
					}
				}
			} else {		// 반시계
				char temp[] = {cube[1][0][0], cube[1][1][0], cube[1][2][0]};
				
				for(int i=0; i<3; i++) {
					cube[1][i][0] = cube[5][2][i];
					cube[5][2][i] = cube[3][2-i][2];
					cube[3][2-i][2] = cube[0][0][2-i];
					cube[0][0][2-i] = temp[i];
				}
				//뒷면 돌리기
				char temp2[][] = new char[3][3];
				temp2[0] = cube[4][0].clone();
				temp2[1] = cube[4][1].clone();
				temp2[2] = cube[4][2].clone();
				
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						cube[4][i][j] = temp2[j][2-i];
					}
				}
			}
		} else if(side == 'L') {	// 왼쪽 면
			if(dir=='+') {	// 시계
				char temp[] = {cube[5][2][0],cube[5][1][0],cube[5][0][0]};
				int idx[] = {5, 2, 0};
				for(int i=0; i<2; i++) {
					for(int j=0; j<3; j++) {
						cube[idx[i]][j][0] = cube[idx[i+1]][j][0];
					}
				} 
				cube[0][0][0]=cube[4][2][2];
				cube[0][1][0]=cube[4][1][2];
				cube[0][2][0]=cube[4][0][2];

				for(int j=0; j<3; j++) {
					cube[4][j][2] = temp[j];
				}
				//왼쪽면 돌리기
				char temp2[][] = new char[3][3];
				temp2[0] = cube[1][0].clone();
				temp2[1] = cube[1][1].clone();
				temp2[2] = cube[1][2].clone();
				
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						cube[1][i][j] = temp2[2-j][i];
					}
				}
			} else {		// 반시계
				char temp[] = {cube[0][2][0],cube[0][1][0],cube[0][0][0]};
				int idx[] = {0, 2, 5};
				for(int i=0; i<2; i++) {
					for(int j=0; j<3; j++) {
						cube[idx[i]][j][0] = cube[idx[i+1]][j][0];
					}
				} 
				cube[5][0][0]=cube[4][2][2];
				cube[5][1][0]=cube[4][1][2];
				cube[5][2][0]=cube[4][0][2];

				for(int j=0; j<3; j++) {
					cube[4][j][2] = temp[j];
				}
				//왼쪽면 돌리기
				char temp2[][] = new char[3][3];
				temp2[0] = cube[1][0].clone();
				temp2[1] = cube[1][1].clone();
				temp2[2] = cube[1][2].clone();
				
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						cube[1][i][j] = temp2[j][2-i];
					}
				}
			}
		} else if(side == 'R') {	// 오른쪽 면
			if(dir=='+') {	// 시계
				char temp[] = {cube[0][2][2],cube[0][1][2],cube[0][0][2]};
				int idx[] = {0, 2, 5};
				for(int i=0; i<2; i++) {
					for(int j=0; j<3; j++) {
						cube[idx[i]][j][2] = cube[idx[i+1]][j][2];
					}
				} 
				cube[5][0][2]=cube[4][2][0];
				cube[5][1][2]=cube[4][1][0];
				cube[5][2][2]=cube[4][0][0];

				for(int j=0; j<3; j++) {
					cube[4][j][0] = temp[j];
				}
				//오른쪽면 돌리기
				char temp2[][] = new char[3][3];
				temp2[0] = cube[3][0].clone();
				temp2[1] = cube[3][1].clone();
				temp2[2] = cube[3][2].clone();
				
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						cube[3][i][j] = temp2[2-j][i];
					}
				}
			} else {		// 반시계
				char temp[] = {cube[5][2][2],cube[5][1][2],cube[5][0][2]};
				int idx[] = {5, 2, 0};
				for(int i=0; i<2; i++) {
					for(int j=0; j<3; j++) {
						cube[idx[i]][j][2] = cube[idx[i+1]][j][2];
					}
				} 
				cube[0][0][2]=cube[4][2][0];
				cube[0][1][2]=cube[4][1][0];
				cube[0][2][2]=cube[4][0][0];

				for(int j=0; j<3; j++) {
					cube[4][j][0] = temp[j];
				}
				
				//오른쪽면 돌리기
				char temp2[][] = new char[3][3];
				temp2[0] = cube[3][0].clone();
				temp2[1] = cube[3][1].clone();
				temp2[2] = cube[3][2].clone();
				
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						cube[3][i][j] = temp2[j][2-i];
					}
				}
			}
		}
		
	}
}
