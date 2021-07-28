import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3122 {
	static class Point{
		double x;
		double y;
		
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Point line[][] = new Point[N+1][2];

		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			double x1 = Double.parseDouble(st.nextToken());
			double y1 = Double.parseDouble(st.nextToken());
			double x2 = Double.parseDouble(st.nextToken());
			double y2 = Double.parseDouble(st.nextToken());
			
			if(y1 <= y2) {
				line[i][0] = new Point(x1,y1);
				line[i][1] = new Point(x2,y2);
			} else {
				line[i][0] = new Point(x2,y2);
				line[i][1] = new Point(x1,y1);
			}
		}
		
		int cnt=0;
		for(int i=1; i<=N-1; i++) {
			for(int j=i+1; j<=N; j++) {
				if(isMeet(line[i],line[j]))	cnt++;
			}
		}
		System.out.println(cnt);
	}

	private static boolean isMeet(Point[] L1, Point[] L2) {
		
		Point a = new Point(L1[1].x-L1[0].x, L1[1].y-L1[0].y);
		Point b = new Point(L2[0].x-L1[0].x, L2[0].y-L1[0].y);
		Point c = new Point(L2[1].x-L1[0].x, L2[1].y-L1[0].y);
		
		double r1 = (double)(a.x*b.y - a.y*b.x) * (double)(a.x*c.y - a.y*c.x);
		
		Point d = new Point(L2[1].x-L2[0].x, L2[1].y-L2[0].y);
		Point e = new Point(L1[1].x-L2[0].x, L1[1].y-L2[0].y);
		Point f = new Point(L1[0].x-L2[0].x, L1[0].y-L2[0].y);
		
		double r2 = (double)(d.x*e.y - d.y*e.x) * (double)(d.x*f.y - d.y*f.x);
		
		
		if((r1<0 && r2<0) || (r1<=0 && r2<0) || (r1<0 && r2<=0))	return true;
		if(r1==0 && r2 ==0) {
			// 겹치는지 확인
			if((L1[0].y > L2[0].y && L1[0].y > L2[1].y) ||
				(L2[0].y > L1[0].y && L2[0].y > L1[1].y))	return false;
			else if(L1[1].y == L2[0].y && L1[1].x < L2[0].x)	return false;
			else return true;
		}
		return false;
		
	}
}
