import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2570 {
	static int K;
	static char data[];
	static boolean selected[];
	static StringBuilder number;
	static ArrayList<String> result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());	
		StringTokenizer st = new StringTokenizer(br.readLine());
		data = new char[K];
		for(int i=0; i<K; i++) {
			data[i] = st.nextToken().charAt(0);
		}
		
		selected = new boolean[10];
		result = new ArrayList<String>();
		for(int i=0; i<10; i++) {
			number=new StringBuilder(i+"");
			selected[i]=true;
			dfs(1);
			selected[i]=false;
		}
		
		System.out.println(result.get(result.size()-1));
		System.out.println(result.get(0));
		
	}

	private static void dfs(int cnt) {
		if(cnt == K+1) {
			result.add(number.toString());
			return;
		}
		
		for(int i=0; i<10; i++) {
			if(!selected[i]) {
				if(data[cnt-1]=='<' && number.charAt(cnt-1)-'0' > i) continue;				
				if(data[cnt-1]=='>' && number.charAt(cnt-1)-'0' < i) return;
				
				selected[i]=true;
				number.append(i);
				dfs(cnt+1);
				number.deleteCharAt(number.length()-1);
				selected[i]=false;
			}
		}
		
	}
}
