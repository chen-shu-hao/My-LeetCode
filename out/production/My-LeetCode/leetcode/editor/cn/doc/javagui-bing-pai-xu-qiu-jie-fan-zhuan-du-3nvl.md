### 解题思路
**翻转对的数量等于[l, mid]中翻转对的数量+[mid+1, r]中翻转对的数量+双指针i,j分别位于这两个区间中时[l, r]中翻转对的数量**
在calculate中，[l, mid]和[mid + 1, r]两部分数组是已经排好序的数组

![image.png](https://pic.leetcode-cn.com/1639141255-AQqswA-image.png)

### 代码

```java
class Solution {
    int cnt = 0;
    public int reversePairs(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return cnt;
    }

    void mergeSort(int[] nums, int l, int r) {
        if(l >= r) return;
        int mid = (l + r) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        calculate(nums, l, mid, r);
        sort(nums, l, mid, r);
    }

    void calculate(int[] nums, int l, int mid, int r) {
        for(int i = l, j = mid + 1; i <= mid; i++) {
            while(j <= r && nums[i] > 2L * nums[j]) j++;
            cnt += j - (mid + 1);
        }
    }

    void sort(int[] nums, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int i = l, j = mid + 1;
        int index = 0;

        while(i <= mid && j <= r) {
            temp[index++] = nums[i] > nums[j] ? nums[j++] : nums[i++];
        }

        while(i <= mid) temp[index++] = nums[i++];
        while(j <= r) temp[index++] = nums[j++];

        for(int k = 0; k < temp.length; k++) {
            nums[l + k] = temp[k];
        }
    }
}
```