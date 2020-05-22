import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Solution_7701_염라대왕의이름정렬 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			TreeSet<String> tset = new TreeSet<>(new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					
					if(o1.compareTo(o2)==0) return 0;
					
					if(o1.length()==o2.length()) {
						return (o1.compareTo(o2)<0)? -1 : 1;
					}
					else {
						return (o1.length()<o2.length())? -1 : 1;
					}
				}
			});
			
			String str;
			
			for(int i=0; i<N; i++) {
				str=br.readLine();
				tset.add(str);
			}
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			bw.write("#"+test_case+"\n");
			bw.flush();
			ArrayList<String> arr = new ArrayList<>(tset);
			for(int i=0; i<arr.size(); i++) {
				bw.write(arr.get(i)+"\n");
				bw.flush();
			}
		}
	}
}
