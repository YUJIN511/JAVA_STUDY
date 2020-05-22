import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3378_스타일리쉬들여쓰기 {
	
	static int m[][];
	static int result[][];
			
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
								
		for(int test_case=1; test_case<=T; test_case++){
			st= new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());	// 마스터 코드 줄 수
			int q = Integer.parseInt(st.nextToken());	// 나의 코드 줄 수 
			
			m = new int[p][4]; // . 소 중 대
			// 마스터 코드
			String str;
			for(int i=0; i<p; i++) {
				str = br.readLine();
				int index=0;	// .의 갯수
				while(str.charAt(index)=='.')	index++;
				
				m[i][0]=index;
				
				if(i>0) {
					m[i][1] = m[i-1][1];
					m[i][2] = m[i-1][2];
					m[i][3] = m[i-1][3];
				}
				
				for(int j=index; j<str.length(); j++) {
					switch (str.charAt(j)) {
					case '(': 	m[i][1]++;	break;
					case ')': 	m[i][1]--;	break;
					case '{': 	m[i][2]++;	break;
					case '}': 	m[i][2]--;	break;
					case '[': 	m[i][3]++;	break;
					case ']': 	m[i][3]--;	break;
					default:
						break;
					}
				}
			}
			
			result = new int[q][4];
			// 내 코드
			for(int i=0; i<q; i++) {
				str = br.readLine();
				int index=0;	
				
				if(i>0) {
					result[i][1] = result[i-1][1];
					result[i][2] = result[i-1][2];
					result[i][3] = result[i-1][3];
				}
				
				for(int j=index; j<str.length(); j++) {
					switch (str.charAt(j)) {
					case '(': 	result[i][1]++;	break;
					case ')': 	result[i][1]--;	break;
					case '{': 	result[i][2]++;	break;
					case '}': 	result[i][2]--;	break;
					case '[': 	result[i][3]++;	break;
					case ']': 	result[i][3]--;	break;
					default:
						break;
					}
				}
			}
			
			for(int i=0; i<q; i++) {
				result[i][0] = -2;	// 안쓰는 값 초기화
			}
			
			//중복 순열
			for(int R = 1; R<= 20; R++) {
				for(int C = 1; C<=20; C++) {
					for(int S =1; S<=20; S++) {
						if(check(R, C, S)) {	// 마스터코드의 해가 되는지
							cal(R, C, S);
						}
					}
				}
			}
			// 결과 출력
			System.out.print("#"+test_case+" 0 ");
			for(int i=1; i<result.length; i++) {
				System.out.print(result[i][0]+" ");
			}
			System.out.println();
		}
	
	}


	private static boolean check(int R, int C, int S) {
		for(int i=1; i<m.length; i++) {
			if(m[i][0] != m[i-1][1]*R + m[i-1][2]*C + m[i-1][3]*S) {
				return false;
			}
		}
		return true;
	}
	
	private static void cal(int R, int C, int S) {
		for(int i=1; i<result.length; i++) {
			int x = result[i-1][1]*R + result[i-1][2]*C + result[i-1][3]*S ;
			
			if(result[i][0] == -2) {
				result[i][0]=x;
			}else if(result[i][0] != x) {
				result[i][0]=-1;
			}
		}
		
	}

}
