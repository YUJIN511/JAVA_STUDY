import java.util.ArrayList;

public class Solution_자물쇠와열쇠 {
	static ArrayList<node> Locklist;
	static ArrayList<node> Keylist;
	static node rotate[][];
	static int N;
	static int M;
	static int home;
	static int[][] LOCK;
	
	static class node{
		int y;
		int x;
		public node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	public static void main(String[] args) {
		if(solution(new int[][]
				{{0, 0, 0}, 
				{1, 0, 0}, 
				{0, 1, 1}}, 
					new int[][] 
				{{1, 1, 1},  
				{1, 1, 0},
				{1, 0, 1}})) {
			System.out.println("true");
		}
		else {
			System.out.println("false");
		}
	}
	
	public static boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        LOCK= lock;
        N = lock.length;
        M = key.length;
        
       Locklist = new ArrayList<>(); 
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {        
        		if(lock[i][j] == 0)  	{
        			Locklist.add(new node(i,j));	// 자물쇠 홈만 add
        			home++;
        		}
        	}
        }
        
       Keylist = new ArrayList<>(); 
        for(int i=0; i<M; i++) {
        	for(int j=0; j<M; j++) {
        		if(key[i][j]==1)	Keylist.add(new node(i,j));
        	}
        }
        
        if(Locklist.size() > Keylist.size())	return answer;	// 불가능
        if(Locklist.isEmpty())	return true; // 자물쇠가 열려 있는 경우
        // 회전 배열 생성
        rotate = new node[M][M];
        int temp=M-1;
        for(int i=0; i<M; i++) {
        	for(int j=0; j<M; j++) {
        		rotate[i][j] = new node(j,temp);
        	}
        	temp--;
        }
        
        // 회전 : 0,90,180, 270
        Top:
        for(int i=0; i<4; i++) {	
			if(i!=0) {	// 처음은 0도 회전
				ArrayList<node> rotateList = new ArrayList<>();
				for(int j=0; j<Keylist.size(); j++) {	// 회전
					node next = rotate[Keylist.get(j).y][Keylist.get(j).x];
					rotateList.add(new node(next.y, next.x));
				}
				Keylist = rotateList;
			}
	        for(node start : Keylist) {	// 채우고 시작할 돌기 선택
	        	answer = check(start.y, start.x);    
	        	if(answer)	break Top;
	        }
        }
        
        return answer;
    }

	private static boolean check(int sy, int sx) {
		
		for(node end : Locklist) {	// 처음에 홈부분을 채우고 시작할 위치
			int my = end.y - sy;	// x 몇칸 이동
			int mx = end.x - sx;	// y 몇칸 이동
	
			int lockcount=1;
			for(node cur : Keylist) {
				if(cur.y == sy && cur.x == sx)	continue;		// 시작 키는 패쓰
				
				int ny = cur.y + my;
				int nx = cur.x + mx;
				
				if(ny>=N || ny<0 || nx>=N || nx<0 )	continue;
				if(LOCK[ny][nx]==1) {	// 돌기와 돌기가 만나는 경우 안됨
					lockcount=-1;
					break;
				}
				if(LOCK[ny][nx]==0)	lockcount++;
			}
				
			if(lockcount==home)	return true;
		}
		
		return false;
		
	}
	
}
