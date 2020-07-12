/**************************************************************
    Problem: 1828
    User: smlie5917
    Language: Java
    Result: Success
    Time:136 ms
    Memory:8208 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;
 
public class Main_1828_냉장고{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int [][] arr = new int[N][2];   // 최저보관온도, 최고보관온도
        int count;
        int result=99999;
         
        for(int i=0; i<N; i++) {
            st=new StringTokenizer(br.readLine(), " ");
            arr[i][0]=Integer.parseInt(st.nextToken());
            arr[i][1]=Integer.parseInt(st.nextToken());
        }
         
        Arrays.sort(arr, new Comparator<int[]>() {
 
            @Override
            public int compare(int[] o1, int[] o2) {
                int a = o1[1];
                int b = o2[1];
                 
                return Integer.compare(a, b);
            }
             
        });
        for(int i=0; i<N; i++) {
            int start=arr[i][0];
            int end =arr[i][1];
            count=1;
            for(int j=0; j<N; j++) {
                if(i!=j) {
                    if((start<=arr[j][0] && end>=arr[j][0])|| (end>=arr[j][1] && start<=arr[j][1])
                            || (arr[j][0]<=start && start<=arr[j][1]) || (arr[j][0]<=end && end<=arr[j][1])) {
                        start=Math.max(start, arr[j][0]);
                        end=Math.min(end, arr[j][1]);
                    }
                    else {
                        start=arr[j][0];
                        end=arr[j][1];
                        count++;
                    }
                }
            }
            if(result>count) result=count;
        }
         
        System.out.println(result);
    }
     
}