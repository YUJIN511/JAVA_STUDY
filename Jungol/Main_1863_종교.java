/**************************************************************
    Problem: 1863
    User: smlie5917
    Language: Java
    Result: Success
    Time:407 ms
    Memory:14320 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main_1863_종교 {
    static int [] p;
    static int [] rank;
     
    public static void main(String[] args) throws Exception{
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
         
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        p = new int[n+1];
        rank = new int[n+1];
        int x;
        int y;
         
        for(int i=0; i<=n; i++) {
            p[i]=i;
        }
        String[] arr = new String[n];
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            x=Integer.parseInt(st.nextToken());
            y=Integer.parseInt(st.nextToken());
             
            union(x, y);
             
        }
         
        int result=0;
        for(int i=1; i<=n; i++) {
            if(p[i]==i) result++;
        }
        System.out.println(result);
    }
 
    private static void union(int x, int y) {
        link(find_set(x), find_set(y));
    }
 
    private static void link(int x, int y) {
        if(rank[x]>rank[y]) {
            p[y]=x;
        }
        else {
            p[x]=y;
            if(rank[x]==rank[y])    rank[y]++;
        }
         
    }
 
    private static int find_set(int x) {
        if(x!=p[x]) p[x]=find_set(p[x]);
         
        return p[x];
    }
     
}