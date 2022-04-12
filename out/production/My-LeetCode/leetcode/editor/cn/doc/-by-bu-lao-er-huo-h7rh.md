### 解题思路
模拟
每一行，每一列，每一个3*3矩阵，都是用一个数字，用来表示已经使用的数字。
具体，若第一行只使用了2，则row[0] | = (1<<2)。
如果想把第一行使用的2去掉，则只需 row[0] ^=(1<<2)，
利用位运算进行模拟可能的答案，
由于满足条件的答案可能会有多个，只需要返回第一个满足条件的即可。
代码中有详细注释！

![image.png](https://pic.leetcode-cn.com/1648092219-MtWchD-image.png)

### 代码java

```java
class Solution {
    public void solveSudoku(char[][] board) {
        //分别表示每一行，每一列，每一个3*3矩阵所对应的集合
        int[] rows = new int[9], cols = new int[9], rowCol = new int[9];
        //先把已经在矩阵中的元素，更新到对应的集合中
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                int a = board[i][j] - '0';
                rows[i] |= (1 << a);
                cols[j] |= (1 << a);
                //就算现在的数，应该位于哪个3*3矩阵中
                int t = (i / 3) * 3 + j / 3;
                rowCol[t] |= (1 << a);
            }
        }
        //为每一个未填入数字的单元格，填入数字
        dfs(board, rows, cols, rowCol, 0, 0);
    }

    boolean flag = true;

    private void dfs(char[][] board, int[] rows, int[] cols, int[] rowCol, int x, int y) {
        if (x == 9) {
            flag = false;
            return;
        }
        if (flag && y == 9)
            dfs(board, rows, cols, rowCol, x + 1, 0);
        else if (flag && board[x][y] != '.')
            dfs(board, rows, cols, rowCol, x, y + 1);
        else if (flag) {
            //计算出点(x,y)所对应的3*3矩阵
            int num = (x / 3) * 3 + y / 3;
            //模拟填入数据
            for (int i = 1; i < 10; i++) {
                if ((rows[x] >> i & 1) == 1 || (cols[y] >> i & 1) == 1 ||
                        (rowCol[num] >> i & 1) == 1)
                    continue;
                rows[x] |= (1 << i);
                cols[y] |= (1 << i);
                rowCol[num] |= (1 << i);
                board[x][y] = (char) (i + '0');
                dfs(board, rows, cols, rowCol, x, y + 1);
                //将board[x][y]位置填入i，不满足条件
                if (flag) {
                    rows[x] ^= (1 << i);
                    cols[y] ^= (1 << i);
                    rowCol[num] ^= (1 << i);
                    board[x][y] = '.';
                } else break;
            }
        }
    }
}
```

### 代码python

```python
class Solution:
    def solveSudoku(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        rows, cols, rowCol, flag = [0] * 9, [0] * 9, [0] * 9, True

        def dfs(x, y):
            nonlocal board, rows, cols, rowCol, flag
            # 已经找到一组满足条件的答案，直接返回
            if x == 9:
                flag = False
                return

            # 到达列的边界，直接跳入到下一行
            if flag and y == 9:
                dfs(x + 1, 0)
            # 该位置已被填充，直接跳到下一个
            elif flag and board[x][y] != '.':
                dfs(x, y + 1)
            elif flag:
                num = (x // 3) * 3 + y // 3
                for i in range(1, 10):
                    if (rows[x] >> i) & 1 or (cols[y] >> i) & 1 or (rowCol[num] >> i) & 1:
                        continue
                    # 假设该位置可以填入数字i
                    rows[x] |= (1 << i)
                    cols[y] |= (1 << i)
                    rowCol[num] |= (1 << i)
                    board[x][y] = chr(i + ord("0"))
                    #递归
                    dfs(x, y + 1)

                    # 该位置不能填入数字i
                    if flag:
                        rows[x] ^= (1 << i)
                        cols[y] ^= (1 << i)
                        rowCol[num] ^= (1 << i)
                        board[x][y] = '.'
                    else:
                        break

        # 初始化rows, cols, rowCol
        for i in range(9):
            for j in range(9):
                if board[i][j] == '.':
                    continue
                a = int(ord(str(board[i][j])) - ord("0"))
                rows[i] |= (1 << a)
                cols[j] |= (1 << a)
                t = (i // 3) * 3 + j // 3
                rowCol[t] |= (1 << a)
        #递归
        dfs(0, 0)
```
