import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_9760_PokerGame {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			boolean scheck = true;
			String result=null;
			char shape;
			int rank[] = new int[15];
			char s='A';
			
			for(int i=0; i<5; i++) {
				String str = st.nextToken();
				shape = str.charAt(0);
				// 슈트 확인
				if(i==0)				s = shape;
				else if(s != shape)	scheck=false;		
				
				// 랭크 확인
				switch (str.charAt(1)) {
				case 'A':	rank[1]++;	rank[14]++;	break;
				case 'T':	rank[10]++;				break;
				case 'J':	rank[11]++;				break;
				case 'Q':	rank[12]++;				break;
				case 'K':	rank[13]++;				break;
				default:	rank[str.charAt(1)-'0']++;	break;
				}
			}
			
			boolean S = false;
			int count=0;
			for(int i=1; i<15; i++) {
				if(rank[i]==1)	count++;
				else			count=0;
				
				if(count==5) {
					S = true;
					break;
				}
			}
			
			if(S && scheck)	result = "Straight Flush";	// 1) 동일한 슈트 && 랭크 연속적
			else {
				boolean FK=false;
				for(int i=1; i<14; i++) {
					if(rank[i]==4) {
						FK=true;	
						break;
					}
				}
				if(FK)	result = "Four of a Kind";	//2) 5장중 4개의 랭크가 모두 같다
				else {
					boolean three = false;
					boolean two1 = false;
					boolean two2 = false;
					for(int i=1; i<14; i++) {	
						if(rank[i]==3)	{
								three = true;
						}
						else if(rank[i]==2) {
							if(!two1)	two1 = true;
							else		two2 = true;
						}
					}
					if(three && (two1 || two2)) {
						result = "Full House";		// 3) 3장 동일한 랭크 && 2장 동일한 랭크
					}
					else if(scheck) {
						result ="Flush";						// 4) 5장 모두 동일한 슈트 
					}
					else if(S) {
								result = "Straight";
					}
					else {
						if(three)					result = "Three of a kind";	// 6) 3장 동일한 랭크
						else if(two1 && two2)		result = "Two pair";		// 7) 2장 동일한 랭크 2개	
						else if(two1 || two2)		result = "One pair";		// 8) 2장 동일한 랭크
					}
				}
			}
			if(result == null)	result ="High card";	// 9) 나머지
			System.out.println("#"+test_case+" "+ result);
			
		}
		
	}
}
