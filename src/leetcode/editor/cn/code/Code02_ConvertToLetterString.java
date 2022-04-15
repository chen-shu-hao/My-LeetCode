package leetcode.editor.cn.code;

public class Code02_ConvertToLetterString {
	private static int count;
	static void printIndent(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print("   ");
		}
	}

	// str只含有数字字符0~9 例如 111可以转为AAA KA AK 3种
	// 返回多少种转化方案
	public static int number(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		return process(str.toCharArray(), 0);
	}

	// str[0..i-1]转化无需过问
	// str[i.....]去转化，返回有多少种转化方法 从i到字符末尾总共有多少种转换方法
	public static int process(char[] str, int i) {
		printIndent(count++);
		System.out.printf("i = %d\n", i);
		if (i == str.length) {
			printIndent(--count);
			System.out.print("return 1\n");
			return 1;
		}
		// i没到最后，说明有字符
		if (str[i] == '0') { // 之前的决定有问题
			printIndent(--count);
			System.out.print("return 0\n");
			return 0;
		}
		// str[i] != '0'
		// 可能性一，i单转
		int ways = process(str, i + 1);
		System.out.printf("first i %d,ways %d\n", i,ways);
		if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
			System.out.println("进入第二层");
			int ways1 = process(str, i + 2);
			System.out.printf("two ways i %d,%d\n",i, ways1);
			ways = ways + ways1;
		} else {
			System.out.println("没有进入第二层");
		}
		printIndent(--count);
		System.out.printf("return %d\n", ways);
		return ways;
	}

	public static int dp(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[] dp = new int[N + 1];
		dp[N] = 1;
		for (int i = N - 1; i >= 0; i--) {
			if (str[i] != '0') {
				int ways = dp[i + 1];
				if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
					ways += dp[i + 2];
				}
				dp[i] = ways;
			}
		}
		return dp[0];
	}

	public static void main(String[] args) {
		System.out.println(number("12345"));
		//System.out.println(dp("1111"));
		//System.out.println(dp("7210231231232031203123"));
	}

}
