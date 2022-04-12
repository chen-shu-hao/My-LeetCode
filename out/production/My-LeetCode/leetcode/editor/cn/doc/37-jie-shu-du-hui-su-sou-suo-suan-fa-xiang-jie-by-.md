## 思路

**如果对回溯算法基础还不了解的话，我还特意录制了一期视频：[带你学透回溯算法（理论篇）](https://www.bilibili.com/video/BV1cy4y167mM/)**  可以结合题解和视频一起看，希望对大家理解回溯算法有所帮助。

棋盘搜索问题可以使用回溯法暴力搜索，只不过这次我们要做的是**二维递归**。

怎么做二维递归呢？

大家已经跟着「代码随想录」刷过了如下回溯法题目，例如：[77.组合（组合问题）](https://programmercarl.com/0077.组合.html)，[131.分割回文串（分割问题）](https://programmercarl.com/0131.分割回文串.html)，[78.子集（子集问题）](https://programmercarl.com/0078.子集.html)，[46.全排列（排列问题）](https://programmercarl.com/0046.全排列.html)，以及[51.N皇后（N皇后问题）](https://programmercarl.com/0051.N皇后.html)，其实这些题目都是一维递归。

**如果以上这几道题目没有做过的话，不建议上来就做这道题哈！**

[N皇后问题](https://programmercarl.com/0051.N皇后.html)是因为每一行每一列只放一个皇后，只需要一层for循环遍历一行，递归来来遍历列，然后一行一列确定皇后的唯一位置。

本题就不一样了，**本题中棋盘的每一个位置都要放一个数字，并检查数字是否合法，解数独的树形结构要比N皇后更宽更深**。

因为这个树形结构太大了，我抽取一部分，如图所示：

![37.解数独](https://pic.leetcode-cn.com/1631608733-vZjRrJ-file_1631608733117)


## 回溯三部曲

* 递归函数以及参数

**递归函数的返回值需要是bool类型，为什么呢？**

因为解数独找到一个符合的条件（就在树的叶子节点上）立刻就返回，相当于找从根节点到叶子节点一条唯一路径，所以需要使用bool返回值，这一点在[回溯算法：N皇后问题](https://programmercarl.com/0051.N皇后.html)中已经介绍过了，一样的道理。

代码如下：

```
bool backtracking(vector<vector<char>>& board)
```

* 递归终止条件

本题递归不用终止条件，解数独是要遍历整个树形结构寻找可能的叶子节点就立刻返回。

**不用终止条件会不会死循环？**

递归的下一层的棋盘一定比上一层的棋盘多一个数，等数填满了棋盘自然就终止（填满当然好了，说明找到结果了），所以不需要终止条件！

**那么有没有永远填不满的情况呢？**

这个问题我在递归单层搜索逻辑里在来讲！

* 递归单层搜索逻辑

![37.解数独](https://pic.leetcode-cn.com/1631608733-vZjRrJ-file_1631608733117)

在树形图中可以看出我们需要的是一个二维的递归（也就是两个for循环嵌套着递归）

**一个for循环遍历棋盘的行，一个for循环遍历棋盘的列，一行一列确定下来之后，递归遍历这个位置放9个数字的可能性！**


代码如下：（**详细看注释**）

```CPP
bool backtracking(vector<vector<char>>& board) {
    for (int i = 0; i < board.size(); i++) {        // 遍历行
        for (int j = 0; j < board[0].size(); j++) { // 遍历列
            if (board[i][j] != '.') continue;
            for (char k = '1'; k <= '9'; k++) {     // (i, j) 这个位置放k是否合适
                if (isValid(i, j, k, board)) {
                    board[i][j] = k;                // 放置k
                    if (backtracking(board)) return true; // 如果找到合适一组立刻返回
                    board[i][j] = '.';              // 回溯，撤销k
                }
            }
            return false;                           // 9个数都试完了，都不行，那么就返回false
        }
    }
    return true; // 遍历完没有返回false，说明找到了合适棋盘位置了
}
```

**注意这里return false的地方，这里放return false 是有讲究的**。

因为如果一行一列确定下来了，这里尝试了9个数都不行，说明这个棋盘找不到解决数独问题的解！

那么会直接返回， **这也就是为什么没有终止条件也不会永远填不满棋盘而无限递归下去！**

## 判断棋盘是否合法

判断棋盘是否合法有如下三个维度：

* 同行是否重复
* 同列是否重复
* 9宫格里是否重复

代码如下：

```CPP
bool isValid(int row, int col, char val, vector<vector<char>>& board) {
    for (int i = 0; i < 9; i++) { // 判断行里是否重复
        if (board[row][i] == val) {
            return false;
        }
    }
    for (int j = 0; j < 9; j++) { // 判断列里是否重复
        if (board[j][col] == val) {
            return false;
        }
    }
    int startRow = (row / 3) * 3;
    int startCol = (col / 3) * 3;
    for (int i = startRow; i < startRow + 3; i++) { // 判断9方格里是否重复
        for (int j = startCol; j < startCol + 3; j++) {
            if (board[i][j] == val ) {
                return false;
            }
        }
    }
    return true;
}
```

最后整体代码如下：

## C++代码

```CPP
class Solution {
private:
bool backtracking(vector<vector<char>>& board) {
    for (int i = 0; i < board.size(); i++) {        // 遍历行
        for (int j = 0; j < board[0].size(); j++) { // 遍历列
            if (board[i][j] != '.') continue;
            for (char k = '1'; k <= '9'; k++) {     // (i, j) 这个位置放k是否合适
                if (isValid(i, j, k, board)) {
                    board[i][j] = k;                // 放置k
                    if (backtracking(board)) return true; // 如果找到合适一组立刻返回
                    board[i][j] = '.';              // 回溯，撤销k
                }
            }
            return false;                           // 9个数都试完了，都不行，那么就返回false
        }
    }
    return true; // 遍历完没有返回false，说明找到了合适棋盘位置了
}
bool isValid(int row, int col, char val, vector<vector<char>>& board) {
    for (int i = 0; i < 9; i++) { // 判断行里是否重复
        if (board[row][i] == val) {
            return false;
        }
    }
    for (int j = 0; j < 9; j++) { // 判断列里是否重复
        if (board[j][col] == val) {
            return false;
        }
    }
    int startRow = (row / 3) * 3;
    int startCol = (col / 3) * 3;
    for (int i = startRow; i < startRow + 3; i++) { // 判断9方格里是否重复
        for (int j = startCol; j < startCol + 3; j++) {
            if (board[i][j] == val ) {
                return false;
            }
        }
    }
    return true;
}
public:
    void solveSudoku(vector<vector<char>>& board) {
        backtracking(board);
    }
};
```

## 总结

解数独可以说是非常难的题目了，如果还一直停留在单层递归的逻辑中，这道题目可以让大家瞬间崩溃。

所以我在开篇就提到了**二维递归**，这也是我自创词汇，希望可以帮助大家理解解数独的搜索过程。

一波分析之后，在看代码会发现其实也不难，唯一难点就是理解**二维递归**的思维逻辑。

**这样，解数独这么难的问题，也被我们攻克了**。

**恭喜一路上坚持打卡的录友们，回溯算法已经接近尾声了，接下来就是要一波总结了**。


## 其他语言版本


Java：
```java
class Solution {
    public void solveSudoku(char[][] board) {
        solveSudokuHelper(board);
    }

    private boolean solveSudokuHelper(char[][] board){
        //「一个for循环遍历棋盘的行，一个for循环遍历棋盘的列，
        // 一行一列确定下来之后，递归遍历这个位置放9个数字的可能性！」
        for (int i = 0; i < 9; i++){ // 遍历行
            for (int j = 0; j < 9; j++){ // 遍历列
                if (board[i][j] != '.'){ // 跳过原始数字
                    continue;
                }
                for (char k = '1'; k <= '9'; k++){ // (i, j) 这个位置放k是否合适
                    if (isValidSudoku(i, j, k, board)){
                        board[i][j] = k;
                        if (solveSudokuHelper(board)){ // 如果找到合适一组立刻返回
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                // 9个数都试完了，都不行，那么就返回false
                return false;
                // 因为如果一行一列确定下来了，这里尝试了9个数都不行，说明这个棋盘找不到解决数独问题的解！
                // 那么会直接返回， 「这也就是为什么没有终止条件也不会永远填不满棋盘而无限递归下去！」
            }
        }
        // 遍历完没有返回false，说明找到了合适棋盘位置了
        return true;
    }

    /**
     * 判断棋盘是否合法有如下三个维度:
     *     同行是否重复
     *     同列是否重复
     *     9宫格里是否重复
     */
    private boolean isValidSudoku(int row, int col, char val, char[][] board){
        // 同行是否重复
        for (int i = 0; i < 9; i++){
            if (board[row][i] == val){
                return false;
            }
        }
        // 同列是否重复
        for (int j = 0; j < 9; j++){
            if (board[j][col] == val){
                return false;
            }
        }
        // 9宫格里是否重复
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++){
            for (int j = startCol; j < startCol + 3; j++){
                if (board[i][j] == val){
                    return false;
                }
            }
        }
        return true;
    }
}
```

Python：
```python3
class Solution:
    def solveSudoku(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        def backtrack(board):
            for i in range(len(board)):  #遍历行
                for j in range(len(board[0])):  #遍历列
                    if board[i][j] != ".": continue
                    for k in range(1,10):  #(i, j) 这个位置放k是否合适
                        if isValid(i,j,k,board):
                            board[i][j] = str(k)  #放置k
                            if backtrack(board): return True  #如果找到合适一组立刻返回
                            board[i][j] = "."  #回溯，撤销k
                    return False  #9个数都试完了，都不行，那么就返回false
            return True  #遍历完没有返回false，说明找到了合适棋盘位置了
        def isValid(row,col,val,board):
            for i in range(9):  #判断行里是否重复
                if board[row][i] == str(val):
                    return False
            for j in range(9):  #判断列里是否重复
                if board[j][col] == str(val):
                    return False
            startRow = (row // 3) * 3
            startcol = (col // 3) * 3
            for i in range(startRow,startRow + 3):  #判断9方格里是否重复
                for j in range(startcol,startcol + 3):
                    if board[i][j] == str(val):
                        return False
            return True
        backtrack(board)
```

Python3:

```python3
class Solution:
    def __init__(self) -> None:
        self.board = []

    def isValid(self, row: int, col: int, target: int) -> bool:
        for idx in range(len(self.board)):
            # 同列是否重复
            if self.board[idx][col] == str(target):
                return False
            # 同行是否重复
            if self.board[row][idx] == str(target):
                return False
            # 9宫格里是否重复
            box_row, box_col = (row // 3) * 3 + idx // 3, (col // 3) * 3 + idx % 3
            if self.board[box_row][box_col] == str(target):
                return False
        return True
    
    def getPlace(self) -> List[int]:
        for row in range(len(self.board)):
            for col in range(len(self.board)):
                if self.board[row][col] == ".":
                    return [row, col]
        return [-1, -1]
    
    def isSolved(self) -> bool:
        row, col = self.getPlace() # 找个空位置
        
        if row == -1 and col == -1: # 没有空位置，棋盘被填满的
            return True
        
        for i in range(1, 10):
            if self.isValid(row, col, i): # 检查这个空位置放i，是否合适
                self.board[row][col] = str(i) # 放i
                if self.isSolved(): # 合适，立刻返回, 填下一个空位置。
                    return True
                self.board[row][col] = "." # 不合适，回溯
        
        return False # 空位置没法解决

    def solveSudoku(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        if board is None or len(board) == 0:
            return
        self.board = board
        self.isSolved()
```

Go：

Javascript:
```Javascript
var solveSudoku = function(board) {
    function isValid(row, col, val, board) {
        let len = board.length
        // 行不能重复
        for(let i = 0; i < len; i++) {
            if(board[row][i] === val) {
                return false
            }
        }
        // 列不能重复
        for(let i = 0; i < len; i++) {
            if(board[i][col] === val) {
                return false
            }
        }
        let startRow = Math.floor(row / 3) * 3
        let startCol = Math.floor(col / 3) * 3

        for(let i = startRow; i < startRow + 3; i++) {
            for(let j = startCol; j < startCol + 3; j++) {
                if(board[i][j] === val) {
                    return false
                }
            }
        }

        return true
    }

    function backTracking() {
        for(let i = 0; i < board.length; i++) {
            for(let j = 0; j < board[0].length; j++) {
                if(board[i][j] !== '.') continue
                for(let val = 1; val <= 9; val++) {
                    if(isValid(i, j, `*{val}`, board)) {
                        board[i][j] = `*{val}`
                        if (backTracking()) {
                            return true
                        }
                        
                        board[i][j] = `.`
                    }
                }
                return false
            }
        }
        return true
    }
    backTracking(board)
    return board
    
};
```

C:

```C
bool isValid(char** board, int row, int col, int k) {
    /* 判断当前行是否有重复元素 */
    for (int i = 0; i < 9; i++) {
        if (board[i][col] == k) {
            return false;
        }
    }
    /* 判断当前列是否有重复元素 */
    for (int j = 0; j < 9; j++) {
        if (board[row][j] == k) {
            return false;
        }
    }
    /* 计算当前9宫格左上角的位置 */
    int startRow = (row / 3) * 3;
    int startCol = (col / 3) * 3;
    /* 判断当前元素所在九宫格是否有重复元素 */
    for (int i = startRow; i < startRow + 3; i++) {
        for (int j = startCol; j < startCol + 3; j++) {
            if (board[i][j] == k) {
                return false;
            }
        }
    }
    /* 满足条件，返回true */
    return true;
}

bool backtracking(char** board, int boardSize, int* boardColSize) {
    /* 从上到下、从左到右依次遍历输入数组 */
    for (int i = 0; i < boardSize; i++) {
        for (int j = 0; j < *boardColSize; j++) {
            /* 遇到数字跳过 */
            if (board[i][j] != '.') {
                continue;
            }
            /* 依次将数组1到9填入当前位置 */
            for (int k = '1'; k <= '9'; k++) {
                /* 判断当前位置是否与满足条件，是则进入下一层 */
                if (isValid(board, i, j, k)) {
                    board[i][j] = k;
                    /* 判断下一层递归之后是否找到一种解法，是则返回true */
                    if (backtracking(board, boardSize, boardColSize)) {
                        return true;
                    }
                    /* 回溯，将当前位置清零 */
                    board[i][j] = '.';
                }
            }
            /* 若填入的9个数均不满足条件，返回false，说明此解法无效 */
            return false;
        }
    }
    /* 遍历完所有的棋盘，没有返回false，说明找到了解法，返回true */
    return true;
}

void solveSudoku(char** board, int boardSize, int* boardColSize) {
    bool res = backtracking(board, boardSize, boardColSize);
}
```

# 回溯算法力扣题目总结

按照如下顺序刷力扣上的题目，相信会帮你在学习回溯算法的路上少走很多弯路。

* [关于回溯算法，你该了解这些！](https://programmercarl.com/回溯算法理论基础.html)
* 组合问题
    * [77.组合](https://programmercarl.com/0077.组合.html)
    * [216.组合总和III](https://programmercarl.com/0216.组合总和III.html)
    * [17.电话号码的字母组合](https://programmercarl.com/0017.电话号码的字母组合.html)
    * [39.组合总和](https://programmercarl.com/0039.组合总和.html)
    * [40.组合总和II](https://programmercarl.com/0040.组合总和II.html)
* 分割问题
    * [131.分割回文串](https://programmercarl.com/0131.分割回文串.html)
    * [93.复原IP地址](https://programmercarl.com/0093.复原IP地址.html)
* 子集问题 
    * [78.子集](https://programmercarl.com/0078.子集.html)
    * [90.子集II](https://programmercarl.com/0090.子集II.html)
* 排列问题
    * [46.全排列](https://programmercarl.com/0046.全排列.html)
    * [47.全排列II](https://programmercarl.com/0047.全排列II.html)
* 棋盘问题
    * [51.N皇后](https://programmercarl.com/0051.N皇后.html)
    * [37.解数独](https://programmercarl.com/0037.解数独.html)
* 其他 
    * [491.递增子序列](https://programmercarl.com/0491.递增子序列.html)
    * [332.重新安排行程](https://programmercarl.com/0332.重新安排行程.html)
* [回溯算法总结篇](https://programmercarl.com/回溯总结.html)

-----------

**大家好，我是程序员Carl，点击[我的头像](https://mp.weixin.qq.com/s/_DzddsMeQW5JPI6qoC7ARQ)**，查看力扣详细刷题攻略，你会发现相见恨晚！

**如果感觉题解对你有帮助，不要吝啬给一个👍吧！**


