import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution_모의_벽돌깨기 {
 
     
    static int N; // 구슬 횟수
    static int W; // 
    static int H; // W * H 배열
    static int[] numbers;
    static int result;
    static int brick_num;
    static int [][]arr;
    static int [][]map;
    static boolean [][]visit;
    static int [][]dir= {{-1,0}, {1,0}, {0, -1}, {0, 1}}; // 상 하 좌 우
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
 
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int T= Integer.parseInt(br.readLine());
         
        for(int test_case=1; test_case<=T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            numbers= new int[N];
            result=W*H;
            map = new int[H][W];
            brick_num=0;
            // 배열 입력
            for(int i=0; i<H; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int j=0; j<W; j++) {
                    map[i][j]=Integer.parseInt(st.nextToken());
                }
            }
             
            brick(0);
             
            System.out.println("#"+test_case+" "+result);
        }
    }
     
    private static void brick(int cnt) {
        int y;
        int x;
        int count;
         
        if(cnt == N) { // 구슬 횟수
 
            arr=new int[H][W];
             
            for(int i=0; i<H; i++) {
                for(int j=0; j<W; j++) {
                    arr[i][j]=map[i][j];
                }
            }
             
            for(int i=0; i<N; i++) {
                visit=new boolean[H][W];
                y=0;
                x=numbers[i];
                while(y<H && (arr[y][x]==0 || (arr[y][x]!=0 && visit[y][x]))) {  // 벽돌을 찾을 때 까지
                    y++;
                }
                if(y>=H) continue;
                boom(y,x);  // 벽돌 깨기
                 
                for(int c=0; c<W; c++) {
                    int index=H-1;
                    for(int r=H-1; r>=0; r--) {
                        if(!visit[r][c] && arr[r][c]>0) {
                            if(index!=r) {
                                arr[index][c]=arr[r][c];
                                arr[r][c]=0;
                                visit[r][c]=true;
                            }
                            index--;
                        }
                    }
                    for(int r=0; r<=index; r++) {
                        arr[r][c]=0;
                    }
                }
            }
             
            count=0;
            for(int i=0; i<H; i++) {
                for(int j=0; j<W; j++) {
                    if(arr[i][j]!=0)count++;
                }
            }
            if(result>count) result=count;   
            return;
        }
         
        for(int i=0; i<W; i++) {
            numbers[cnt]=i;
            brick(cnt+1);
        }
    }
 
    // 벽돌 깨기
    private static void boom(int y, int x) {
        int r;
        int c;
        visit[y][x]=true;
        if(arr[y][x]>1) {    // 그 외
            for(int i=0; i<4; i++) { // 상 하 좌 우
                r=y;
                c=x;
                for(int j=1; j<arr[y][x]; j++) { // 범위 만큼
                     
                    r+=dir[i][0];
                    c+=dir[i][1];
                     
                    if(r<0 || r>=H || c<0 || c>=W)  break;
                     
                    if(arr[r][c]>1 && !visit[r][c]) {
                        boom(r,c);
                    }
                     
                    if(arr[r][c]!=0 && !visit[r][c]) {
                        visit[r][c]=true;
                    }
                     
                }
            }
        }
    }
}