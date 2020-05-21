import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_17140_이차원배열과연산 {
	static int arr[][];
	static int rsize;
	static int csize;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		arr = new int[101][101];
		
		for(int i=1; i<=3; i++) {	// 입력
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		rsize=3;
		csize=3;
		int time=0;
		
		while(arr[r][c]!=k) {
			if(rsize>=csize)	rcal();
			else				ccal();
			
			if(time++ == 100)	break;	// 100넘어가는 경우 끝
		}
		
		System.out.println(time>100? -1 : time);
	}

	private static void rcal() {
		int cmax=-1;
		int mapsize[] = new int[rsize+1];	// 맵사이즈
		
		for(int r=1; r<=rsize; r++) {
			HashMap<Integer, Integer> map = new HashMap<>();
			for(int c=1; c<=csize; c++) {	// r 연산
				int key = arr[r][c];
				if(key==0)	continue;			
				if(map.containsKey(key)) {	// 체크한 숫자인 경우
					int value = map.get(key);
					map.replace(key, value, value+1);	// 등장횟수 +1
				} else {
					map.put(key, 1);
				}
			}
			ArrayList<Integer> keylist = new ArrayList<>(map.keySet());
			Collections.sort(keylist, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) { 
					if(map.get(o1)== map.get(o2))	return Integer.compare(o1, o2);		// value 오름차순
					else							return Integer.compare(map.get(o1), map.get(o2));	// key 오름차순
				}
			});
			int idx=1;
			for(int c : keylist) {	// 연산 값 넣기
				arr[r][idx++] = c;
				arr[r][idx++] = map.get(c);
			}
			mapsize[r]=map.size()*2;
			if(r==1)	cmax = map.size()*2;
			else		cmax=Math.max(cmax, map.size()*2);
		}
		csize = cmax;
		for(int r=1; r<=rsize; r++) {	// 열의 크기가 커진 곳 0 채우기
			for(int c=mapsize[r]+1; c<=csize; c++) {	
				arr[r][c]=0;
			}
		}
	}
	private static void ccal() {
		int rmax = -1;
		int mapsize[] = new int[csize+1];	// 맵사이즈
		
		for(int c=1; c<=csize; c++) {
			HashMap<Integer, Integer> map = new HashMap<>();
			for(int r=1; r<=rsize; r++) {	// c 연산
				int key = arr[r][c];
				if(key==0)	continue;
				if(map.containsKey(key)) {	// 체크한 숫자인 경우
					int value = map.get(key);
					map.replace(key, value, value+1);	// 등장횟수 +1
				} else {
					map.put(key, 1);
				}
			}
			ArrayList<Integer> keylist = new ArrayList<>(map.keySet());
			Collections.sort(keylist, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) { 
					if(map.get(o1)== map.get(o2))	return Integer.compare(o1, o2);		// value 오름차순
					else							return Integer.compare(map.get(o1), map.get(o2));	// key 오름차순
				}
			});
			int idx=1;
			for(int r : keylist) {	// 연산 값 넣기
				arr[idx++][c] = r;
				arr[idx++][c] = map.get(r);
			}
			mapsize[c]=map.size()*2;
			if(c==1)	rmax = map.size()*2;
			else		rmax=Math.max(rmax, map.size()*2);
		}
		rsize = rmax;
		for(int c=1; c<=csize; c++) {	// 행의 크기가 커진 곳 0 채우기
			for(int r=mapsize[c]+1; r<=rsize; r++) {	
				arr[r][c]=0;
			}
		}
	}


}
