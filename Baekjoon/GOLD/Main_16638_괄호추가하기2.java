import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16638_괄호추가하기2 {

	static int N;
	static int B;
	static boolean selected[];
	static int numbers[];
	static String formula;
	static int maxResult;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());	// 수식 길이
		formula = br.readLine();				// 수식
		
		B = ((N/2)+1)/2;	// 괄호식이 최대 몇개 들어가는지
		selected = new boolean[N/2];
		numbers = new int[B];
		maxResult = Integer.MIN_VALUE;
		
		int R = 0;			// 뽑을 괄호 갯수
		while(R <= B) {
			com(0, 0, R);
			R++;
		}
		
		System.out.println(maxResult);
	}

	private static void com(int start, int cnt, int R) {
		if(cnt == R) {
			int result =check(cnt, numbers);
			maxResult = Integer.max(result, maxResult);
			return;
		}
		
		for(int i=start; i<N/2; i++) {
			if(!selected[i]) {
				if(i!=0 && selected[i-1])	continue;	// 괄호 중첩 안되게
				selected[i] = true;
				numbers[cnt] = i;
				com(i+1, cnt+1, R);
				selected[i] = false;
			}
		}
	}

	private static int check(int cnt, int[] numbers) {
		String newFormula="";
		
		int idx=0;
		int cntIdx=0;
		// 괄호 계산
		while(idx < formula.length()) {
			if(cntIdx < cnt && idx==numbers[cntIdx]*2) {
				newFormula+=cal(formula.substring(idx, idx+3));
				cntIdx++;
				idx+=3;
			} else {
				newFormula+=formula.charAt(idx);
				idx++;
			}
		}
		
		String temp = "";
		idx=0;
		
		// 곱하기 먼저 계산
		while(idx < newFormula.length()) {
			if(newFormula.charAt(idx)=='*') {
				int s = idx-1;
				int e = idx+1;
				// 다음수 확인
				while(s-1 >= 0 && !"+-".contains(newFormula.charAt(s-1)+""))	s--;
				
				if(s-1 >= 0 && newFormula.charAt(s-1) =='-' ) { // 음수인지 확인
					if(s-1==0 || "+-".contains(newFormula.charAt(s-2)+"")) s--;	
				}

				if(newFormula.charAt(e)=='-') e++;	// 마이너스 일경우
				while(e+1 < newFormula.length() && !"*+-".contains(newFormula.charAt(e+1)+""))	e++;
				
				int a = Integer.parseInt(newFormula.substring(s,idx));
				int b = Integer.parseInt(newFormula.substring(idx+1,e+1));
				
				temp = newFormula.substring(0,s);
				temp += a*b;
				
				newFormula = temp + newFormula.substring(e+1,newFormula.length());
				idx = temp.length();
			}	else {
				idx++;
			}
		}
		
		// 계산
		idx=1;
		while(idx < newFormula.length()) {
			if("+-".contains(newFormula.charAt(idx)+"")) {
				int nextIdx=idx+1;
				if(newFormula.charAt(nextIdx)=='-')	nextIdx++;	// 음수인지 확인
				
				while(nextIdx < newFormula.length() && !"+-".contains(newFormula.charAt(nextIdx)+""))	nextIdx++;
				
				int a = Integer.parseInt(newFormula.substring(0, idx));
				int b = Integer.parseInt(newFormula.substring(idx+1, nextIdx));
				temp = "";
				if(newFormula.charAt(idx)=='+')	temp += a+b;
				else							temp += a-b;
				
				newFormula = temp + newFormula.substring(nextIdx, newFormula.length());
				idx = temp.length();
			} else {
				idx++;
			}
					
		}
		return Integer.parseInt(newFormula);
	}

	private static int cal(String formu) {
		int result=0;
		int a = formu.charAt(0)-'0';
		int b = formu.charAt(2)-'0';
		
		switch(formu.charAt(1)) {
			case '+' : 
				result = a+b;
				break;
			case '*' : 
				result = a*b;
				break;
			case '-' : 
				result = a-b;
				break;
		}
		
		return result;
	}

}
