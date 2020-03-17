import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_14267_내리갈굼 {

	static ArrayList<Integer>[] s;
	static int count[];
	static int n;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		s = new ArrayList[n+1];
		count = new int[n+1];
	
		for(int i=1; i<=n; i++) {		// 선언
			s[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		
		int num;	
		int w;	
		
		for(int i=2; i<=n; i++) {
			num = Integer.parseInt(st.nextToken());	// 상사 번호
			s[num].add(i);
		}
		
		for(int j=0; j<m; j++) {
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());	// 갈굼 당한 직원
			w = Integer.parseInt(st.nextToken());	// 갈굼 정도
			
			count[num]+=w;
			
		}
		
		attack();	// 내리갈굼
		
		for(int i=1; i<=n; i++) {
			System.out.print(count[i]+" ");
		}
		System.out.println();
	}
	private static void attack() {
		for(int i=1; i<=n; i++) {
			for(int j=0; j<s[i].size(); j++) {
				count[s[i].get(j)]+=count[i];
			}
		}
		
	}
}
