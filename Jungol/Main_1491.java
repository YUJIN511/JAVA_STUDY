import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
 
public class Main_1491 {
    static int N;
    static int D;
    static int dis[];
    static int time[];
    static int results[];
 
    public static void main(String[] args) throws Exception{
    	System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        D = Integer.parseInt(br.readLine());    // 정비를 받지 않고 갈 수 있는 최대 거리
        N = Integer.parseInt(br.readLine());    // 정비소 수
 
        StringTokenizer st = new StringTokenizer(br.readLine());
        dis = new int[N+2];
        time = new int[N+2];
         
        for(int i=0; i<=N; i++) {
            dis[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }
        
        int dp[] = new int[N+2];
        results = new int[N+2];
        
        for(int i=1; i<=N+1; i++) {
        	dp[i]=Integer.MAX_VALUE;
        	int d = 0;
        	
        	for(int j=i-1; j>=0; j--) {
        		d += dis[j];
        		if(d > D) break;
        		
        		if(dp[i] > dp[j]+time[i]) {
        			dp[i] = dp[j]+time[i];
        			results[i]=j;
        		}
        	}
        }
        System.out.println(dp[N+1]);
        if(dp[N+1] != 0) {
        	int i=0;
        	int cnt=0;
        	Stack<Integer> s = new Stack<Integer>();
        	for(i=N+1; i>0; i=results[i]) {
        		cnt++;
        		s.push(results[i]);
        	}
        	
        	System.out.println(cnt-1);
        	s.pop();
        	while(!s.isEmpty()) {
        		System.out.print(s.pop()+" ");
        	}
        	System.out.println();        	
        }
    }
}