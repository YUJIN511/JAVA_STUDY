import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
 * 봄 : 나무가 자신의 나이만큼 양분을 먹고 나이+1
 * -> 여러 나무가 있다면, 나이가 어린 나무부터 양분을 먹음
 * -> 양분이 부족하면 나무는 죽음
 * 
 * 여름 : 봄에 죽은 나무가 양분으로 변함
 * -> 나이를 2로 나눈 값이 양분에 추가
 * 
 * 가을 : 나무가 번식하는데, 나이가 5이 배수어야 함
 * -> 인접한 칸(상하좌우대각선) 에 나이가 1인 나무가 생김
 * 
 * 겨울 : 입력으로 주어주는 양분 추가
 * 
 * ---------------------------
 * 출력 : K년이 지난 후 살아있는 나무의 수 
 */
public class Main_16235_나무재테크 {
	
	static LinkedList<Tree> tree;
	static int ground[][];
	static int A[][];
	static int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1} };
	static int N;
	static int K;
	
	static class Tree {
		int r;
		int c;
		int z;
		boolean life;
		
		public Tree(int x, int y, int z) {
			super();
			this.r = x;
			this.c = y;
			this.z = z;
			this.life =false;
		}
		
		public void update(int z) {
			this.z=z;
		}
		public void die () {
			this.life=true;
		}
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// N*N 땅
		int M = Integer.parseInt(st.nextToken());	// M개의 나무
		K = Integer.parseInt(st.nextToken());	// K년 후
		
		ground = new int[N+1][N+1];				// 현재 양분
		A = new int[N+1][N+1];					// 추가되는 양분 양
		tree = new LinkedList<Tree>();	// 나무 정보
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				ground[i][j]=5;
				A[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		int r, c, z;
		
		for(int k=0; k<M; k++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());	// 나무 위치
			c = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());	// 나무 나이
			tree.offer(new Tree(r, c, z));
		}
		
		invest();	// 재테크
		
		System.out.println(tree.size());	// 살아있는 나무 개수 출력
	}
	private static void invest() {
		ArrayDeque<Tree> addTree = new ArrayDeque<>();
		
		while(K-->0) {	// K년 동안 반복
			
			// 봄
			for(Tree t : tree) {
				if(ground[t.r][t.c]>=t.z) {	// 양분을 먹음
					ground[t.r][t.c]-=t.z;
					t.update(t.z+1);
				}
				else {	// 양분을 못 먹어 죽음
					t.die();
				}
			}
			
			Tree cur;
			// 여름
			Iterator<Tree> it = tree.iterator();
			while(it.hasNext()) {
				cur = it.next();
				if(cur.life) {
					ground[cur.r][cur.c] += cur.z/2;	// 죽은 나무가 있던 칸에 양분 추가
					it.remove();
				}
			}
			
			// 가을
			int nr;
			int nc;
			for(Tree t: tree) {
				if(t.z%5 != 0)	continue;	// 나이가 5의 배수면 번식 가능
				
				for(int i=0; i<8; i++) {	// 인접한 8개 칸에 번식
					nr = t.r + dir[i][0];
					nc = t.c + dir[i][1];
					if(nr<1 || nr>N || nc<1 || nc>N)	continue;	// 경계 체크
					addTree.offer(new Tree(nr, nc, 1));	// 번식	
				}
			}
			while(!addTree.isEmpty()) {
				cur = addTree.poll();
				tree.offerFirst(cur);	// 번식
			}
				
			// 겨울
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					ground[i][j]+=A[i][j];
				}
			}
		}
	}
}
