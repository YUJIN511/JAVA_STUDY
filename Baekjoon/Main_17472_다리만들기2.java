import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17472_다리만들기2 {
	static int N;
	static int M;
	static int count;
	static int map[][];
	static boolean visited[][];
	static List<int[]> graph;
	static List<int[]> pos;
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static int to, from;
	static int parent[];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		graph = new ArrayList<int[]>();
		pos = new ArrayList<int[]>();
		
		for(int i=0; i<N; i++) {	// 정보 입력
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		count=1;
		//그룹
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					pos.add(new int[] {i,j});
					BFS(i, j);
					count++;
				}
			}
		}
		// 다리 만들기
		for(to=1; to<count; to++) {
			for(from=to+1; from<count; from++) {	
				visited = new boolean[N][M];
				bridge(pos.get(to-1)[0], pos.get(to-1)[1],0,-1);	
			}
		}
		// 오름차순 정렬
		graph.sort(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		// 크루스칼
		int result=0;
		parent = new int[count];
		
		for(int i=1; i<count; i++) {
			parent[i] = i;
		}
		for(int[] cur : graph ) {
			int a = findSet(cur[0]);
			int b = findSet(cur[1]);
			
			if(a == b)	continue;
			
			union(a,b);
			result += cur[2];
		}
		// 섬 모두 연결됬는지 체크
		int check = findSet(1);
		for(int i=2; i<count; i++) {
			if(check != findSet(i)) {
				result=-1;
				break;
			}
		}
		// 결과
		System.out.println(result==0?-1:result);
	}

	private static void union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		if(a != b)	parent[b] = a;
	}

	private static int findSet(int v) {
		if(parent[v] == v)	return v;
		else {
			parent[v]=findSet(parent[v]);
			return parent[v];
		}
	}

	private static void bridge(int r, int c, int l, int d) {
		visited[r][c]=true;
		if(map[r][c]!= 0 && d!=-1) {
			if(map[r][c] == from && l>2) {
				graph.add(new int[] {to, from, l-1});
			}
			visited[r][c]=false;
			return;
		}
		
		if(d==-1) {
			for(int i = 0; i<4; i++) {
				int nr = r + dir[i][0];
				int nc = c + dir[i][1];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M || visited[nr][nc])	continue; // 경계 체크
				
				if(map[nr][nc]==to)	bridge(nr,nc,l,-1);
				else				bridge(nr,nc,l+1,i);
			}
		}
		else {
			int nr = r + dir[d][0];
			int nc = c + dir[d][1];
			if(nr>=0 && nr<N && nc>=0 && nc<M && !visited[nr][nc]) {	// 경계 체크
				bridge(nr, nc, l+1, d);
			}
		}
		visited[r][c]=false;
	}
	
	private static void BFS(int i, int j) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		q.offer(new int[] {i, j});
		visited[i][j]=true;
		map[i][j]=count;
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int d=0; d<4; d++){
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M || map[nr][nc]==0 || visited[nr][nc])	continue;
				
				q.offer(new int [] {nr,nc});
				map[nr][nc]=count;
				visited[nr][nc]=true;
			}
			
		}
	}
}
