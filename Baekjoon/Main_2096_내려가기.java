import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * <입력>
 * N : N줄에 0이상 9이하의 숫자가 3개씩
 * 
 * <출력>
 * 얻을 수 있는 최대점수, 최소점수
 * 
 * - 게임은 첫줄에 시작해서 마지막줄에서 끝
 * - 다음줄로 내려갈 때는 바로 아래의 수 또는 아래의 수에 붙어있는 수로 이동 가능
 */
public class Main_2096_내려가기 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int minmap[] = new int[3];
		int maxmap[] = new int[3];
		
		int input[] = new int[3];
		int tempmin[] = new int[3];
		int tempmax[] = new int[3];
		// 입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				input[j] = Integer.parseInt(st.nextToken());
				if(i!=0){
					if(j==0) {	// 위 && 오른쪽 위 비교
						tempmin[j]=Math.min(minmap[j],minmap[j+1]);
						tempmax[j]=Math.max(maxmap[j],maxmap[j+1]);
					}
					else if(j==1) {	// 왼쪽 위 && 위 && 오른쪽 위 비교
						tempmin[j]=Math.min(Math.min(minmap[j-1], minmap[j]), minmap[j+1]);
						tempmax[j]=Math.max(Math.max(maxmap[j-1], maxmap[j]), maxmap[j+1]);
					}
					else {	// 위  && 오른쪽 위 비교
						tempmin[j]=Math.min(minmap[j-1], minmap[j]);	
						tempmax[j]=Math.max(maxmap[j-1], maxmap[j]);	
					}
				}
			}
			minmap[0]=tempmin[0]+input[0]; // 더해서 최소가 되는 전 값과 지금 값 더함
			minmap[1]=tempmin[1]+input[1];
			minmap[2]=tempmin[2]+input[2];
			maxmap[0]=tempmax[0]+input[0];	// 더해서 최대가 되는 전 값과 지금 값 더함
			maxmap[1]=tempmax[1]+input[1];
			maxmap[2]=tempmax[2]+input[2];
		}
		
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		for(int i=0; i<3; i++) {
			max = Math.max(max, maxmap[i]);
			min = Math.min(min, minmap[i]);
		}
		System.out.println(max+" "+min);	// 결과 출력
	}
}
