/**************************************************************
    Problem: 1462
    User: smlie5917
    Language: Java
    Result: Success
    Time:528 ms
    Memory:10068 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
 
public class Main_1462_보물섬 {
    static int y;
    static int x;
    static char [][]arr;
    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    static int result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
         
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
         
        arr = new char [y][x];
        String str;
        result=0;
         
        for(int i=0; i<y; i++) {
            str=br.readLine();
            arr[i]=str.toCharArray();
        }
         
        for(int i=0; i<y; i++) {
            for(int j=0; j<x; j++) {
                if(arr[i][j]=='L')  bfs(i,j);
            }
        }
        System.out.println(result-1);
    }
 
    private static void bfs(int r, int c) {
        LinkedList<int []> queue = new LinkedList<>();
        int [][]visit = new int [y][x];
         
        queue.offer(new int [] {r, c});
        visit[r][c]=1;
        int [] cur;
        int nr;
        int nc;
        while(!queue.isEmpty()) {
            cur = queue.poll();
            r=cur[0];
            c=cur[1];
             
            for(int i=0; i<4; i++) {
                nr = r+dir[i][0];
                nc = c+dir[i][1];
                if(nr>-1 && nr<y && nc>-1 && nc<x && arr[nr][nc]=='L' && visit[nr][nc]==0) {
                    queue.offer(new int[] {nr, nc});
                    visit[nr][nc]=visit[r][c]+1;
                    if(result<visit[nr][nc]) result=visit[nr][nc];
                }
            }
        }
         
    }
 
}