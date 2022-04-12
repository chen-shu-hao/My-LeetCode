说一下这道题的难点以及我的理解吧，不想一下，以后还会忘掉，那么太麻烦了。
难点主要是当`p[j-1]=='*'`的时候，其实我们可以得出下面的结论。
1. `*`可以提供消除的功能，并且只能消除它本身和它的前一个字符，比如 空字符串`s=""`与`p="a*"`能够匹配
2. `*`可以提供添加的功能，可以添加若干个它的前一个字符。那么可以得出下面的性质: 
   - 当`s[i-1]`和`p[j-2]`匹配的时候，作为`p[j-1]`的符号`*`就有能力去自己产生`p[j-2]`，于是我们可以推导出这样一个结论: **当`s[i-1]`和`p[j-2]`匹配的时候`f[i][j]==f[i-1][j]`**，因为当`f[i-1][j]`为true的时候，`*`可以自己产生一个`p[j-2]` 与`s[i-1]`匹配，使得`f[i][j]`也为true，而当`f[i-1][j]`为false的时候，表示匹配不成功，那么既然前面匹配不成功，后面增加什么也不会匹配成功。
   - 当`s[i-1]`和`p[j-2]`不匹配的时候，就自然没有没有这个性质了。

因此当`p[j-1]=='*'`,推导公式为:
```java
         f[i][j-2]||f[i-1][j]  when s[i-1] matches p[j-2]
f[i][j]=
         f[i][j-2]             when s[i-1] ! matches p[j-2]
```
想个例子测试一下：比如`aaabb`:`aaab*` => `aaab`:`aaab*` => `aaa`:`aaab*` => `aaa`:`aaa`=> true

完整代码
```java
import java.util.Stack;

class Solution {
    public boolean isMatch(String s, String p) {
        //
        int m=s.length();
        int n=p.length();
        boolean[][]f=new boolean[m+1][n+1];
        //dp 初始化
        f[0][0]=true;
        //f[i][0]=false;
        //f[0][i]需要讨论
        int count=0;
        for(int i=1;i<=n;i++){
            char c=p.charAt(i-1);
            if(c!='*') count++;
            else count--;
            f[0][i]= count == 0;
        }
        //
        for(int i=1;i<=m;i++){
            char c1=s.charAt(i-1);
            for(int j=1;j<=n;j++){
                //s的前i个 和 p的前j个是否匹配
                char c2=p.charAt(j-1);
                if(Character.isLetter(c2)){
                    if(c1==c2) f[i][j]=f[i-1][j-1];
                    else f[i][j]=false;
                }else if(c2=='.'){
                    f[i][j]=f[i-1][j-1];
                }else{
                    f[i][j]=f[i][j]||f[i][j-2];
                    char c=p.charAt(j-2);
                    if(c==c1||c=='.'){
                        f[i][j]=f[i][j]||f[i-1][j];
                    }
                    }
                }
            }
        return  f[m][n];
        }
    }
```